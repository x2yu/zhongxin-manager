package com.zhongxin.manager.mapper;

import com.zhongxin.manager.entity.TblApplication;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * 应用信息表  Mapper 接口
 * </p>
 *
 * @author zhongxin
 * @since 2020-05-27
 */
public interface TblApplicationMapper extends BaseMapper<TblApplication> {

    @Select("Select manager_name from tbl_manager where manager_id = #{id}")
    String getManagerNameByID(Integer id);

    @Select("Select count(*) from tbl_application where cate_id = #{categoryId}")
    Integer getCategorySum(Integer categoryId);

    @Select("Select * from tbl_application limit #{firstIndex},#{pageSize}")
    List<TblApplication> getByPage(Integer firstIndex, Integer pageSize);

    @Select("Select * from tbl_application where app_name like '%${name}%' limit #{firstIndex},#{pageSize}")
    List<TblApplication> getByPageName(Integer firstIndex, Integer pageSize, String name);

    @Select("Select count(*) from tbl_application where app_name like '%${name}%'")
    Integer countName(String name);

    @Select("Select app_id from tbl_application where app_name = #{name}")
    Integer getIdByName(String name);

    @Insert("Insert into tbl_app_manager(app_id,manager_id) values (#{app_id},#{manager})")
    Integer insertMaApp(Integer app_id, Integer manager);

    @Delete("DELETE FROM tbl_application where app_id = #{app_id} ")
    Integer deleteApp(Integer app_id);

    @Delete("DELETE FROM tbl_app_manager where app_id = #{app_id} ")
    Integer deleteAPPMa(Integer app_id);

    @Delete("DELETE FROM tbl_doc where app_id = #{app_id} ")
    Integer deleteDoc(Integer app_id);

    @Delete("DELETE FROM tbl_review where app_id = #{app_id} ")
    Integer deleteReview(Integer app_id);

    @Select("Select count(*) from tbl_doc where app_id = #{app_id}")
    Integer countDocByAppId(Integer app_id);
}
