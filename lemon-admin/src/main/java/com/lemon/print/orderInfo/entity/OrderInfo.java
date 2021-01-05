package com.lemon.print.orderInfo.entity;

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
@ApiModel(value="OrderInfo对象", description="")
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "unitguid", type = IdType.ASSIGN_ID)
    private String unitguid;

    @ApiModelProperty(value = "门店id")
    private String storeId;

    @ApiModelProperty(value = "订单编号")
    private String orderNumber;

    @ApiModelProperty(value = "付款状态")
    private String orderPayStatus;

    @ApiModelProperty(value = "打印状态")
    private String orderPrintStatus;

    @ApiModelProperty(value = "取货状态")
    private String orderDeliveryStatus;

    @ApiModelProperty(value = "取货码")
    private String deliveryCode;

    @ApiModelProperty(value = "是否删除")
    private String isDelete;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    private String createUser;


}
