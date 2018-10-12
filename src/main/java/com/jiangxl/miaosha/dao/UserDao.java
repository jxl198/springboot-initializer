package com.jiangxl.miaosha.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiangxl.miaosha.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Driver;

/**
 * @author jiangxl
 * @description
 * @date 2018-10-12 9:52
 **/
@Mapper
public interface UserDao extends BaseMapper<User> {

//    @Select("select id,name from user where id =#{id}")
//    public User getById(@Param("id") Integer id);
}



