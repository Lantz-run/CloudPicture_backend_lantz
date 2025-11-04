package com.lantz.lantzpicturebackend.model.dto.picture;

import lombok.Data;

import java.io.Serializable;

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
public class PictureUploadRequest implements Serializable {

    private static final long serialVersionUID = -5030117611211042602L;

    /**
     * id（用于修改）
     */
    private Long id;

    /**
     * 文件地址
     */
    private String fileUrl;

}
