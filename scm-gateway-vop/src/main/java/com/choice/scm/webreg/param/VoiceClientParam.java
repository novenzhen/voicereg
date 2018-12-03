package com.choice.scm.webreg.param;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author : noven.zhen
 * @date : 2018-11-23
 * @email: zjm@choicesoft.com.cn
 */
@Component
@Data
public class VoiceClientParam implements Serializable {
    @Value("${voice.baidu.appId}")
    private String appId;
    @Value("${voice.baidu.apiKey}")
    private String apikey;
    @Value("${voice.baidu.secretKey}")
    private String secretKey;
}
