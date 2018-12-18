package com.choice.scm.webreg.service.impl;

import com.baidu.aip.speech.AipSpeech;
import com.choice.scm.webreg.param.VoiceClientParam;
import com.choice.scm.webreg.param.VoiceFileParam;
import com.choice.scm.webreg.rest.BaiduResponce;
import com.choice.scm.webreg.rest.Result;
import com.choice.scm.webreg.rest.ResultCode;
import com.choice.scm.webreg.service.IVoiceRegService;
import com.choice.scm.webreg.utils.AudioFileConvertUtils;
import com.choice.scm.webreg.utils.VoiceTestUtils;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * @author : noven.zhen
 * @date : 2018-11-23
 * @email: zjm@choicesoft.com.cn
 */
@Service
public class VoiceRegServiceImpl implements IVoiceRegService {
    private static final Logger log= LoggerFactory.getLogger(VoiceRegServiceImpl.class);
    @Autowired
    VoiceClientParam clientParam;
    private final static String PCM="pcm";
    private final static int FILE_RATE=16000;
    private final static int DEV_PID=1536;
    private VoiceTestUtils voiceTestUtils=new VoiceTestUtils();


    /**
     * 完成文件转化及服务请求
     * @param voiceFileParam
     * @param file
     * @return
     */
    @Override
    public Result voiceReg(VoiceFileParam voiceFileParam, MultipartFile file) {

        byte[] content= voiceTestUtils.audioToPcm(voiceTestUtils.createFileFromMultiPartFile(voiceFileParam,file));
        if(ObjectUtils.isEmpty(content)){
            return Result.failure(ResultCode.FILE_TRANSFER_ERROR);
        }
        HashMap<String,Object> options= new HashMap<>();
        options.put("dev_pid",DEV_PID);
        return getResultFromBaiduAi(content,options);
    }

    /**
     * 判断请求是否成功
     * @param baiduResponce
     * @return
     */
    private boolean isSuccess(BaiduResponce baiduResponce){
        return baiduResponce.getErr_no()==0;
    }

    /**
     * 请求百度语音识别
     * @param content
     * @param options
     * @return
     */
    private Result getResultFromBaiduAi(byte[] content,HashMap<String,Object> options){
        AipSpeech client = new AipSpeech(clientParam.getAppId(), clientParam.getApikey(), clientParam.getSecretKey());
        BaiduResponce responce= new Gson().fromJson(client.asr(content,PCM,FILE_RATE,options).toString(),BaiduResponce.class);
        if(isSuccess(responce)){
           return Result.success(responce.getResult().get(0));
        }

        return Result.failure(responce.getErr_no(),responce.getErr_msg());
    }
}
