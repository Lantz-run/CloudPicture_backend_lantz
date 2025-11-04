package com.lantz.lantzpicturebackend.model.enums;

import cn.hutool.core.util.ObjUtil;
import lombok.Getter;

/**
 * <p>Project: lantz-picture-backend
 * <p>Powered by Lantz On 2025/6/16
 *
 * @author Lantz
 * @version 1.0
 * @Description FileSuffixEnum
 * @since 1.8
 */
@Getter
public enum FileSuffixEnum {

    JPEG("jpeg", "jpeg"),
    JPG("jpg", "jpg"),
    PNG("png", "png"),
    WEBP("webp", "webp");

    private final String text;
    private final String value;


    FileSuffixEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value 枚举值的value
     * @return 枚举值
     */
    public static FileSuffixEnum getEnumByValue(String value){
        if (ObjUtil.isEmpty(value)) {
            return null;
        }
        for (FileSuffixEnum anEnum : FileSuffixEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}
