package com.example.result;

import com.example.enums.EnumType;

public enum ResultCode implements EnumType {
    OK(200, "OK"),
    RESULT_INVALID_PARA(400, "invalid parameter"),
    RESULT_LOGIN_EXPIRED(401, "login expired"),
    RESULT_ANOTHER_LOGIN(402, "你的账号在另一设备登录，为了安全，该设备已下线"),
    FORBIDDEN(403, "Forbidden"),
    RESULT_DATA_NOTFOUND(404, "data not found"),
    CONFLICT(409, "Conflict"),

    RESULT_SYSTEM_ERROR(500, "system error!"),

    RESULT_NEED_ADVANCE_AUTH(1001, "need advance wechat auth"),
    RESULT_NEED_PASSWORD(1002, "need password"),
    RESULT_NEED_BINDPHONE(1003, "need bindPhone"),
    RESULT_NEED_SUBSCRIBE(1004, "need subscribe"),
    RESULT_NEED_SET_PASSWORD(1005, "need set password"),
    RESULT_ACCOUNT_ERR(1006, "invalid account"),
    RESULT_NEED_BINDUSERNAME(1007, "need bindUserName"),
    RESULT_ACCOUNT_FROZEN(1008, "账户已经冻结"),
    RESULT_NO_AUTHORITY(401, "没有相关权限"),
    RESULT_OPERATION_FAILED(2000, "unknown error"),
    RESULT_INVALID_BALANCE(2001, "invalid balance"),
    RESULT_LOGINNAME_OR_PASSWORD_ERR(2002, "用户名或密码不正确"),
    RESULT_OLDPASSWORD_ERR(2003, "旧密码不正确"),
    RESULT_TWO_PASSWORD_SAME(2004, "新旧密码相同"),
    RESULT_TWO_PASSWORD_NOT_THE_SAME(2005, "两次输入的密码不一致"),
    RESULT_AUTHCODE_FREQUENTLY(2101, "距离上次验证码发送时间过短"),
    RESULT_AUTHCODE_ERROR(2102, "验证码不正确"),
    RESULT_AUTHCODE_PAST_DUE(2103, "验证码已经过期"),
    RESULT_PHONE_EXISTS(2104, "手机号已存在"),
    RESULT_PHONE_ERROR(2105, "手机号错误"),
    RESULT_PHONE_NO_EXISTS(2106, "该手机号并未注册"),
    RESULT_PHONE_NO_BINDING(2108, "尚未绑定手机号"),
    RESULT_NEWDATA(2201, "新数据"),
    RESULT_UPDDATA(2202, "更新数据"),
    RESULT_HAVERECORD(2003, "有相同数据"),
    RESULT_DUPLICATE(2204, "数据重复"),
    RESULT_DATE_INCONSISTENCY(2205, "数据不一致"),
    RESULT_USER_REGISTER_FAIL(2207, "注册用户失败"),
    PHONE_CODE_SEND_ERROR(1201, "手机验证码发送失败。"),
    PAY_SUCCESS(2000, "支付成功"),
    PAY_FAILED(2010, "支付失败"),
    PAY_DUPLICATE(2011, "重复支付"),

    //用于单点登录

    SSO_CODE_INVALID(3010, "无效的code"),
    SSO_TOKEN_INVALID(3010, "无效的token"),


    ;

    private final int code;
    private final String text;

    private ResultCode(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int code() {
        return code;
    }

    public String text() {
        return text;
    }

    public static ResultCode codeOf(int code) {
        for (ResultCode value : values()) {
            if (value.code == code) {
                return value;
            }
        }

        throw new IllegalArgumentException("Invalid ResultCode code: " + code);
    }
}
