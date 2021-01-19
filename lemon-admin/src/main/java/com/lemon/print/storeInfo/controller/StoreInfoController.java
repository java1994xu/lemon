package com.lemon.print.storeInfo.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lemon.common.vo.Result;
import com.lemon.print.printerInfo.entity.PrinterInfo;
import com.lemon.print.printerInfo.service.PrinterInfoService;
import com.lemon.print.storeInfo.entity.StoreInfo;
import com.lemon.print.storeInfo.service.StoreInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xubb
 * @since 2021-01-06
 */
@RestController
@RequestMapping("//storeInfo")
@Api(tags = "分店管理")
public class StoreInfoController {

    @Resource
    private StoreInfoService storeInfoService;

    @PostMapping("/page")
    @ApiOperation(value = "分页查询列表")
    public Result page(@RequestBody Page page, @RequestBody StoreInfo storeInfo) {
        return Result.success(storeInfoService.page(page, Wrappers.query(storeInfo)));
    }


    @PostMapping("/save")
    @ApiOperation(value = "新增店铺")
    public Result save(@RequestBody StoreInfo storeInfo) {
        return Result.success(storeInfoService.save(storeInfo));
    }


    @PostMapping("/update")
    @ApiOperation(value = "修改店铺 信息")
    public Result update(@RequestBody StoreInfo storeInfo) {
        return Result.success(storeInfoService.updateById(storeInfo));
    }
}
