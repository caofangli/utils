package com.cfl.oneself.utils.softAll;

import com.cfl.oneself.utils.entity.ProductEntity;
import com.cfl.oneself.utils.entity.UserEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName： SoftMain
 * @Description： 比较器，已经有现成存在的工具了，collections.soft，和以下代码逻辑一样。
 * @Author： cfl
 * @Date: Created in 17:06 2019/12/1
 * @Vesion 1.0
 */
public class SoftMain {
    public static void main(String[] args){

        List users = Arrays.asList(
                UserEntity.builder().id(1).userName("你是").password("123").age(11).build(),
                UserEntity.builder().id(2).userName("小明").password("123456").age(19).build(),
                UserEntity.builder().id(3).userName("小李").password("456").age(18).build(),
                UserEntity.builder().id(4).userName("小王").password("12343").age(26).build(),
                UserEntity.builder().id(5).userName("小赵").password("789").age(22).build(),
                UserEntity.builder().id(6).userName("小曹").password("0123").age(15).build()
        );
        SoftUtil<UserEntity> user = new SoftUtil<>();
        List soft = user.soft(users, new UserBiJiaoQi());

        soft.stream().forEach(t -> {
            System.out.println(t.toString());
        });
        System.out.println("-----------------------------------------------------------------------------------------");

        List products = Arrays.asList(
                ProductEntity.builder().id(1).name("台灯").price(15.8).number(11).build(),
                ProductEntity.builder().id(1).name("书桌").price(88.8).number(10).build(),
                ProductEntity.builder().id(1).name("床").price(6.69).number(15).build(),
                ProductEntity.builder().id(1).name("被子").price(15.99).number(8).build(),
                ProductEntity.builder().id(1).name("音响").price(66.6).number(26).build(),
                ProductEntity.builder().id(1).name("电脑").price(77.6).number(22).build()
        );
        SoftUtil<ProductEntity> product = new SoftUtil<>();
        List productList = product.soft(products, new ProductBiJiaoQi());

        productList.stream().forEach(t -> {
            System.out.println(t.toString());
        });
        System.out.println("-----------------------------------------------------------------------------------------");

        List soft1 = product.soft(products, new BiJiaoQi<ProductEntity>() {
            @Override
            public boolean bijiao(ProductEntity o1, ProductEntity o2) {
                if (o1.getNumber() > o2.getNumber()) {
                    return true;
                }
                return false;
            }
        });
        soft1.stream().forEach(t -> {
            System.out.println(t.toString());
        });
        System.out.println("-----------------------------------------------------------------------------------------");

        Collections.sort(products, new Comparator<ProductEntity>() {
            @Override
            public int compare(ProductEntity o1, ProductEntity o2) {
                if(o1.getNumber()-o2.getNumber()>0){
                    return -1;
                }
                return 1;
            }
        });
        products.stream().forEach(t -> {
            System.out.println(t.toString());
        });
        System.out.println("-----------------------------------------------------------------------------------------");

        // 还有一种方式是实体类实现Comparable<UserEntity>接口，添加自定义的比较逻辑
        //@Override
        //public int compareTo(User other){
        //      if(this.getNumber>other.getNumber){
        //            return 1;
        //      }else if (this.getNumber=other.getNumber){
        //          return 0;
        //      }eles {
        //          return -1;
        //      }
        // }
        // 直接调用，Collections.sort(userList);
    }
}
