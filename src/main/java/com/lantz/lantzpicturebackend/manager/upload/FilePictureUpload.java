package com.lantz.lantzpicturebackend.manager.upload;

import cn.hutool.core.io.FileUtil;
import com.lantz.lantzpicturebackend.exception.ErrorCode;
import com.lantz.lantzpicturebackend.exception.ThrowUtils;
import com.lantz.lantzpicturebackend.model.enums.FileSuffixEnum;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Project: lantz-picture-backend
 * <p>Powered by Lantz On 2025/11/4
 *
 * @author Lantz
 * @version 1.0
 * @Description FilePictureUpload 图片文件上传
 * @since 1.8
 */
@Service
public class FilePictureUpload extends PictureUploadTemplate{
    @Override
    protected void validPicture(Object inputSource) {
        MultipartFile multipartFile = (MultipartFile) inputSource;
        ThrowUtils.throwIf(multipartFile == null, ErrorCode.PARAMS_ERROR, "文件不能为空");
        // 1. 校验文件大小
        long fileSize = multipartFile.getSize();
        final long FOUR_M = 4 * 1024 * 1024L;
        ThrowUtils.throwIf(fileSize > FOUR_M, ErrorCode.PARAMS_ERROR, "文件大小不能超过 4M");
        // 2. 校验文件后缀
        String fileSuffix = FileUtil.getSuffix(multipartFile.getOriginalFilename());
        // 允许上传的文件后缀
        final List<String> ALLOW_FORMAT_LIST = Arrays.asList(
                FileSuffixEnum.JPEG.getValue(),
                FileSuffixEnum.JPG.getValue(),
                FileSuffixEnum.PNG.getValue(),
                FileSuffixEnum.WEBP.getValue());
        ThrowUtils.throwIf(!ALLOW_FORMAT_LIST.contains(fileSuffix), ErrorCode.PARAMS_ERROR, "文件类型错误");
    }

    @Override
    protected String getOriginFilename(Object inputSource) {
        MultipartFile multipartFile = (MultipartFile) inputSource;
        return multipartFile.getOriginalFilename();
    }

    @Override
    protected void processFile(Object inputSource, File file) throws IOException {
        MultipartFile multipartFile = (MultipartFile) inputSource;
        multipartFile.transferTo(file);
    }
}
