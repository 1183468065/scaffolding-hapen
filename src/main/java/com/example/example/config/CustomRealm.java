package com.example.example.config;

import com.example.example.model.User;
import com.example.example.service.PermService;
import com.example.example.service.RoleService;
import com.example.example.service.UserService;
import com.example.example.utils.JsonUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * 这个类是参照JDBCRealm写的，主要是自定义了如何查询用户信息，如何查询用户的角色和权限，如何校验密码等逻辑
 */
public class CustomRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(AuthorizingRealm.class);
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermService permService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("授权");
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        User user = (User) getAvailablePrincipal(principals);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        log.info("获取角色信息：" + user.getRoles());
        log.info("获取权限信息：" + user.getPerms());
        authorizationInfo.setRoles(user.getRoles());
        authorizationInfo.setStringPermissions(user.getPerms());
        return authorizationInfo;
    }

    //定义如何获取用户信息的业务逻辑，给shiro做登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("登录认证");
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();

        // Null username is invalid
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }

        User userDB = userService.findUserByName(username);
        log.info("userDB:{}", JsonUtil.toJson(userDB));

        if (userDB == null) {
            throw new UnknownAccountException("No account found for admin [" + username + "]");
        }

        //查询用户的角色和权限存到SimpleAuthenticationInfo中，这样在其它地方
        //SecurityUtils.getSubject().getPrincipal()就能拿出用户的所有信息，包括角色和权限
        Set<String> roles = roleService.getRolesByUserId(userDB.getUid());
        Set<String> perms = permService.getPermsByUserId(userDB.getUid());
        userDB.getRoles().addAll(roles);
        userDB.getPerms().addAll(perms);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userDB, userDB.getPwd(), getName());
        if (userDB.getSalt() != null) {
            info.setCredentialsSalt(ByteSource.Util.bytes(userDB.getSalt()));
        }

        return info;

    }

}
