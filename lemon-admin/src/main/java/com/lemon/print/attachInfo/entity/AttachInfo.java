package com.lemon.print.attachInfo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author xubb
 * @since 2021-01-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AttachInfo对象", description="")
public class AttachInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "unitguid", type = IdType.ASSIGN_ID)
    private String unitguid;

    @ApiModelProperty(value = "附件名")
    private String attachName;

    @ApiModelProperty(value = "附件类型")
    private String attachType;

    @ApiModelProperty(value = "附件大小KB")
    private String attachLength;

    @ApiModelProperty(value = "附件存储文件夹路径")
    private String attachPath;

    @ApiModelProperty(value = "分组guid")
    private String groupGuid;

    @ApiModelProperty(value = "分组type")
    private String groupType;

    @ApiModelProperty(value = "文档页数")
    private String pageNum;

    @ApiModelProperty(value = "打印份数")
    private String copies;

    @ApiModelProperty(value = "是否删除,默认1未删除，2删除")
    private String isDelete;

    private Date uploadTime;

    private String uploadUser;


}
