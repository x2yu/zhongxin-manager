package com.zhongxin.manager.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhongxin.manager.ManagerApplication;
import com.zhongxin.manager.dto.ManagerApplicationDto;
import com.zhongxin.manager.dto.ManagerApplicationPage;
import com.zhongxin.manager.entity.TblAppManager;
import com.zhongxin.manager.entity.TblApplication;
import com.zhongxin.manager.entity.TblManager;
import com.zhongxin.manager.mapper.TblAppManagerMapper;
import com.zhongxin.manager.service.ITblAppManagerService;
import com.zhongxin.manager.service.ITblApplicationService;
import com.zhongxin.manager.service.ITblManagerService;
import com.zhongxin.manager.utils.Result;
import com.zhongxin.manager.utils.ResultUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 负责人信息表  前端控制器
 * </p>
 *
 * @author zhongxin
 * @since 2020-05-27
 */
@RestController
@RequestMapping("api/manager/")
public class TblManagerController {
    @Autowired
    ITblApplicationService applicationService;
    @Autowired
    ITblManagerService managerService;
    @Autowired
    ITblAppManagerService appManagerService;


    private static int PageSize = 10;

    @GetMapping("list/{page}")
    @ApiOperation("分页获取所有负责人信息")
    @ApiImplicitParam(name = "page",value = "页码",required = true,dataType = "Integer")
    public ManagerApplicationPage getManagerInfo(@PathVariable("page")Integer page){

        // 分页查询应用数据
        Page<TblApplication> appPage =new Page<>(page,PageSize);
        IPage<TblApplication> appIPage = applicationService.page(appPage);

        //填充单个传输对象数据载体
        List<ManagerApplicationDto>  mApplicationDtos =  initApplicationInfo(appIPage);

        // 封装初始化为分页数据载体 并且返回
        return  initManagerPageinfo(mApplicationDtos,appIPage);
    }

    @GetMapping("list")
    @ApiOperation("获取所有负责人信息")
    public List<TblManager> getManagers(){

        return managerService.list();
    }


    @PostMapping("add")
    @ApiOperation("添加某应用新的负责人")
    public Result addManager(@RequestBody TblAppManager appManager){
        try{
            // 储存新负责人
            appManagerService.save(appManager);
            return ResultUtil.success();
        }catch (Exception e){
            return ResultUtil.submitError();
        }
    }


    @DeleteMapping("singleDelete")
    @ApiOperation("删除应用中某一个负责人")
    public Result deleteSingleManager(@RequestBody TblAppManager appManager){
        try{
            appManagerService.deleteSingleManager(appManager);
            return ResultUtil.success();
        }catch (Exception e){
            return ResultUtil.submitError();
        }
    }

    /**
     * 初始化应用以及负责人数据集
     * */
    private List<ManagerApplicationDto> initApplicationInfo(IPage<TblApplication> appIPage){

        //应用以及负责人数据集载体
        List<ManagerApplicationDto> managerApplicationDtos = new ArrayList<>();

        // 应用数据集合
        List<TblApplication> applicationList = appIPage.getRecords();

        applicationList.forEach(app ->{
            ManagerApplicationDto mApplicationDto = new ManagerApplicationDto();
            mApplicationDto.setAppId(app.getAppId());
            mApplicationDto.setAppName(app.getAppName());
            mApplicationDto.setAppPic(app.getAppPic());
            mApplicationDto.setCreatedTime(app.getCreatedTime());
            mApplicationDto.setUpdateTime(app.getUpdatedTime());

            //根据应用id在数据库关系表中获取负责人ids集合
            List<Integer> managerIds = appManagerService.getManagerIdsByAppId(app.getAppId());
            //查询填充负责人数据集合
            mApplicationDto.setManagers(managerService.listByIds(managerIds));

            managerApplicationDtos.add(mApplicationDto);
        });

        return managerApplicationDtos;

    }

    private ManagerApplicationPage initManagerPageinfo(List<ManagerApplicationDto>  mApplicationDtos,IPage<TblApplication> appIPage){
        ManagerApplicationPage mApplicationPage = new ManagerApplicationPage();
        // 填装初始化数据
        mApplicationPage.setRecords(mApplicationDtos);
        mApplicationPage.setTotal(appIPage.getTotal());
        mApplicationPage.setCurrent(appIPage.getCurrent());
        mApplicationPage.setPages(appIPage.getPages());

        return mApplicationPage;
    }

}
