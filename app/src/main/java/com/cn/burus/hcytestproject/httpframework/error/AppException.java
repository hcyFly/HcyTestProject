package com.cn.burus.hcytestproject.httpframework.error;

/**
 * 全局异常类
 * Created by chengyou.huang on 2017/5/11.
 */

public class AppException extends Exception {

    /**
     * 状态码
     */
    public int statusCode;
    /**
     * 服务器返回信息
     */
    public String responseMessage;

    public ErrorType type;

    /**
     * 此处可以根据项目实际
     * 添加不同的异常字段
     * 和相应的构造方法
     */

    /**
     * 构造方法1
     *
     * @param status
     * @param responseMessage
     */
    public AppException(int status, String responseMessage) {
        super(responseMessage);
        this.type = ErrorType.SERVER;
        this.statusCode = status;
        this.responseMessage = responseMessage;
    }

    /**
     * 构造方法2
     *
     * @param type
     * @param detailMessage
     */
    public AppException(ErrorType type, String detailMessage) {
        super(detailMessage);
        this.type = type;
    }


    /**
     * 异常类型
     */
    public enum ErrorType {
        CANCEL, TIMEOUT, SERVER, JSON, IO, FILE_NOT_FOUND, UPLOAD, MANUAL
    }
}
