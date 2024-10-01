package com.lab.commonEnum;

public enum ExceptionEnum {
    // 数据操作错误定义
    BAD_REQUEST(400, "请求的数据格式不符!"),
    UNAUTHORIZED(401, "未授权，请登录或联系管理员!"),
    FORBIDDEN(403, "没有权限访问!"),
    NOT_FOUND(404, "资源不存在!"),

    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    BAD_GATEWAY(502, "网关错误!"),
    SERVER_BUSY(503, "服务器正忙，请稍后再试!");

    /**
     * 错误码
     */
    private Integer resultCode;

    /**
     * 错误描述
     */
    private String resultMsg;

    ExceptionEnum (Integer resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public Integer getCode () {
        return resultCode;
    }

    public String getMessage () {
        return resultMsg;
    }
}
