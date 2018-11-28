package com.choice.scm.webreg.ffmpeg;

/**
 * @author : noven.zhen
 * @date : 2018-11-27
 * @email: zjm@choicesoft.com.cn
 */
public class ConvertParamConstants {

    public static final String PCM_SOURCE_PARAMS=" -y  -i ";
    public static final String PCM_TARGET_PARAMS="  -acodec pcm_s16le -f s16le -ac 1 -ar 16000 ";


    public static final String TMP_FILE_PATH=System.getProperty("user.dir") + "/tmp/";
}
