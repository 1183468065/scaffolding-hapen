package gen.example.entity;

import com.example.model.BaseEntity;

/**
 * t_sys_user 系统用户表
 * @author ASUS 2019-05-24
 */
public class SysUserEntity extends BaseEntity {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 登录用户名
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 工号
     */
    private String jobNo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 用户头像
     */
    private String photo;

    /**
     * 是否可登录：可以:Y 否:N
     */
    private String loginFlag;

    /**
     * 用户id
     * @return id 用户id
     */
    public Long getId() {
        return id;
    }

    /**
     * 用户id
     * @param id 用户id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 登录用户名
     * @return login_name 登录用户名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 登录用户名
     * @param loginName 登录用户名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * 登录密码
     * @return password 登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 登录密码
     * @param password 登录密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 工号
     * @return job_no 工号
     */
    public String getJobNo() {
        return jobNo;
    }

    /**
     * 工号
     * @param jobNo 工号
     */
    public void setJobNo(String jobNo) {
        this.jobNo = jobNo == null ? null : jobNo.trim();
    }

    /**
     * 姓名
     * @return name 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 邮箱
     * @return email 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 电话
     * @return phone 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 电话
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 手机
     * @return mobile 手机
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 手机
     * @param mobile 手机
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 用户头像
     * @return photo 用户头像
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * 用户头像
     * @param photo 用户头像
     */
    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    /**
     * 是否可登录：可以:Y 否:N
     * @return login_flag 是否可登录：可以:Y 否:N
     */
    public String getLoginFlag() {
        return loginFlag;
    }

    /**
     * 是否可登录：可以:Y 否:N
     * @param loginFlag 是否可登录：可以:Y 否:N
     */
    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag == null ? null : loginFlag.trim();
    }
}