package com.lab.response;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {
    private int total; // 一共有多少条记录
    private List<T> records; // 数据
}
