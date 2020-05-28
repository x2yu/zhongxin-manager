package com.zhongxin.manager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhongxin.manager.entity.TblAppManager;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhongxin.manager.entity.TblApplication;

import java.util.List;

/**
 * <p>
 * 应用开发者中间表  服务类
 * </p>
 *
 * @author zhongxin
 * @since 2020-05-27
 */
public interface ITblAppManagerService extends IService<TblAppManager> {

    /**
     * 根据应用id获取对应负责人id集合
     * */
    List<Integer> getManagerIdsByAppId(Integer appId);

    /**
     * 删除单个负责人
     * */
    void deleteSingleManager(TblAppManager appManager);


    /*
    * 根据描述搜索应用以及负责人数据集
    * */
    IPage<TblApplication> getAppManagerInfoByDes( String des, Page page);


    /*
     * 批量删除应用负责人信息
     * */
    void deleteAppManagerBatch(Integer[] ids);

}
