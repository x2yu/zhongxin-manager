package com.zhongxin.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zhongxin.manager.entity.TblDoc;
import com.zhongxin.manager.mapper.TblDocMapper;
import com.zhongxin.manager.service.ITblDocService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 应用文档表  服务实现类
 * </p>
 *
 * @author zhongxin
 * @since 2020-05-27
 */
@Service
public class TblDocServiceImpl {

    @Autowired
    private TblDocMapper tblDocMapper;

    public List<TblDoc> list() {
        return tblDocMapper.selectAll();
    }

    public TblDoc selectBy_doc_id(int doc_id){
        return tblDocMapper.selectBy_doc_id(doc_id);
    }

    public List<TblDoc> selectBy_app_id(int app_id){
        return tblDocMapper.selectBy_app_id(app_id);
    }

    public boolean update(TblDoc tblDoc){
        return tblDocMapper.update(tblDoc);
    }

    public boolean deleteBy_doc_id(int doc_id){
        return tblDocMapper.deleteBy_doc_id(doc_id);
    }

    public boolean add(TblDoc tblDoc){
        return tblDocMapper.add(tblDoc);
    }
}
