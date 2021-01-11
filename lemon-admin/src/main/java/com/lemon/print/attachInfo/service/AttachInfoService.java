package com.lemon.print.attachInfo.service;

import com.lemon.print.attachInfo.entity.AttachInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xubb
 * @since 2021-01-06
 */
public interface AttachInfoService extends IService<AttachInfo> {

    /**
     * 通用保存文件
     * @param file
     * @param groupGUid
     * @param groupType
     * @throws IOException
     */
    void save(MultipartFile file, String groupGUid, String groupType) throws IOException;

    /**
     * 针对需要打印的文件进行保存
     * @param file
     * @param groupGUid
     * @param groupType
     * @throws IOException
     */
    Map<String ,Object> savePrintFile(MultipartFile file, String groupGUid, String groupType) throws IOException;

    /**
     * 导出、下载文件
     * @param file
     * @return
     */
    ResponseEntity<FileSystemResource> export(File file);
}
