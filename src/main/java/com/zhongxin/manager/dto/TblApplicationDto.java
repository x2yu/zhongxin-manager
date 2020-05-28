package com.zhongxin.manager.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TblApplicationDto implements Serializable {

    private static final long serialVersionUID = 1L;

    Integer app_id;
    String app_name;
    String app_pic;
    String cate_name;
    String doc_id;
    String version;
}
