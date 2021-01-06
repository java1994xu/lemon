package com.lemon.print.attachInfo.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.lemon.config.properties.LemonProperties;
import com.lemon.print.attachInfo.entity.AttachInfo;
import com.lemon.print.attachInfo.mapper.AttachInfoMapper;
import com.lemon.print.attachInfo.service.AttachInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xubb
 * @since 2021-01-06
 */
@Service
@Slf4j
public class AttachInfoServiceImpl extends ServiceImpl<AttachInfoMapper, AttachInfo> implements AttachInfoService {


    @Resource
    private LemonProperties lemonProperties;


    @Override
    public void save(MultipartFile file, String groupGUid, String groupType) throws IOException {
        Date now = new Date();
        String attachGuid = StrUtil.uuid();
        String rootPath = lemonProperties.getFileRootPath();
        String filename = file.getOriginalFilename();
        String fullPath = rootPath + File.separator + groupGUid + File.separator + attachGuid + File.separator + filename + File.separator;

        File f = FileUtil.writeFromStream(file.getInputStream(), fullPath);
        String fileType = FileUtil.getType(f);

        AttachInfo attachInfo = new AttachInfo();
        attachInfo.setUnitguid(attachGuid);
        attachInfo.setAttachName(filename);
        attachInfo.setAttachType(fileType);
        attachInfo.setAttachPath(fullPath);
        attachInfo.setGroupGuid(groupGUid);
        attachInfo.setGroupType(groupType);
        attachInfo.setUploadTime(now);

        this.save(attachInfo);
    }

    @Override
    public void savePrintFile(MultipartFile file, String groupGUid, String groupType) throws IOException {
        Date now = new Date();
        String attachGuid = StrUtil.uuid();
        String rootPath = lemonProperties.getFileRootPath();
        String filename = file.getOriginalFilename();
        String fullPath = rootPath + File.separator + groupGUid + File.separator + attachGuid + File.separator + filename + File.separator;

        File f = FileUtil.writeFromStream(file.getInputStream(), fullPath);
        String fileType = FileUtil.getType(f);

        AttachInfo attachInfo = new AttachInfo();
        attachInfo.setUnitguid(attachGuid);
        attachInfo.setAttachName(filename);
        attachInfo.setAttachType(fileType);
        attachInfo.setAttachPath(fullPath);
        attachInfo.setGroupGuid(groupGUid);
        attachInfo.setGroupType(groupType);
        attachInfo.setUploadTime(now);

        this.save(attachInfo);
    }

    @Override
    public ResponseEntity<FileSystemResource> export(File file) {
        if (file == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream")).body(new FileSystemResource(file));
    }

}
