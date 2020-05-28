package com.zhongxin.manager.service.impl;

import com.zhongxin.manager.entity.TblCategory;
import com.zhongxin.manager.mapper.TblApplicationMapper;
import com.zhongxin.manager.mapper.TblCategoryMapper;
import com.zhongxin.manager.service.ITblCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 应用类别表  服务实现类
 * </p>
 *
 * @author zhongxin
 * @since 2020-05-27
 */
@Service
public class TblCategoryServiceImpl extends ServiceImpl<TblCategoryMapper, TblCategory> implements ITblCategoryService {

    @Autowired
    TblCategoryMapper tblCategoryMapper;

    @Autowired
    TblApplicationMapper tblApplicationMapper;

    @Override
    public String getNameByID(Integer id) {
        return tblCategoryMapper.getNameByid(id);
    }

    @Override
    public Integer getCategorySum(Integer cate_id) {
        return tblApplicationMapper.getCategorySum(cate_id);
    }

    public List<TblCategory> getByPage(Integer firstIndex, Integer size) {
        return tblCategoryMapper.getByPage(firstIndex,size);
    }

    @Override
    public Boolean isExist(String cate_name) {
        Integer judge = tblCategoryMapper.isExist(cate_name);
        if(judge > 0)
            return true;
        else
            return false;
    }

    @Override
    public Integer insertCate(TblCategory tblCategory) {
        return tblCategoryMapper.insertCate(tblCategory);
    }

    @Override
    public Integer deleteCate(String cateName) {
        return tblCategoryMapper.deleteCate(cateName);
    }

    @Override
    public TblCategory getOldCate(String cateName) {
        return tblCategoryMapper.getOldCate(cateName);
    }

    @Override
    public Integer updateCate(TblCategory tblCategory) {
        return tblCategoryMapper.updateCate(tblCategory);
    }

    @Override
    public List<TblCategory> getByPageName(Integer firstIndex, Integer pageSize, String name) {
        return tblCategoryMapper.getByPageName(firstIndex,pageSize,name);
    }

    @Override
    public Integer countName(String name) {
        return tblCategoryMapper.countName(name);
    }
}
