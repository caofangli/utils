package com.cfl.oneself.utils.entity.user;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName： ProductEntity
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 17:28 2019/12/1
 * @Vesion 1.0
 */
@Data
@Builder
@ToString
public class ProductEntity {

    /**
     * id
     */
    private Integer id;

    /**
     * 商品名
     */
    private String name;

    /**
     * 价格
     */
    private Double price;

    /**
     * 数量
     */
    private Integer number;
}
