package com.zhongxin.manager;

import com.zhongxin.manager.controller.TblApplicationController;
import com.zhongxin.manager.entity.TblApplication;
import com.zhongxin.manager.mapper.TblCategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserTest {

    @Autowired
    TblCategoryMapper tblCategoryMapper;

    @Test
    void doTest(){
        String nameByid = tblCategoryMapper.getNameByid(1);
        System.out.println(nameByid);
    }

}
