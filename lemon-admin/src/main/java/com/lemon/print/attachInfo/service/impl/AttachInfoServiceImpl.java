package com.lemon.print.attachInfo.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.config.properties.LemonProperties;
import com.lemon.print.attachInfo.entity.AttachInfo;
import com.lemon.print.attachInfo.mapper.AttachInfoMapper;
import com.lemon.print.attachInfo.service.AttachInfoService;
import com.lemon.utils.WordUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    public String save(MultipartFile file, String groupGUid, String groupType) throws IOException {
        Date now = new Date();
        String time = DateUtil.format(now, "yyyyMM");
        String attachGuid = StrUtil.uuid();
        String rootPath = lemonProperties.getFileRootPath();
        String filename = file.getOriginalFilename();
        String fullPath = rootPath + File.separator + time + File.separator + groupGUid + File.separator + attachGuid + File.separator + filename;

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
        return attachGuid;
    }

    @Override
    public Map<String, Object> savePrintFile(MultipartFile file, String groupGUid, String groupType) throws IOException {
        Date now = new Date();
        String time = DateUtil.format(now, "yyyyMM");
        String attachGuid = StrUtil.uuid();
        String rootPath = lemonProperties.getFileRootPath();
        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));
        String fullPath = rootPath + File.separator + time + File.separator + groupGUid + File.separator + attachGuid + File.separator + filename;

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

        String count = "0";
        try {
            count = WordUtils.getFilePages(suffix, file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            count = "无法获取";
        }
        attachInfo.setPageNum(count);
        boolean save = this.save(attachInfo);
        Map<String, Object> result = new HashMap<>();
        result.put("unitGuid", attachGuid);
        result.put("pageCount", count);
        return result;
    }



    @Override
    public ResponseEntity<FileSystemResource> export(File file) throws UnsupportedEncodingException {
        if (file == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream")).body(new FileSystemResource(file));
    }

}
