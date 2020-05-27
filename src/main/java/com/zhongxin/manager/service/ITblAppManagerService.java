package com.zhongxin.manager.service;

import com.zhongxin.manager.entity.TblAppManager;
import com.baomidou.mybatisplus.extension.service.IService;

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

    void deleteSingleManager(TblAppManager appManager);
}
