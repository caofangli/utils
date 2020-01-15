package com.cfl.oneself.utils.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName： UserEntity
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 19:47 2019/11/30
 * @Vesion 1.0
 */
@Data
@Builder
@ToString
public class BaseEntity {

    /**
     * id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 年龄
     */
    private Integer age;

}
