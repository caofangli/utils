package com.cfl.oneself.utils.learning.socktrpc;

import com.cfl.oneself.utils.learning.socktrpc.Product;
import com.cfl.oneself.utils.learning.socktrpc.ReadData;
import com.cfl.oneself.utils.learning.socktrpc.ReadDataUtil;

/**
 * @ClassName： rpcDemo
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 8:23 2019/12/10
 * @Vesion 1.0
 */
public class rpcDemo {
    
    public static void main(String[] args) throws Exception{
        ReadDataUtil util = new ReadDataUtil();
        ReadData readData = util.getInstance();
        Product p = readData.findProductById(3);
        System.out.println(p.toString());
    }
}
