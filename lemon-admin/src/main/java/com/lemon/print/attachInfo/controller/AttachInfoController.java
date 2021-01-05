package com.lemon.print.attachInfo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lemon.common.vo.Result;
import com.lemon.print.attachInfo.entity.AttachInfo;
import com.lemon.print.attachInfo.service.AttachInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xubb
 * @since 2021-01-06
 */
@RestController
@RequestMapping("/attachInfo")
@Api(tags = "附件")
public class AttachInfoController {

    @Resource
    private AttachInfoService attachInfoService;


    @PostMapping("/listByGroupGuid")
    @ApiOperation(value = "listByGroupGuid", tags = "附件列表")
    public Result listByGroupGuid(@RequestParam String groupGuid) {
        QueryWrapper<AttachInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AttachInfo::getGroupGuid, groupGuid);
        List<AttachInfo> list = attachInfoService.list(queryWrapper);
        return Result.success(list);
    }

    @PostMapping("/uploadFile")
    @ApiOperation(value = "uploadFile", tags = "上传附件")
    public Result uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("groupGUid") String groupGUid, @RequestParam("groupType") String groupType) {

        try {
            attachInfoService.save(file, groupGUid, groupType);
            return Result.success();
        } catch (IOException e) {
            e.printStackTrace();
            return  Result.error("异常");
        }

    }
}
