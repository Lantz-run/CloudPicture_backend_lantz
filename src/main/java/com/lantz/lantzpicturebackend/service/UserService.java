package com.lantz.lantzpicturebackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lantz.lantzpicturebackend.model.dto.user.UserQueryRequest;
import com.lantz.lantzpicturebackend.model.entity.User;
import com.lantz.lantzpicturebackend.model.vo.LoginUserVO;
import com.lantz.lantzpicturebackend.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author 少年的魂
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2025-04-29 23:23:12
*/
public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userName, String userPassword, String checkPassword);

    /**
     * 密码加盐加密
     * @param userPassword 用户密码
     * @return 返回加工后的密码
     */
    String getEncrytPassword(String userPassword);

    /**
     * 用户登录
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param request 请求
     * @return 返回脱敏后的用户信息
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取脱敏后已登录用户信息
     * @param user 用户
     * @return 脱敏后已登录用户信息
     */
    LoginUserVO getLoginUserVO(User user);

    /**
     * 获取当前登录用户
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 用户注销
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 用户脱敏
     * @param user
     * @return
     */
    UserVO getUserVO(User user);

    /**
     * 脱敏用户列表
     * @param userList
     * @return
     */
    List<UserVO> getUserVOList(List<User> userList);

    /**
     * 查询用户
     * @param userQueryRequest
     * @return
     */
    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);


    /**
     * 是否为管理员
     * @param user
     * @return
     */
    boolean isAdmin(User user);
}
