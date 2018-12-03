package com.choice.scm.webreg.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : noven.zhen
 * @date : 2018-11-23
 * @email: zjm@choicesoft.com.cn
 */
@Data
public class VoiceFileParam implements Serializable {

    /**
     * 文件格式
     */
    private String fileFormat;
    /**
     * 文件采样率
     */
    private int fileRate;
    /**
     *
     */
    private byte[] content;

}
