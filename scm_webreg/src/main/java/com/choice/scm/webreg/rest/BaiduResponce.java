package com.choice.scm.webreg.rest;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author : noven.zhen
 * @date : 2018-11-28
 * @email: zjm@choicesoft.com.cn
 */
@Data
@ToString
public class BaiduResponce implements Serializable {

    private int err_no;
    private String err_msg;
    private String corpus_no;
    private String sn;
    private List<String> result;


}
