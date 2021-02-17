package com.lemon.print.orderInfo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xubb
 * @Description
 * @create 2021-02-17 20:56
 */
@ApiModel("订单查询传输对象")
@Data
public class QueryDTO {

    @ApiModelProperty(value = "门店id")
    private String storeId;
}
