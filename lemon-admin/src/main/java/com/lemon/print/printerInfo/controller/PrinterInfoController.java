package com.lemon.print.printerInfo.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lemon.common.vo.Result;
import com.lemon.print.printerInfo.entity.PrinterInfo;
import com.lemon.print.printerInfo.service.PrinterInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xubb
 * @since 2021-01-06
 */
@RestController
@RequestMapping("/printerInfo")
@Api(tags = "打印机管理")
public class PrinterInfoController {

    @Resource
    private PrinterInfoService printerInfoService;

    @PostMapping("/page")
    @ApiOperation(value = "分页查询列表")
    public Result page(@RequestBody Page page, @RequestBody PrinterInfo printerInfo){
        return Result.success(printerInfoService.page(page,Wrappers.query(printerInfo)));
    }


    @PostMapping("/save")
    @ApiOperation(value = "新增打印机")
    public Result save(@RequestBody PrinterInfo printerInfo){
        return Result.success(printerInfoService.save(printerInfo));
    }


    @PostMapping("/update")
    @ApiOperation(value = "修改打印机")
    public Result update(@RequestBody PrinterInfo printerInfo){
        return Result.success(printerInfoService.updateById(printerInfo));
    }

}
