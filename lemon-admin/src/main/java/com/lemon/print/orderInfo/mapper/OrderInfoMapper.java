package com.lemon.print.orderInfo.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lemon.print.orderInfo.dto.OrderInfoDTO;
import com.lemon.print.orderInfo.dto.QueryDTO;
import com.lemon.print.orderInfo.entity.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xubb
 * @since 2021-01-06
 */
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    List<OrderInfo> getPrintOrderList(@Param("storeId") String storeId);

    List<OrderInfo> getOrderList(Page page, @Param("query") OrderInfoDTO query);
}
