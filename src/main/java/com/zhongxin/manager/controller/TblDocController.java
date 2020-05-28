package com.zhongxin.manager.controller;


import com.zhongxin.manager.entity.TblDoc;
import com.zhongxin.manager.service.ITblDocService;
import com.zhongxin.manager.service.impl.TblDocServiceImpl;
import com.zhongxin.manager.utils.Result;
import com.zhongxin.manager.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 应用文档表  前端控制器
 * </p>
 *
 * @author zhongxin
 * @since 2020-05-27
 */
@RestController
@RequestMapping("api/doc")
public class TblDocController {

    @Autowired
    TblDocServiceImpl tblDocServiceImpl;

    //查询所有文档
    @RequestMapping("/list")
    public List<TblDoc> list(){
        return tblDocServiceImpl.list();
    }

    //根据文档ID查询相应文档内容
    @RequestMapping("selectBy_doc_id/{doc_id}")
    public List<TblDoc> selectDocBy_doc_id(@PathVariable("doc_id") int doc_id){
        List<TblDoc> list=new ArrayList<>();
        TblDoc tblDoc=tblDocServiceImpl.selectBy_doc_id(doc_id);
        list.add(tblDoc);
        return list;
    }

    //根据应用ID查询相应文档内容
    @RequestMapping("selectBy_app_id/{app_id}")
    public List<TblDoc> selectDocBy_app_id(@PathVariable("app_id") int app_id){
        List<TblDoc> list= tblDocServiceImpl.selectBy_app_id(app_id);
        return list;
    }

    //根据文档ID更新对应文档内容
    @RequestMapping("/updateDoc")
    public Result updateDoc(@RequestBody TblDoc tblDoc){
            System.out.println(tblDoc.getDocId());
            System.out.println(tblDoc.getContent());
         if (!tblDocServiceImpl.update(tblDoc)){
             return ResultUtil.submitError();
         }else {
             return ResultUtil.success();
         }
    }

    //根据文档ID删除相应文档
    @RequestMapping("deleteBy_doc_id/{doc_id}")
    public Result deleteDoc(@PathVariable("doc_id") int doc_id){
        if (!tblDocServiceImpl.deleteBy_doc_id(doc_id)){
            return ResultUtil.submitError();
        }else {
            return ResultUtil.success();
        }
    }

    @RequestMapping("addDoc")
    public Result addDoc(@RequestBody TblDoc tblDoc){
        if (tblDocServiceImpl.add(tblDoc)){
            return ResultUtil.success();
        }else {
            return ResultUtil.submitError();
        }
    }

}
