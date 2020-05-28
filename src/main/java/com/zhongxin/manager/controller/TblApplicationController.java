package com.zhongxin.manager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhongxin.manager.dto.Option;
import com.zhongxin.manager.dto.Response;
import com.zhongxin.manager.dto.TblApplicationDto;
import com.zhongxin.manager.dto.TblCategoryDto;
import com.zhongxin.manager.entity.TblApplication;
import com.zhongxin.manager.entity.TblCategory;
import com.zhongxin.manager.entity.TblManager;
import com.zhongxin.manager.service.impl.TblApplicationServiceImpl;
import com.zhongxin.manager.service.impl.TblCategoryServiceImpl;
import com.zhongxin.manager.service.impl.TblManagerServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 应用信息表  前端控制器
 * </p>
 *
 * @author zhongxin
 * @since 2020-05-27
 */
@RestController
@CrossOrigin
@RequestMapping("/api/manager/tbl-application")
public class TblApplicationController {

    @Autowired
    TblApplicationServiceImpl tblApplicationService;

    @Autowired
    TblCategoryServiceImpl tblCategoryService;

    @Autowired
    TblManagerServiceImpl tblManagerService;

    @RequestMapping("/queryByPageName")
    public Response<TblApplicationDto> queryByPageName(Integer pageIndex, Integer pageSize,String name){
        List<TblApplication> appList = tblApplicationService.getByPageName((pageIndex-1)*pageSize,pageSize,name);
        List<TblApplicationDto> applicationDtos = new ArrayList<>(appList.size());
        for(TblApplication app:appList){
            TblApplicationDto tblApplicationDto = new TblApplicationDto();
            tblApplicationDto.setApp_id(app.getAppId());
            tblApplicationDto.setApp_name(app.getAppName());
            tblApplicationDto.setApp_pic(app.getAppPic());
            tblApplicationDto.setDoc_id(app.getDocId().toString());
            String cata_name = tblCategoryService.getNameByID(app.getCateId());
            tblApplicationDto.setCate_name(cata_name);
            tblApplicationDto.setVersion(app.getVersion());
            applicationDtos.add(tblApplicationDto);
        }

        Response<TblApplicationDto> response = new Response<>();
        response.setList(applicationDtos);
        response.setTotalSize(tblApplicationService.countName(name));
        response.setPageSize(5);
        return response;
    }

    @RequestMapping("/getCateOptions")
    public List<Option> getCateOptions(){
        List<TblCategory> tblCategories = tblCategoryService.list();
        List<Option> options = new ArrayList<>(tblCategories.size());
        for(TblCategory tblCategory:tblCategories){
            Option option = new Option();
            option.setLabel(tblCategory.getCateName());
            option.setValue(tblCategory.getCateId());
            options.add(option);
        }
        return options;
    }

    @RequestMapping("/getManagerOptions")
    public List<Option> getManagerOptions(){
        List<TblManager> tblManagers = tblManagerService.list();
        List<Option> options = new ArrayList<>(tblManagers.size());
        for(TblManager tblManager:tblManagers){
            Option option = new Option();
            option.setLabel(tblManager.getManagerName());
            option.setValue(tblManager.getManagerId());
            options.add(option);
        }
        return options;
    }

    @RequestMapping("/insertApplication")
    public Boolean insertApplication(@Param("cate_id")Integer cate_id,@Param("manager")Integer manager,@Param("name") String name){
        System.out.println(cate_id+" "+manager+" "+name);
        return tblApplicationService.insertApplication(cate_id,manager,name);
    }
    @RequestMapping("/deleteApplication")
    public Boolean deleteApplication(Integer app_id){
        System.out.println(app_id);
        return tblApplicationService.deleteApplication(app_id);
    }

    @RequestMapping("/updateApplication")
    public Boolean updateApplication(Integer app_id,String app_name,Integer cate_id){
        TblApplication oldApp = tblApplicationService.getById(app_id);
        TblApplication newApp = new TblApplication();
        newApp.setAppId(oldApp.getAppId());
        newApp.setVersion(oldApp.getVersion());
        newApp.setDocId(oldApp.getDocId());
        newApp.setAppName(app_name);
        newApp.setCateId(cate_id);
        //tblApplicationService.updateById(newApp);
        return tblApplicationService.updateById(newApp);
    }


    @RequestMapping("/queryByPage")
    public Response<TblApplicationDto> queryAllByPages(Integer pageIndex, Integer pageSize){
        //封装dto List
        List<TblApplication> appList = tblApplicationService.getByPage((pageIndex-1)*pageSize,pageSize);
        List<TblApplicationDto> applicationDtos = new ArrayList<>(appList.size());
        for(TblApplication app:appList){
            TblApplicationDto tblApplicationDto = new TblApplicationDto();
            tblApplicationDto.setApp_id(app.getAppId());
            tblApplicationDto.setApp_name(app.getAppName());
            tblApplicationDto.setApp_pic(app.getAppPic());
            if(app.getDocId()!=null){
                tblApplicationDto.setDoc_id(app.getDocId().toString());
            }

            String cata_name = tblCategoryService.getNameByID(app.getCateId());
            tblApplicationDto.setCate_name(cata_name);
            tblApplicationDto.setVersion(app.getVersion());
            applicationDtos.add(tblApplicationDto);
        }

        Response<TblApplicationDto> response = new Response<>();
        response.setList(applicationDtos);
        response.setTotalSize(tblApplicationService.count());
        response.setPageSize(5);
        return response;
    }


    @RequestMapping("/queryAll")
    public List<TblApplicationDto> queryAll(){
        List<TblApplication> appList = tblApplicationService.list();
        List<TblApplicationDto> applicationDtos = new ArrayList<>(appList.size());
        for(TblApplication app:appList){
            TblApplicationDto tblApplicationDto = new TblApplicationDto();
            tblApplicationDto.setApp_id(app.getAppId());
            tblApplicationDto.setApp_name(app.getAppName());
            tblApplicationDto.setApp_pic(app.getAppPic());
            tblApplicationDto.setDoc_id(app.getDocId().toString());
            String cata_name = tblCategoryService.getNameByID(app.getCateId());
            tblApplicationDto.setCate_name(cata_name);
            tblApplicationDto.setVersion(app.getVersion());
            applicationDtos.add(tblApplicationDto);
        }
        return applicationDtos;
    }

    public void deleteApp(Integer id){

    }

}
