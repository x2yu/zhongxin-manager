package com.zhongxin.manager.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author: x2yu
 * @Date: 2020/5/27 14:19
 * @Describe：应用负责人分页数据封装实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ManagerApplicationPage {
    /**
     * 分页数据集
     */
    private List<ManagerApplicationDto> records;


    /**
     * 书籍总共有多少
     */
    private Long total;

    /**
     * 当前页
     */
    private Long current;


    /**
     * 总页数
     */
    private Long pages;
}
