package com.zhongxin.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhongxin.manager.entity.TblAppManager;
import com.zhongxin.manager.mapper.TblAppManagerMapper;
import com.zhongxin.manager.service.ITblAppManagerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 应用开发者中间表  服务实现类
 * </p>
 *
 * @author zhongxin
 * @since 2020-05-27
 */
@Service
public class TblAppManagerServiceImpl extends ServiceImpl<TblAppManagerMapper, TblAppManager> implements ITblAppManagerService {

    @Autowired
    TblAppManagerMapper appManagerMapper;


    @Override
    public List<Integer> getManagerIdsByAppId(Integer appId) {

        QueryWrapper<TblAppManager> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("app_id",appId);

        List<TblAppManager> appManagers = appManagerMapper.selectList(queryWrapper);

        List<Integer> ids =appManagers.stream().map(TblAppManager::getManagerId).collect(Collectors.toList());

        return ids;
    }

    @Override
    public void deleteSingleManager(TblAppManager appManager) {
        QueryWrapper<TblAppManager> wrapper = new QueryWrapper<>();
        wrapper.eq("app_id",appManager.getAppId())
                .eq("manager_id",appManager.getManagerId());

        appManagerMapper.delete(wrapper);
    }
}
