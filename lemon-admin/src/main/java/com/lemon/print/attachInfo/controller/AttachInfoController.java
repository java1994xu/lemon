package com.lemon.print.attachInfo.controller;


import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lemon.common.vo.Result;
import com.lemon.print.attachInfo.dto.AttachDto;
import com.lemon.print.attachInfo.entity.AttachInfo;
import com.lemon.print.attachInfo.service.AttachInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

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
    @ApiOperation(value = "listByGroupGuid")
    public Result listByGroupGuid(@RequestBody AttachDto dto) {
        QueryWrapper<AttachInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AttachInfo::getGroupGuid, dto.getGroupGuid());
        List<AttachInfo> list = attachInfoService.list(queryWrapper);
        return Result.success(list);
    }

    @PostMapping("/uploadPrintFile")
    @ApiOperation(value = "uploadPrintFile")
    public Result uploadPrintFile(@RequestParam("file") MultipartFile file, @RequestParam("groupGUid") String groupGUid, @RequestParam("groupType") String groupType) {
        try {
            Map<String, Object> result = attachInfoService.savePrintFile(file, groupGUid, groupType);
            return Result.success(result);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("异常");
        }
    }

    @PostMapping("/uploadFile")
    @ApiOperation(value = "uploadFile")
    public Result uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("groupGUid") String groupGUid, @RequestParam("groupType") String groupType) {
        try {
            attachInfoService.save(file, groupGUid, groupType);
            return Result.success();
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("异常");
        }
    }


    @GetMapping("/download")
    @ApiOperation(value = "download")
    public ResponseEntity<FileSystemResource> downloadFile(@RequestParam("attachGuid") String attachGuid, HttpServletResponse response) throws UnsupportedEncodingException {

        AttachInfo attachInfo = attachInfoService.getById(attachGuid);
        String fullPath = attachInfo.getAttachPath();
        String fileName = attachInfo.getAttachName();
        File file = new File(fullPath);
        // 如果文件名存在，则进行下载
        if (file.exists()) {
            return attachInfoService.export(file);
        }
        return null;
    }


    @PostMapping("/deleteFile")
    @ApiOperation(value = "deleteFile")
    public Result deleteFileByGuid(@RequestBody AttachDto dto) {
        AttachInfo attachInfo = attachInfoService.getById(dto.getAttachGuid());
        boolean flag = FileUtil.del(attachInfo.getAttachPath());
        attachInfoService.removeById(attachInfo.getUnitguid());
        return Result.success();

    }
}
