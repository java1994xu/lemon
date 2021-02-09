package com.lemon.print.orderInfo.dto;

import com.lemon.print.orderInfo.entity.OrderInfo;
import io.swagger.annotations.ApiModel;

/**
 * @author xubb
 * @Description
 * @create 2021-01-31 22:54
 */

@ApiModel(value="OrderInfoDTO对象", description="")
public class OrderInfoDTO extends OrderInfo {
    private Integer pageNo;

    private Integer pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
