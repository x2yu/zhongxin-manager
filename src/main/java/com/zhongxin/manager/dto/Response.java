package com.zhongxin.manager.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Response<T> implements Serializable {
    List<T> list;
    Integer pageIndex;
    Integer pageSize;
    Integer totalSize;
    String name;
}
