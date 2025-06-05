package com.lantz.lantzpicturebackend.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Project: lantz-picture-backend
 * <p>Powered by Lantz On 2025/4/30
 *
 * @author Lantz
 * @version 1.0
 * @Description LoginUserVO 脱敏后的登陆用户信息
 * @since 1.8
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 8530678706224578824L;
    /**
     * id
     */
    private Long id;

    /**
     * 账号
     */
    private String userAccount;

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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
