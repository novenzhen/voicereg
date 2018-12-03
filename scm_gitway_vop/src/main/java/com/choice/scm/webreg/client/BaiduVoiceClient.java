package com.choice.scm.webreg.client;

import com.baidu.aip.speech.AipSpeech;
import com.choice.scm.webreg.param.VoiceClientParam;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : noven.zhen
 * @date : 2018-11-23
 * @email: zjm@choicesoft.com.cn
 */
public class BaiduVoiceClient {
    @Autowired
    VoiceClientParam voiceClientParam;

    private AipSpeech client;

    public AipSpeech getClient(){
        return client;
    }

    private BaiduVoiceClient(){
        client=new AipSpeech(voiceClientParam.getAppId(),voiceClientParam.getApikey(),voiceClientParam.getSecretKey());
    }

    private static class BaiduVoiceClientHolder{
        private static final BaiduVoiceClient INSTANCE=new BaiduVoiceClient();
    }

    public static BaiduVoiceClient getInstance() {
        return BaiduVoiceClientHolder.INSTANCE;
    }
}
