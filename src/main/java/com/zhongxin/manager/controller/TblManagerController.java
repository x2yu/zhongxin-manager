package com.zhongxin.manager.controller;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhongxin.manager.dto.ManagerApplicationDto;
import com.zhongxin.manager.dto.ManagerApplicationPage;
import com.zhongxin.manager.dto.ExportData;
import com.zhongxin.manager.entity.TblAppManager;
import com.zhongxin.manager.entity.TblApplication;
import com.zhongxin.manager.entity.TblManager;
import com.zhongxin.manager.service.ITblAppManagerService;
import com.zhongxin.manager.service.ITblApplicationService;
import com.zhongxin.manager.service.ITblManagerService;
import com.zhongxin.manager.utils.Result;
import com.zhongxin.manager.utils.ResultUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<ManagerApplicationDto>  mApplicationDtos =  initApplicationInfo(appIPage.getRecords());

        // 封装初始化为分页数据载体 并且返回
        return  initManagerPageinfo(mApplicationDtos,appIPage);
    }

    @GetMapping("search/{page}")
    @ApiOperation("分页获取条件搜索应用及其负责人数据集")
    @ApiImplicitParam(name = "page",value = "页码",required = true,dataType = "Integer")
    public ManagerApplicationPage getManagerInfoByDes(@RequestParam("des") String des,
                                                      @PathVariable("page")Integer page){
        //分页模糊搜索应用名称集合
        Page<TblApplication> PageDes = new Page<>(page,PageSize);
        IPage<TblApplication> iPageDes = appManagerService.getAppManagerInfoByDes(des,PageDes);


        //填充单个传输对象数据载体
        List<ManagerApplicationDto> managerAppDtosDes = initApplicationInfo(iPageDes.getRecords());

        // 封装初始化为分页数据载体 并且返回
        return initManagerPageinfo(managerAppDtosDes,iPageDes);
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


    @DeleteMapping("deleteManager")
    @ApiOperation("删除应用中某一个负责人")
    public Result deleteSingleManager(@RequestBody TblAppManager appManager){
        try{
            appManagerService.deleteSingleManager(appManager);
            return ResultUtil.success();
        }catch (Exception e){
            return ResultUtil.submitError();
        }
    }

    @PostMapping("deleteBatch")
    @ApiOperation("批量删除应用以及负责人信息")
    public Result deleteAppManagerBatch(Integer[] ids){

        //批量删除应用负责人关系表中的数据
        try{
            appManagerService.deleteAppManagerBatch(ids);
            return ResultUtil.success();
        }catch (Exception e){
           return ResultUtil.submitError();
        }
    }


    @GetMapping("exportSingle")
    @ApiOperation("导出单行excel应用负责人数据")
    public void exportSingleAMInfo(HttpServletResponse response, @RequestParam Integer appId)throws IOException {

        // 查询应用数据
        List<Integer> ids = new ArrayList<>();
        ids.add(appId);

        exportData(response,ids);
    }


    @GetMapping("exportBatch")
    @ApiOperation("导出多行excel应用负责人数据")
    public void exportBatchAMInfo(HttpServletResponse response, @RequestParam Integer[] ids )throws IOException {

        // 查询应用数据
        List<Integer> idsList  = Stream.of(ids).collect(Collectors.toList());

        exportData(response,idsList);
    }


    /**
     * 初始化应用以及负责人数据集
     * */
    private List<ManagerApplicationDto> initApplicationInfo(List<TblApplication> applicationList){

        //应用以及负责人数据集载体
        List<ManagerApplicationDto> managerApplicationDtos = new ArrayList<>();

        // 应用数据集合
//        List<TblApplication> applicationList = appIPage.getRecords();

        applicationList.forEach(app ->{
            ManagerApplicationDto mApplicationDto = new ManagerApplicationDto();
            mApplicationDto.setAppId(app.getAppId());
            mApplicationDto.setAppName(app.getAppName());
            mApplicationDto.setAppPic(app.getAppPic());
            mApplicationDto.setCreatedTime(app.getCreatedTime());
            mApplicationDto.setUpdateTime(app.getUpdatedTime());

            //根据应用id在数据库关系表中获取负责人ids集合
            List<Integer> managerIds = appManagerService.getManagerIdsByAppId(app.getAppId());
            //查询填充负责人数据集合 ids集合不能为空
            if(!managerIds.isEmpty()){
                mApplicationDto.setManagers(managerService.listByIds(managerIds));
            }
            managerApplicationDtos.add(mApplicationDto);
        });

        return managerApplicationDtos;

    }

    // 初始化应用以及负责人数据集分页数据集
    private ManagerApplicationPage initManagerPageinfo(List<ManagerApplicationDto>  mApplicationDtos,IPage<TblApplication> appIPage){
        ManagerApplicationPage mApplicationPage = new ManagerApplicationPage();
        // 填装初始化数据
        mApplicationPage.setRecords(mApplicationDtos);
        mApplicationPage.setTotal(appIPage.getTotal());
        mApplicationPage.setCurrent(appIPage.getCurrent());
        mApplicationPage.setPages(appIPage.getPages());

        return mApplicationPage;
    }

    // 导出excel数据
    private void exportData(HttpServletResponse response,List<Integer> ids)throws IOException{
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode(LocalDateTime.now().toString(), "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), ExportData.class).sheet("模板").doWrite(data(ids));


    }

    private List<ExportData> data(List<Integer>ids) {

        List<TblApplication> applicationList = applicationService.listByIds(ids);
        List<ExportData> list = new ArrayList<ExportData>();

        applicationList.forEach( application ->{
            ExportData data = new ExportData();
            data.setAppId(application.getAppId());
            data.setAppName(application.getAppName());
            data.setAppPic(application.getAppPic());

            ZoneId zoneId = ZoneId.systemDefault();
            LocalDateTime localDateTime = application.getCreatedTime();
            ZonedDateTime zdt = localDateTime.atZone(zoneId);

            Date dateCreated = Date.from(zdt.toInstant());
            Date dateUpdated = Date.from(zdt.toInstant());

            data.setCreatedTime(dateCreated);
            data.setUpdateTime(dateUpdated);

            List<Integer> managerIds = appManagerService.getManagerIdsByAppId(application.getAppId());

            List<TblManager> managers = managerService.listByIds(managerIds);
            data.setManagers(managers.toString());
            list.add(data);
        });

        return list;
    }

}
