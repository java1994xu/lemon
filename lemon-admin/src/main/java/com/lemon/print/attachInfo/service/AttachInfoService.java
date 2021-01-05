package com.lemon.print.attachInfo.service;

import com.lemon.print.attachInfo.entity.AttachInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xubb
 * @since 2021-01-06
 */
public interface AttachInfoService extends IService<AttachInfo> {

    void save(MultipartFile file, String groupGUid, String groupType) throws IOException;
}
