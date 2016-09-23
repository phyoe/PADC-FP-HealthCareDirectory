package com.padc.healthcaredirectory.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Phyoe Khant on 9/20/2016.
 */
public class TagVO {

    @SerializedName("tag-id")
    private int tagId;

    @SerializedName("tag-name")
    private String tagName;

    @SerializedName("tag-name-mm")
    private String tagNameMM;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagNameMM() {
        return tagNameMM;
    }

    public void setTagNameMM(String tagNameMM) {
        this.tagNameMM = tagNameMM;
    }
}
