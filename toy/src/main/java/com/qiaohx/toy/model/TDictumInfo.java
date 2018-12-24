package com.qiaohx.toy.model;

import java.io.Serializable;
import java.util.Date;

public class TDictumInfo implements Serializable {
    private String id;

    private Date dictumDate;

    private String title;

    private String author;

    private String content;

    private String dictumType;

    private String country;

    private Date createDate;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getDictumDate() {
        return dictumDate;
    }

    public void setDictumDate(Date dictumDate) {
        this.dictumDate = dictumDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getDictumType() {
        return dictumType;
    }

    public void setDictumType(String dictumType) {
        this.dictumType = dictumType == null ? null : dictumType.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}