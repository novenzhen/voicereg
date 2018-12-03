package com.choice.scm.webreg.rest;

/**
 * @author : noven.zhen
 * @date : 2018-11-21
 * @email: zjm@choicesoft.com.cn
 */
public enum ResultCode {

    /**
     * 0:成功，400:失败，401：未授权，404：不存在，500：服务器内部错误
     */
    SUCCESS(0,"SUCCESS"),
    FAIL(1,"FAILURE"),
    UNAUTHORIZED(401,"UNAUTHORIZED"),
    NOT_FOUND(404,"NOT_FOUND"),
    INTERNAL_SERVER_ERROR(500,"SERVER_ERROR"),

    /**
     * 服务请求返回值
     */
    ILLIGAL_PARAM(3300,"输入参数不正确"),
    AUDIO_QUAITY_TOO_BAD(3301,"音频质量过差"),
    AUTHORIZE_FAILURE(3302,"鉴权失败"),
    BAIDU_VOICE_SERVICE_ERROR(3303,"百度语音服务错误"),
    QPS_OVERLOW(3304,"QPS超过上限"),
    PV_OVERLOW(3305,"PV超过上限"),
    AUDIO_REG_ERROR(3306,"语音识别错误"),
    AUDIO_TOO_LONG(3307,"音频过长"),
    AUDIO_DATA_ERROR(3308,"音频文件错误"),
    AUDIO_FILE_TOO_LARGE(3309,"音频文件过大"),
    RATE_NOT_SURPPORT(3310,"采样率不支持"),
    FORMAT_NOT_SURPPORT(3311,"文件格式不支持"),
    FILE_TRANSFER_ERROR(3322,"文件转换错误");


    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public static String getMessage(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return name;
    }

    public static Integer getCode(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
