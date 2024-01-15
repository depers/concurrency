package com.bravedawn.concurrency.example.immutable;

import java.util.Date;

/**
 * @Author : fengx9
 * @Project : concurrency-master
 * @Date : Created in 2024-01-15 16:50
 */
public class ImmutableClass {

    /**
     * 不可变类的实现
     *
     * 防御性拷贝：一个可变类引用作为类成员变量出现在构造函数中，为了避免该变量的原始对象被修改，故要对这种可变的类成员变量进行防御性拷贝
     *
     *
     * 参考文章：
     *      https://blog.csdn.net/ToraNe/article/details/103003531
     *      https://blog.csdn.net/Gravitas/article/details/118523519
     */

    // 基本数据类型
    private final int primitiveTypeField;
    // 不可变的引用类型
    private final String immutableReferenceTypeField;
    // 可变的引用类型
    private final Date mutableReferenceTypeField;


    public ImmutableClass(int primitiveTypeField, String immutableReferenceTypeField, Date mutableReferenceTypeField) {
        this.primitiveTypeField = primitiveTypeField;
        this.immutableReferenceTypeField = immutableReferenceTypeField;
        this.mutableReferenceTypeField = new Date(mutableReferenceTypeField.getTime()); // 防御性拷贝
    }
}
