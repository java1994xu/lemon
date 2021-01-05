package com.lemon.print.printerInfo.entity;

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
@ApiModel(value="PrinterInfo对象", description="")
public class PrinterInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "unitguid", type = IdType.ASSIGN_ID)
    private String unitguid;

    @ApiModelProperty(value = "机器名打印机名")
    private String machineName;

    @ApiModelProperty(value = "门店id")
    private String storeId;

    @ApiModelProperty(value = "机器状态")
    private String machineStatus;

    @ApiModelProperty(value = "墨盒含量")
    private String inkBox;

    @ApiModelProperty(value = "是否删除")
    private String isDelete;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    private String createUser;


}
