package com.cfl.oneself.utils.softAll;

import com.cfl.oneself.utils.entity.ProductEntity;

/**
 * @ClassName： ProductBiJiaoQi
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 17:30 2019/12/1
 * @Vesion 1.0
 */
public class ProductBiJiaoQi implements BiJiaoQi<ProductEntity> {
    @Override
    public boolean bijiao(ProductEntity o1, ProductEntity o2) {
        if(o2.getPrice()<o1.getPrice()){
            return true;
        }
        return false;
    }
}
