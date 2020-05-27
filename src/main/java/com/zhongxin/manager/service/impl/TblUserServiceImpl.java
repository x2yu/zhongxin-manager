package com.zhongxin.manager.service.impl;

import com.zhongxin.manager.entity.TblUser;
import com.zhongxin.manager.mapper.TblUserMapper;
import com.zhongxin.manager.service.ITblUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表  服务实现类
 * </p>
 *
 * @author zhongxin
 * @since 2020-05-27
 */
@Service
public class TblUserServiceImpl extends ServiceImpl<TblUserMapper, TblUser> implements ITblUserService {

}
