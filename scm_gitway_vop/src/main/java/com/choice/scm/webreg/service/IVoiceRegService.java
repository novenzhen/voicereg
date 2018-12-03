package com.choice.scm.webreg.service;

import com.choice.scm.webreg.param.VoiceFileParam;
import com.choice.scm.webreg.rest.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : noven.zhen
 * @date : 2018-11-23
 * @email: zjm@choicesoft.com.cn
 */
public interface IVoiceRegService {
    Result voiceReg(VoiceFileParam voiceFileParam, MultipartFile file);
}
