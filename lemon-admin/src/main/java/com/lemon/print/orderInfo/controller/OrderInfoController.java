package com.lemon.print.orderInfo.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lemon.common.vo.Result;
import com.lemon.print.attachInfo.entity.AttachInfo;
import com.lemon.print.attachInfo.service.AttachInfoService;
import com.lemon.print.orderInfo.dto.OrderInfoDTO;
import com.lemon.print.orderInfo.dto.QueryDTO;
import com.lemon.print.orderInfo.dto.SaveOrderDTO;
import com.lemon.print.orderInfo.entity.OrderInfo;
import com.lemon.print.orderInfo.mapper.OrderInfoMapper;
import com.lemon.print.orderInfo.service.OrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author xubb
 * @since 2021-01-06
 */
@Api(tags = "订单信息")
@RestController
@RequestMapping("/orderInfo")
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;
    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Resource
    private AttachInfoService attachInfoService;

    @PostMapping("/page")
    @ApiOperation(value = "分页查询列表")
    public Result page(@RequestBody OrderInfoDTO dto) {
        Page<OrderInfo> page = new Page<>(dto.getPageNo(), dto.getPageSize());

        return Result.success(orderInfoMapper.getOrderList(page, dto));
    }


    @ApiOperation(value = "保存订单")
    @PostMapping("/save")
    public Result save(@RequestBody SaveOrderDTO dto) {

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUnitguid(dto.getUnitguid());
        orderInfo.setStoreId(dto.getStoreId());
        orderInfo.setOwner(dto.getOwner());
        orderInfo.setOrderPrintStatus("0");
        orderInfo.setOrderPayStatus("0");
        orderInfo.setCreateTime(new Date());
        List<AttachInfo> attachGuidList = dto.getAttachList();
        Double sum = 0d;
        for (AttachInfo attach : attachGuidList) {
//单面
            if ("1".equals(attach.getSides())) {
                sum = sum + 0.2 * Double.valueOf(attach.getPageNum()) * Double.valueOf(attach.getCopies());

            } else {
//                双面
                sum = sum + 0.3 * Double.valueOf(attach.getPageNum()) * Double.valueOf(attach.getCopies());
            }

            attach.setOrderGuid(dto.getUnitguid());
            attachInfoService.updateById(attach);

        }

        orderInfo.setSum(sum.toString());
        orderInfoService.save(orderInfo);
        return Result.success();
    }


    @PostMapping("/update")
    @ApiOperation(value = "修改订单 信息")
    public Result update(@RequestBody OrderInfo OrderInfo) {
        return Result.success(orderInfoService.updateById(OrderInfo));
    }


    @ApiOperation("获取待打印的工单列表")
    @PostMapping("/getPrintOrderList")
    public Result<List> getPrintOrderList(@RequestBody QueryDTO dto) {
        List<Map> resultList = new ArrayList();
        List<OrderInfo> printOrderList = orderInfoService.getPrintOrderList(dto.getStoreId());
        for (OrderInfo orderInfo : printOrderList) {
            Map<String, Object> orderMap = new HashMap<>();
            orderMap.put("orderId", orderInfo.getUnitguid());
            LambdaQueryWrapper<AttachInfo> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(AttachInfo::getOrderGuid, orderInfo.getUnitguid());
            List<AttachInfo> attachList = attachInfoService.list(queryWrapper);
            orderMap.put("attachList", attachList);
            resultList.add(orderMap);
        }
        return Result.success(resultList);
    }


}
