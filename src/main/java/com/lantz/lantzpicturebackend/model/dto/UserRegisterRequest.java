package com.lantz.lantzpicturebackend.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Project: lantz-picture-backend
 * <p>Powered by Lantz On 2025/4/30
 *
 * @author Lantz
 * @version 1.0
 * @Description UserRegisterRequest
 * @since 1.8
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = -9177304111330026816L;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 确认密码
     */
    private String checkPassword;

    /**
     * 用户昵称
     */
    private String userName;


}
