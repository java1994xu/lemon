package com.lemon.print.attachInfo.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.tokenizer.Word;
import com.aspose.words.Document;
import com.lemon.config.properties.LemonProperties;
import com.lemon.print.attachInfo.entity.AttachInfo;
import com.lemon.print.attachInfo.mapper.AttachInfoMapper;
import com.lemon.print.attachInfo.service.AttachInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import java.io.*;
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
    public void save(MultipartFile file, String groupGUid, String groupType) throws IOException {
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

        boolean save = this.save(attachInfo);
        String count = "0";
        try {
//            count = getWordPageCount(suffix, file.getInputStream());
            count = getPages(suffix, file.getInputStream());
        } catch (Exception e) {

            e.printStackTrace();
            count = "无法获取";
        }
        log.info(count + "");
        Map<String, Object> result = new HashMap<>();
        result.put("unitGuid", attachGuid);
        result.put("pageCount", count);
        return result;
    }

    public String getPages(String suffix, InputStream fis) throws Exception {

        Integer pageCount = 0;
        if (".doc".equals(suffix) || ".docx".equals(suffix)) {
            Document doc = new Document(fis);
            pageCount = doc.getPageCount();

        }else if (".ppt".equals(suffix) || ".pptx".equals(suffix)) {
            Document doc = new Document(fis);
            pageCount = doc.getPageCount();

        }

        return pageCount.toString();
    }


    public static String getWordPageCount(String suffix, InputStream fis) throws Exception {

        Integer pageCount = 0;
        if (".doc".equals(suffix)) {
            WordExtractor doc = new WordExtractor(fis);
            pageCount = doc.getSummaryInformation().getPageCount();
        } else if (".docx".equals(suffix)) {
            XWPFDocument docx = new XWPFDocument(fis);
            pageCount = docx.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();
        }


//aspose
//        FileReader fileReader = new FileReader(fullpath);
//        InputStream is = new ByteArrayInputStream(fileReader.readBytes());
//        Document doc = new Document(is);
//        int pageCount1 = doc.getPageCount();

        return pageCount.toString();
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
