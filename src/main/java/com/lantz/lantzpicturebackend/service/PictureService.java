package com.lantz.lantzpicturebackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lantz.lantzpicturebackend.model.dto.picture.PictureQueryRequest;
import com.lantz.lantzpicturebackend.model.dto.picture.PictureUploadRequest;
import com.lantz.lantzpicturebackend.model.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lantz.lantzpicturebackend.model.entity.User;
import com.lantz.lantzpicturebackend.model.vo.PictureVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
* @author 少年的魂
* @description 针对表【picture(图片)】的数据库操作Service
* @createDate 2025-06-10 10:46:01
*/
public interface PictureService extends IService<Picture> {

    /**
     * 上传图片
     * @param multipartFile
     * @param pictureUploadRequest
     * @param loginUser
     * @return
     */
    PictureVO uploadPicture(MultipartFile multipartFile,
                            PictureUploadRequest pictureUploadRequest,
                            User loginUser);

    /**
     * 查询图片
     * @param pictureQueryRequest
     * @return
     */
    QueryWrapper<Picture> getQueryWrapper(PictureQueryRequest pictureQueryRequest);


    /**
     * 图片封装
     * @param picture
     * @param request
     * @return
     */
    PictureVO getPictureVO(Picture picture, HttpServletRequest request);

    /**
     * 分页获取图片列表
     * @param picturePage
     * @param request
     * @return
     */
    Page<PictureVO> getPictureVOPage(Page<Picture> picturePage, HttpServletRequest request);

    /**
     * 校验图片
     * @param picture
     */
    void validPicture(Picture picture);

}
