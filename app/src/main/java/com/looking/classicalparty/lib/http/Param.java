package com.looking.classicalparty.lib.http;

import java.io.File;

/**
 * User: YangHai (1454025171@qq.com)
 * Date: 2016-10-19
 * Time: 10:50
 */
public class Param {
    String key;
    String value;
    File filevalue;

    public Param() {
    }

    public Param(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Param(String key, File filevalue) {
        this.key = key;
        this.filevalue = filevalue;
    }
}
