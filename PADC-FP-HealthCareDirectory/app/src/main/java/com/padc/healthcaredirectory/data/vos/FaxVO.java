package com.padc.healthcaredirectory.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Phyoe Khant on 9/20/2016.
 */
public class FaxVO {

    @SerializedName("fax-id")
    private long faxId;

    @SerializedName("fax-name")
    private String faxName;

    public long getFaxId() {
        return faxId;
    }

    public void setFaxId(int faxId) {
        this.faxId = faxId;
    }

    public String getFaxName() {
        return faxName;
    }

    public void setFaxName(String faxName) {
        this.faxName = faxName;
    }
}
