package com.lemon.print.orderInfo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lemon.print.attachInfo.entity.AttachInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author xubb
 * @Description
 * @create 2021-02-09 2:18
 */
@ApiModel("保存工单传输对象")
@Data
public class SaveOrderDTO {
    @ApiModelProperty(value = "主键id")
    private String unitguid;

    @ApiModelProperty(value = "门店id")
    private String storeId;

    @ApiModelProperty(value = "取件人姓名")
    private String owner;


    @ApiModelProperty(value = "选择了哪些附件打印，list")
    private List<AttachInfo> attachList;

    @ApiModelProperty(value = "总价")
    private String price;
}
