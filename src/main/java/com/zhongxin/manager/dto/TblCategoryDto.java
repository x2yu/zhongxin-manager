package com.zhongxin.manager.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class TblCategoryDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 类别id
     */
    private Integer cateId;

    /**
     * 类别名称
     */
    private String cateName;


    /*
     * 类别中书籍数目
     */
    private Integer sum;

}
