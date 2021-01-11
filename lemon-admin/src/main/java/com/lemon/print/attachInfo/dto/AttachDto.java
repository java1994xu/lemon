package com.lemon.print.attachInfo.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author xubb
 * @Description
 * @create 2021-01-10 15:48
 */
public class AttachDto {
    @ApiModelProperty(value = "附件分组guid，可以获取该页面上传的附件")
    private String groupGuid;

    @ApiModelProperty(value = "附件的唯一id")
    private String attachGuid;


    public String getAttachGuid() {
        return attachGuid;
    }

    public void setAttachGuid(String attachGuid) {
        this.attachGuid = attachGuid;
    }

    public String getGroupGuid() {
        return groupGuid;
    }

    public void setGroupGuid(String groupGuid) {
        this.groupGuid = groupGuid;
    }
}
