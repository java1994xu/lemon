package com.lemon.print.orderInfo.service.impl;

import com.lemon.print.orderInfo.entity.OrderInfo;
import com.lemon.print.orderInfo.mapper.OrderInfoMapper;
import com.lemon.print.orderInfo.service.OrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xubb
 * @since 2021-01-06
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Override
    public List<OrderInfo> getPrintOrderList(String storeId) {
        return  orderInfoMapper.getPrintOrderList(storeId);
    }
}
