package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper
public interface UserMapper extends BaseMapper<User> {

//    @Select("select * from user")
//    List<User> findAll();
//
//    @Insert("insert into user(username,password,tel,role) values(#{userName},#{passWord},#{tel},#{role})")
//   int insert(User user);
//
//    int update(User user);
//
//    @Delete("delete from user where userid=#{id}")
//    int deleteById(@Param("id") Integer id);


//    @Select("select * from user where username like concat('%',#{username},'%') and tel like concat('%',#{tel},'%') and role like concat('%',#{role},'%') limit #{pageNum},#{pageSize}")
//    List<User> selectPage(Integer pageNum,Integer pageSize,String username,String tel,String role);

//    @Select("select count(*) from user where username like concat('%',#{username},'%') and tel like concat('%',#{tel},'%') and role like concat('%',#{role},'%')")
//    Integer selectTotal(String username,String tel,String role);
}
