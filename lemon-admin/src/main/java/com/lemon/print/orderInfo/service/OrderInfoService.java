package com.lemon.print.orderInfo.service;

import com.lemon.print.orderInfo.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xubb
 * @since 2021-01-06
 */
public interface OrderInfoService extends IService<OrderInfo> {

    List<OrderInfo> getPrintOrderList(String storeId);

}
