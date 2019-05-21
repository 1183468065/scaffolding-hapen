package com.example.example.enums;

public enum Status implements EnumType {
    DELETED(-1, "已删除"),
    UNKNOWN(0, "未知"),
    NORMAL(1, "正常"),
    ;
    private final int code;
    private final String text;

    private Status(int code, String text) {
        this.code = code;
        this.text = text;
    }

    @Override
    public int code() {
        return code;
    }
    

    @Override
    public String text() {
        return text;
    }

    public static Status codeOf(int code) {
        if (code < 0) {
            return Status.DELETED;
        }
        
        for (Status value : values()) {
            if (value.code == code) {
                return value;
            }
        }

        throw new IllegalArgumentException("Invalid Status code: " + code);
    }
}
