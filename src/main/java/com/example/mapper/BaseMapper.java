package com.example.mapper;

import java.util.List;

/**
 * @author JYuan
 * @create 2021-08-19 11:01
 */
public interface BaseMapper<T> {

    /**查所有
     * @return
     */
    default List<T> findAll() {
        return null;
    }

    /**
     * 差一个
     * @param obj
     * @return
     */
    default T findOne(Object obj){
        return null;
    }

    /**
     * 更新
     * @param obj
     * @return 0表示不成功，1表示成功
     */
    default int update(Object obj) {
        return 0;
    }
}
