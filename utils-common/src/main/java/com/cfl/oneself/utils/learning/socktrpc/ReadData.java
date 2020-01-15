package com.cfl.oneself.utils.learning.socktrpc;

import com.cfl.oneself.utils.learning.socktrpc.Product;

import java.util.List;

/**
 * @ClassName： ReadData
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 9:03 2019/12/9
 * @Vesion 1.0
 */
public interface ReadData {

    com.cfl.oneself.utils.learning.socktrpc.Product findProductById(int id) throws Exception;

    List<Product> findProductByName(String keyword) throws Exception;
}
