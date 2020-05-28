package com.zhongxin.manager.mapper;

import com.zhongxin.manager.entity.TblCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * 应用类别表  Mapper 接口
 * </p>
 *
 * @author zhongxin
 * @since 2020-05-27
 */
public interface TblCategoryMapper extends BaseMapper<TblCategory> {

    @Select("Select cate_name from tbl_category where cate_id = #{id}")
    String getNameByid(Integer id);

    @Select("Select * from tbl_category limit #{firstIndex},#{size}")
    List<TblCategory> getByPage(Integer firstIndex, Integer size);

    @Select("Select * from tbl_category where cate_name like '%${name}%' limit #{firstIndex},#{size}")
    List<TblCategory> getByPageName(Integer firstIndex, Integer size,String name);

    @Select("Select count(*) from tbl_category where cate_name = #{cate_name}")
    Integer isExist(String cate_name);

    @Insert("Insert into tbl_category(cate_name) values(#{cateName})")
    Integer insertCate(TblCategory tblCategory);

    @Delete("Delete From tbl_category where cate_name = #{cateName}")
    Integer deleteCate(String cateName);

    @Select("Select * from tbl_category where cate_name = #{cateName}")
    TblCategory getOldCate(String cateName);

    @Update("Update tbl_category set cate_name = #{cateName} where cate_id = #{cateId}")
    Integer updateCate(TblCategory tblCategory);

    @Select("Select count(*) from tbl_category where cate_name like '%${name}%'")
    Integer countName(String name);
}
