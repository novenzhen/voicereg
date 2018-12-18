package com.choice.scm.webreg.utils;

import com.choice.scm.webreg.ffmpeg.ConvertParamConstants;
import com.choice.scm.webreg.ffmpeg.DefaultFFMPEGLocator;
import com.choice.scm.webreg.ffmpeg.FFMPEGExecutor;
import com.choice.scm.webreg.ffmpeg.FFMPEGLocator;
import com.choice.scm.webreg.param.VoiceFileParam;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author : noven.zhen
 * @date : 2018-12-18
 * @email: zjm@choicesoft.com.cn
 */
public class VoiceTestUtils {

    public static String returnTest(){
        return "Success";
    }

    private static final Logger log= LoggerFactory.getLogger(VoiceTestUtils.class);

    private static final FFMPEGLocator locator=new DefaultFFMPEGLocator();
    private static final FFMPEGExecutor ffmpeg=locator.createExecutor();
    private static final String FILE_SEPERATOR=".";



}
