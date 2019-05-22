package com.example.result;

import com.example.model.BasePojo;

public class ResultResponse extends BasePojo {

    private static final long serialVersionUID = 1L;

    private int code = 0;

    private String codeText = null;

    private String message = null;

    private ResultCode resultCode = null;

    public ResultResponse() {
        this(ResultCode.OK);
    }

    public static ResultResponse fail() {
        return init(ResultCode.RESULT_OPERATION_FAILED);
    }

    public static ResultResponse fail(String message) {
        return init(ResultCode.RESULT_OPERATION_FAILED).setMessage(message);
    }

    public static ResultResponse init(ResultCode code) {
        ResultResponse result = new ResultResponse();
        result.setResultCode(code);
        return result;
    }

    public static ResultResponse ok() {
        return new ResultResponse();
    }

    public static ResultResponse ok(String message) {
        ResultResponse result = new ResultResponse();
        result.setMessage(message);
        return result;
    }

    public ResultResponse(ResultCode code) {
        this(code, null);
    }

    public ResultResponse(ResultCode resultCode, String message) {
        this.resultCode = resultCode;
        this.code = resultCode.code();
        this.codeText = resultCode.name();
        if (message == null) {
            this.message = resultCode.text();
        } else {
            this.message = message;
        }
    }

    public ResultResponse setResultCode(ResultCode code) {
        this.code = code.code();
        this.codeText = code.name();
        this.message = code.text();
        this.resultCode = code;
        return this;
    }

    public ResultCode resultCode() {
        return this.resultCode;
    }

    public ResultResponse copyResultCode(ResultResponse result) {
        this.code = result.getCode();
        this.codeText = result.getCodeText();
        this.message = result.getMessage();
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultResponse setMessage(String... message) {
        if (message == null) {
            return this;
        }

        if (message.length == 1) {
            this.message = message[0];
            return this;
        }

        StringBuilder strBld = new StringBuilder();
        for (String m : message) {
            strBld.append(m);
        }
        this.message = strBld.toString();
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCodeText() {
        return codeText;
    }

    public void setCodeText(String codeText) {
        this.codeText = codeText;
    }

    public boolean isSuccess() {
        return (this.code == ResultCode.OK.code());
    }
}