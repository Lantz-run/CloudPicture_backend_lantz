package com.lantz.lantzpicturebackend.controller;

import com.lantz.lantzpicturebackend.annotation.AuthCheck;
import com.lantz.lantzpicturebackend.common.BaseResponse;
import com.lantz.lantzpicturebackend.common.ResultUtils;
import com.lantz.lantzpicturebackend.config.CosClientConfig;
import com.lantz.lantzpicturebackend.constant.UserConstant;
import com.lantz.lantzpicturebackend.exception.BusinessException;
import com.lantz.lantzpicturebackend.exception.ErrorCode;
import com.lantz.lantzpicturebackend.manager.CosManager;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.COSObjectInputStream;
import com.qcloud.cos.utils.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * <p>Project: lantz-picture-backend
 * <p>Powered by Lantz On 2025/6/8
 *
 * @author Lantz
 * @version 1.0
 * @Description FileController
 * @since 1.8
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Resource
    private CosClientConfig cosClientConfig;

    @Resource
    private CosManager cosManager;

    /**
     * 测试文件上传
     * @param multipartFile
     * @return
     */
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @PostMapping("/test/upload")
    public BaseResponse<String> testUploadFile(@RequestPart("file")MultipartFile multipartFile) {
        // 文件目录
        String filename = multipartFile.getOriginalFilename();
        String filePath = String.format("/test/%s", filename);
        File file = null;

        try {
            // 上传文件
            file = File.createTempFile(filePath, null);
            multipartFile.transferTo(file);
            cosManager.putObject(filePath, file);
            // 返回访问地址
            return ResultUtils.success(filePath);

        } catch (Exception e) {
            log.error("file upload error, filePath = {}", filePath, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "上传错误");
        } finally {
            if (file != null) {
                // 删除临时文件
                boolean deleteFile = file.delete();
                if (!deleteFile) {
                    log.error("file delete error, filePath = {}", filePath);
                }
            }
        }
    }


    /**
     * 测试文件下载
     * @param filePath 文件路径
     * @param response 响应对象
     * @return
     */
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @GetMapping("/test/download")
    public void testDownloadFile(String filePath, HttpServletResponse response) throws IOException{
        COSObjectInputStream cosObjectInput = null;
        COSObject cosObject = cosManager.getObject(filePath);
        cosObjectInput = cosObject.getObjectContent();
        try {
            // 处理下载到的流
            byte[] bytes = IOUtils.toByteArray(cosObjectInput);
            // 设置响应头
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename="+filePath);
            // 写入响应
            response.getOutputStream().write(bytes);
            response.getOutputStream().flush();
        } catch (Exception e) {
            log.error("file download error, filePath = {}", filePath, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "文件下载失败");
        } finally {
            if (cosObjectInput != null) {
                cosObjectInput.close();
            }
        }
    }
}
