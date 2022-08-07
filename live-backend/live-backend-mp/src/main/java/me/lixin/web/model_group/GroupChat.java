package me.lixin.web.model_group;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class GroupChat implements Serializable {
    @ApiModelProperty(value = "群聊id")
    private Long gcId;

    @ApiModelProperty(value = "二维码图片url")
    private String gcUrl;

    @ApiModelProperty(value = "二维码的解析字段")
    private String gcEncodeChar;

    @ApiModelProperty(value = "群聊二维码更新时间")
    private Date gcUpdateTime;

    @ApiModelProperty(value = "用户id-关联用户的id")
    private Integer gcUserId;

    @ApiModelProperty(value = "主题id-关联主题")
    private Integer gcTopicId;

    @ApiModelProperty(value = "地域id-关联地域")
    private Integer gcLocalId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getGcId() {
        return gcId;
    }

    public void setGcId(Long gcId) {
        this.gcId = gcId;
    }

    public String getGcUrl() {
        return gcUrl;
    }

    public void setGcUrl(String gcUrl) {
        this.gcUrl = gcUrl;
    }

    public String getGcEncodeChar() {
        return gcEncodeChar;
    }

    public void setGcEncodeChar(String gcEncodeChar) {
        this.gcEncodeChar = gcEncodeChar;
    }

    public Date getGcUpdateTime() {
        return gcUpdateTime;
    }

    public void setGcUpdateTime(Date gcUpdateTime) {
        this.gcUpdateTime = gcUpdateTime;
    }

    public Integer getGcUserId() {
        return gcUserId;
    }

    public void setGcUserId(Integer gcUserId) {
        this.gcUserId = gcUserId;
    }

    public Integer getGcTopicId() {
        return gcTopicId;
    }

    public void setGcTopicId(Integer gcTopicId) {
        this.gcTopicId = gcTopicId;
    }

    public Integer getGcLocalId() {
        return gcLocalId;
    }

    public void setGcLocalId(Integer gcLocalId) {
        this.gcLocalId = gcLocalId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}