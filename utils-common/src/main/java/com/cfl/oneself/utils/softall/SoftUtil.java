package com.cfl.oneself.utils.softall;

import java.util.List;

/**
 * @ClassName： SoftUtil
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 16:50 2019/12/1
 * @Vesion 1.0
 */
public class SoftUtil<T> {

    public List<T> soft(List<T> list, BiJiaoQi bijiaoqi) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                boolean flag = bijiaoqi.bijiao(list.get(j), list.get(j + 1));
                if (flag) {
                    T tmp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tmp);
                }
            }
        }
        return list;
    }
}
