package com.choice.scm.webreg.api;

import com.choice.scm.webreg.param.VoiceFileParam;
import com.choice.scm.webreg.rest.Result;
import com.choice.scm.webreg.service.IVoiceRegService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author : noven.zhen
 * @date : 2018-11-23
 * @email: zjm@choicesoft.com.cn
 */
@RestController
@RequestMapping(value = "/api/v0.1")
public class VoiceRegApi {
    @Autowired
    IVoiceRegService iVoiceRegService;


    @ApiOperation(value = "语音识别服务请求接口", notes = "上传音频文件，请求语音识别")
    @RequestMapping(value = "/voicereg",method = RequestMethod.POST)
    public Result voiceReg(VoiceFileParam voiceFileParam, @RequestParam("file") MultipartFile file){
        return iVoiceRegService.voiceReg(voiceFileParam,file);
    }

}
