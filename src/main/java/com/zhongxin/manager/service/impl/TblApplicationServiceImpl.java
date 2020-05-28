package com.zhongxin.manager.service.impl;

import com.zhongxin.manager.dto.TblApplicationDto;
import com.zhongxin.manager.entity.TblApplication;
import com.zhongxin.manager.mapper.TblApplicationMapper;
import com.zhongxin.manager.service.ITblApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 应用信息表  服务实现类
 * </p>
 *
 * @author zhongxin
 * @since 2020-05-27
 */
@Service
public class TblApplicationServiceImpl extends ServiceImpl<TblApplicationMapper, TblApplication> implements ITblApplicationService {


    @Autowired
    TblApplicationMapper tblApplicationMapper;

    @Override
    public List<TblApplicationDto> queryAll() {
        return null;
    }

    public String getManagerNameByID(Integer id){
        return tblApplicationMapper.getManagerNameByID(id);
    }

    @Override
    public List<TblApplication> getByPage(Integer firstIndex, Integer pageSize) {
        return tblApplicationMapper.getByPage(firstIndex,pageSize);
    }

    @Override
    public List<TblApplication> getByPageName(Integer firstIndex, Integer pageSize, String name) {
        return tblApplicationMapper.getByPageName(firstIndex,pageSize,name);
    }

    @Override
    public Integer countName(String name) {
        return tblApplicationMapper.countName(name);
    }

    @Override
    @Transactional
    public Boolean insertApplication(Integer cate_id, Integer manager, String name) {
        TblApplication tblApplication = new TblApplication();
        tblApplication.setCateId(cate_id);
        tblApplication.setAppName(name);
        tblApplication.setVersion("1.0");
        Integer i = tblApplicationMapper.insert(tblApplication);
        Integer app_id = tblApplicationMapper.getIdByName(name);
        Integer app = tblApplicationMapper.insertMaApp(app_id, manager);
        if(i >0 && app >0)
            return true;
        else
            return false;
    }

    @Override
    @Transactional
    public Boolean deleteApplication(Integer app_id) {
        tblApplicationMapper.deleteApp(app_id);

        tblApplicationMapper.deleteAPPMa(app_id);


        if(tblApplicationMapper.countDocByAppId(app_id)>0)
            tblApplicationMapper.deleteDoc(app_id);

        tblApplicationMapper.deleteReview(app_id);

        return true;
    }
}
