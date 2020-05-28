package com.zhongxin.manager.service;

import com.zhongxin.manager.entity.TblCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 * 应用类别表  服务类
 * </p>
 *
 * @author zhongxin
 * @since 2020-05-27
 */
public interface ITblCategoryService extends IService<TblCategory> {

    String getNameByID(Integer id);
    Integer getCategorySum(Integer cate_id);
    List<TblCategory> getByPage(Integer firstIndex, Integer size);
    Boolean isExist(String cate_name);
    Integer insertCate(TblCategory tblCategory);
    Integer deleteCate(String cateName);
    TblCategory getOldCate(String cateName);
    Integer updateCate(TblCategory tblCategory);

    List<TblCategory> getByPageName(Integer firstIndex, Integer pageSize, String name);

    Integer countName(String name);
}
