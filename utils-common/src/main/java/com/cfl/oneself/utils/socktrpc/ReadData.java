package com.cfl.oneself.utils.socktrpc;

import java.util.List;

/**
 * @ClassName： ReadData
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 9:03 2019/12/9
 * @Vesion 1.0
 */
public interface ReadData {

    Product findProductById(int id) throws Exception;

    List<Product> findProductByName(String keyword) throws Exception;
}
