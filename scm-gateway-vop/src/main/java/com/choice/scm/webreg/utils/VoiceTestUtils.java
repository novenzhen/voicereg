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


    /**
     * 组装命令
     * @param sourcePath
     * @param targetPath
     * @return
     */
    public String getConverCmd(String sourcePath,String targetPath){
        return ConvertParamConstants.PCM_SOURCE_PARAMS+sourcePath+ConvertParamConstants.PCM_TARGET_PARAMS+targetPath;

    }

    /**
     * 创建临时文件
     * @param voiceFileParam
     * @param file
     * @return
     */
    public File createFileFromMultiPartFile(VoiceFileParam voiceFileParam, MultipartFile file){
        File tmpSourceFille=new File(ConvertParamConstants.TMP_FILE_PATH+ UUID.randomUUID()+FILE_SEPERATOR+voiceFileParam.getFileFormat());
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), tmpSourceFille);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("create tmp file error,msg={},errmsg={}",e.getMessage(),e.getStackTrace());
        }
        return tmpSourceFille;
    }
}
