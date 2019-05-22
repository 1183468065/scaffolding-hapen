package com.example.session;

import com.example.model.SysUser;

import java.io.Serializable;

/**
 * @author hanxiangxue
 */
public class CustomerSession implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private SysUser sysUser;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }
}
