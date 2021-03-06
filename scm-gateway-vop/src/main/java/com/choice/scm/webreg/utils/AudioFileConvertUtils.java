package com.choice.scm.webreg.utils;


import com.choice.scm.webreg.ffmpeg.ConvertParamConstants;
import ws.schild.jave.ScmDefaultFFMPEGLocator;
import ws.schild.jave.ScmFFMPEGExecutor;
import ws.schild.jave.ScmFFMPEGLocator;
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
 * @date : 2018-11-26
 * @email: zjm@choicesoft.com.cn
 */
public  class AudioFileConvertUtils {
    private static final Logger log= LoggerFactory.getLogger(AudioFileConvertUtils.class);

    private static final String FILE_SEPERATOR=".";
    private static final ScmFFMPEGLocator locator=new ScmDefaultFFMPEGLocator();
    private static final ScmFFMPEGExecutor ffmpeg=locator.createExecutor();

    /**
     * 音频文件转化
     * @param sourceFile
     * @return
     */
    public static byte[] audioToPcm(File sourceFile){
        File targetFile=new File(ConvertParamConstants.TMP_FILE_PATH+sourceFile.getName()+".pcm");
        log.info("create target file ,filename is {}",targetFile.getAbsolutePath());
        String cmd=getConverCmd(sourceFile.getAbsolutePath(),targetFile.getAbsolutePath());
        byte[] result=null;
        try {
            log.info("convert file cmd is {}",cmd);
            ffmpeg.execute(cmd);
            InputStream inputStream=new FileInputStream(targetFile);
            result=IOUtils.toByteArray(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                FileUtils.forceDelete(sourceFile);
                FileUtils.forceDelete(targetFile);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("delete tmp file error,msg={},errmsg={}",e.getMessage(),e.getStackTrace());
            }
        }
        return result;
    }

    /**
     * 组装命令
     * @param sourcePath
     * @param targetPath
     * @return
     */
    private static String getConverCmd(String sourcePath,String targetPath){
        return ConvertParamConstants.PCM_SOURCE_PARAMS+sourcePath+ConvertParamConstants.PCM_TARGET_PARAMS+targetPath;

    }

    /**
     * 创建临时文件
     * @param voiceFileParam
     * @param file
     * @return
     */
    public static File createFileFromMultiPartFile(VoiceFileParam voiceFileParam,MultipartFile file){
        File tmpSourceFille=new File(ConvertParamConstants.TMP_FILE_PATH+UUID.randomUUID()+FILE_SEPERATOR+voiceFileParam.getFileFormat());
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), tmpSourceFille);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("create tmp file error,msg={},errmsg={}",e.getMessage(),e.getStackTrace());
        }
        return tmpSourceFille;
    }


    public static String returnTest(){
        return "Success";
    }

}
