package com.lab.utils;

import com.lab.response.Page;

import java.util.List;

public class PageUtil {
    public static Page toPage (List list) {
        Page page=new Page<>();
        page.setTotal(list.size());
        page.setRecords(list);
        return page;
    }
}
