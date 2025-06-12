package com.lantz.lantzpicturebackend.model.dto.user;

import com.lantz.lantzpicturebackend.exception.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest implements Serializable {

    private static final long serialVersionUID = -9177304111330026816L;

    /**
     * 用户 id
     */
    private Long id;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin
     */
    private String userRole;

}
