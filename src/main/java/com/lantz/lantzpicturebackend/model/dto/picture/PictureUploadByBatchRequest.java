package com.lantz.lantzpicturebackend.model.dto.picture;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Project: lantz-picture-backend
 * <p>Powered by Lantz On 2025/6/10
 *
 * @author Lantz
 * @version 1.0
 * @Description PictureUploadRequest
 * @since 1.8
 */
@Data
public class PictureUploadByBatchRequest implements Serializable {

    private static final long serialVersionUID = -5030117611211042602L;

    /**
     * 搜索关键词
     */
    private String searchText;

    /**
     * 抓取图片数目
     */
    private Integer count = 10;

    /**
     * 图片名字的后缀
     */
    private String namePrefix;

    /**
     * 分类
     */
    private String category;

    /**
     * 标签
     */
    private List<String> tags;

}
