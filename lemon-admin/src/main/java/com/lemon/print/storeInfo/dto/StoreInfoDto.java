package com.lemon.print.storeInfo.dto;

import com.lemon.print.storeInfo.entity.StoreInfo;

/**
 * @author xubb
 * @Description
 * @create 2021-01-28 23:58
 */

public class StoreInfoDto extends StoreInfo {
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
