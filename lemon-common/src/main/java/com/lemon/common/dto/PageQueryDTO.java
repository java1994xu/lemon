package com.lemon.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xubb
 * @Description
 * @create 2020-09-16 11:43
 */
public class PageQueryDTO {


    /**
     * 每页显示记录数
     */
    @ApiModelProperty(value = "每页显示记录数 ")
    private Integer pageSize;


    /**
     * 当前记录起始索引
     */
    @ApiModelProperty(value = "当前记录起始索引 ")
    private Integer pageNo;


    /**
     * 排序列
     */
    @ApiModelProperty(value = "排序列")
    private String orderByColumn;

    /**
     * 排序的方向desc或者asc
     */
    @ApiModelProperty(value = "排序的方向")
    private String isAsc = "asc";


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(String isAsc) {
        this.isAsc = isAsc;
    }
}
