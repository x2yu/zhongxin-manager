package com.zhongxin.manager.service;

import com.zhongxin.manager.dto.TblApplicationDto;
import com.zhongxin.manager.entity.TblApplication;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 应用信息表  服务类
 * </p>
 *
 * @author zhongxin
 * @since 2020-05-27
 */
public interface ITblApplicationService extends IService<TblApplication> {

    public List<TblApplicationDto> queryAll();
    String getManagerNameByID(Integer id);

    List<TblApplication> getByPage(Integer firstIndex, Integer pageSize);

    List<TblApplication> getByPageName(Integer firstIndex, Integer pageSize,String name);

    Integer countName(String name);

    Boolean insertApplication(Integer cate_id, Integer manager, String name);

    Boolean deleteApplication(Integer app_id);
}
