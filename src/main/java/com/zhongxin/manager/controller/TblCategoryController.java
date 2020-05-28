package com.zhongxin.manager.controller;


import com.zhongxin.manager.dto.Response;
import com.zhongxin.manager.dto.TblCategoryDto;
import com.zhongxin.manager.entity.TblCategory;
import com.zhongxin.manager.mapper.TblCategoryMapper;
import com.zhongxin.manager.service.impl.TblCategoryServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 应用类别表  前端控制器
 * </p>
 *
 * @author zhongxin
 * @since 2020-05-27
 */
@RestController
@CrossOrigin
@RequestMapping("/api/manager/tbl-category")
public class TblCategoryController {

    @Autowired
    TblCategoryServiceImpl tblCategoryService;

    @RequestMapping("/isExist")
    public Boolean isExist(String cateName){
        return tblCategoryService.isExist(cateName);
    }

    @RequestMapping("/insertCate")
    public Boolean insertCate(TblCategory tblCategory){
        Integer cate = tblCategoryService.insertCate(tblCategory);
        if(cate > 0)
            return true;
        else
            return false;

    }

    @RequestMapping("/updateCate")
    public Boolean updateCate(TblCategory tblCategory){
        System.out.println(tblCategory.getCateId()+" "+tblCategory.getCateName());
        Integer cate = tblCategoryService.updateCate(tblCategory);
        return cate > 0 ? true : false;
    }

    @RequestMapping("/deleteCate")
    public Boolean deleteCate(String cateName){
        Integer deleteCate = tblCategoryService.deleteCate(cateName);
        if(deleteCate > 0)
            return true;
        else
            return false;
    }

    @RequestMapping("/queryByPageName")
    public Response<TblCategoryDto> queryAllByPageName(String name,Integer pageIndex, Integer pageSize){
        System.out.println(name);
        List<TblCategory> categories = tblCategoryService.getByPageName((pageIndex-1)*pageSize,pageSize,name);
        List<TblCategoryDto> catagoryDtos = new ArrayList<>(categories.size());
        for (TblCategory catagory:categories
        ) {
            TblCategoryDto tblCategoryDto = new TblCategoryDto();
            tblCategoryDto.setCateId(catagory.getCateId());
            tblCategoryDto.setCateName(catagory.getCateName());
            Integer sum = tblCategoryService.getCategorySum(catagory.getCateId());
            tblCategoryDto.setSum(sum);
            catagoryDtos.add(tblCategoryDto);
        }
        Response<TblCategoryDto> response = new Response<>();
        response.setList(catagoryDtos);
        response.setPageSize(5);
        System.out.println(pageIndex+" "+pageSize);
        response.setTotalSize(tblCategoryService.countName(name));
        return response;
    }


    /**
     * 分页查询
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping("/queryByPage")
    public Response<TblCategoryDto> queryAllByPages(Integer pageIndex, Integer pageSize){
        System.out.println(pageIndex+" "+pageSize);
        List<TblCategory> categories = tblCategoryService.getByPage((pageIndex-1)*pageSize,pageSize);
        List<TblCategoryDto> catagoryDtos = new ArrayList<>(categories.size());
        for (TblCategory catagory:categories
        ) {
            TblCategoryDto tblCategoryDto = new TblCategoryDto();
            tblCategoryDto.setCateId(catagory.getCateId());
            tblCategoryDto.setCateName(catagory.getCateName());
            Integer sum = tblCategoryService.getCategorySum(catagory.getCateId());
            tblCategoryDto.setSum(sum);
            catagoryDtos.add(tblCategoryDto);
        }
        Response<TblCategoryDto> response = new Response<>();
        response.setList(catagoryDtos);
        response.setPageSize(5);
        System.out.println(pageIndex+" "+pageSize);
        response.setTotalSize(tblCategoryService.count());
        return response;
    }


}
