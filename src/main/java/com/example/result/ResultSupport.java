package com.example.result;

import com.example.utils.JsonUtil;

import javax.servlet.ServletResponse;
import java.io.IOException;

public class ResultSupport {
    private ResultSupport() {
    }

    public static ResultResponse ok() {
        return new ResultResponse();
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<T>(ResultCode.OK);
        result.setData(data);
        return result;
    }

    /*
     * public static Result<String> ok(ServletResponse resp) throws IOException
     * { return outputJson(ok(), resp); }
     */

    public static <T> Result<T> serverError() {
        return new Result<T>(ResultCode.RESULT_SYSTEM_ERROR);
    }

    public static <T> Result<T> serverError(ResultCode code) {
        return new Result<T>(code);
    }

    public static Result<String> serverError(ServletResponse resp) throws IOException {
        return outputJson(serverError(), resp);
    }

    public static Result<String> forbidden() {
        return new Result<String>(ResultCode.FORBIDDEN);
    }

    public static Result<String> forbidden(ServletResponse resp) throws IOException {
        return outputJson(forbidden(), resp);
    }

    public static final <T> Result<T> outputJson(Result<T> obj, ServletResponse resp) throws IOException {
        resp.getWriter().write(JsonUtil.toJson(obj));
        return obj;
    }

    public static final Result<String> outputJson(ResultCode resultCode, ServletResponse resp) throws IOException {
        return ResultSupport.outputJson(new Result<String>(resultCode), resp);
    }

    public static <T> Result<T> paramError() {
        return serverError(ResultCode.RESULT_INVALID_PARA);
    }

    public static <T> Result<T> authCodeError() {
        return serverError(ResultCode.RESULT_AUTHCODE_ERROR);
    }

    public static <T> Result<T> paramError(String message) {
        Result<T> result = serverError(ResultCode.RESULT_INVALID_PARA);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> dataNotFound() {
        Result<T> result = new Result<T>(ResultCode.RESULT_DATA_NOTFOUND);
        return result;
    }

    public static <T> Result<T> dataNotFound(String message) {
        Result<T> result = new Result<T>(ResultCode.RESULT_DATA_NOTFOUND);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> fail(ResultCode resultCode) {
        Result<T> result = new Result<T>(resultCode);
        return result;
    }
}
