package com.lemon.print.orderInfo.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lemon.common.vo.Result;
import com.lemon.print.attachInfo.entity.AttachInfo;
import com.lemon.print.attachInfo.service.AttachInfoService;
import com.lemon.print.orderInfo.dto.OrderInfoDTO;
import com.lemon.print.orderInfo.dto.SaveOrderDTO;
import com.lemon.print.orderInfo.entity.OrderInfo;
import com.lemon.print.orderInfo.service.OrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    private AttachInfoService attachInfoService;

    @PostMapping("/page")
    @ApiOperation(value = "分页查询列表")
    public Result page(@RequestBody OrderInfoDTO dto) {
        Page<OrderInfo> page = new Page<>(dto.getPageNo(), dto.getPageSize());
        OrderInfo info = new OrderInfo();
        BeanUtil.copyProperties(dto, info);
        return Result.success(orderInfoService.page(page));
    }


    @ApiOperation(value = "保存订单")
    @PostMapping("/save")
    public Result save(@RequestBody SaveOrderDTO dto) {

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUnitguid(dto.getUnitguid());
        orderInfo.setStoreId(dto.getStoreId());
        List<String> attachGuidList = dto.getAttachGuidList();
        for (String s : attachGuidList) {
            AttachInfo attach = attachInfoService.getById(s);
//TODO 计算金额，生成付款码，付款成功后，生成取件码
        }
        return Result.success(orderInfoService.save(orderInfo));
    }


    @PostMapping("/update")
    @ApiOperation(value = "修改订单 信息")
    public Result update(@RequestBody OrderInfo OrderInfo) {
        return Result.success(orderInfoService.updateById(OrderInfo));
    }
}
