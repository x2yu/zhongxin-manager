package com.zhongxin.manager.dto;

import lombok.Data;

import java.util.List;

@Data
public class InsertValue {
    Integer cate_id;
    String name;
    List<Integer> managers;
}
