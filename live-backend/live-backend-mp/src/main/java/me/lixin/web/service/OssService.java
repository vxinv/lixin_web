package me.lixin.web.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {

    String uploadImageOrVideo(MultipartFile file) throws Exception;
}
