package com.qiaohx.encrypt.model;

import java.io.Serializable;
import java.util.Date;

public class Emd5 implements Serializable {
    private String md5Key;

    private String md5Value;

    private Date createDate;

    private static final long serialVersionUID = 1L;

    public String getMd5Key() {
        return md5Key;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key == null ? null : md5Key.trim();
    }

    public String getMd5Value() {
        return md5Value;
    }

    public void setMd5Value(String md5Value) {
        this.md5Value = md5Value == null ? null : md5Value.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}