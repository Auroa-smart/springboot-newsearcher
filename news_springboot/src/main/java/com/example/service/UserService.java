package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Constants;
import com.example.common.Result;
import com.example.controller.dto.UserDTO;
import com.example.exception.ServiceException;
import com.example.mapper.UserMapper;
import com.example.pojo.User;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper,User> {
//    @Autowired
//    UserMapper userMapper;
//
//    public int save(User user){
//        if(  user.getUserId() == 0 ){ //user没有id,表明是新增
//           return userMapper.insert(user);
//        }else {
//            return userMapper.update(user);
//        }
//    }

    public boolean saveUser(User user){
//        if(user.getUserId() == 0){
//            return  save(user);
//        }else{
//            return updateById(user);
//        }
        return saveOrUpdate(user);
    }

    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        //防止用户名和密码都相同的
//        List<User> userList = list(queryWrapper);
//        return userList.size() != 0;

        try{
            if(one != null){
                BeanUtils.copyProperties(one,userDTO);
                return userDTO;
            }else{
                throw new ServiceException(Constants.CODE_500,"系统错误");
            }
        }catch (Exception e){
             throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }

    }

    public User register(UserDTO userDTO) {
//        User one =getUserInfo(userDTO);
//        if(one == null){
            User one = new User();
            one.setRole("用户");
            System.out.println(one.toString());
            BeanUtils.copyProperties(userDTO,one);
            save(one); //把copy之后的用户对象存到数据库
//        }else{
//            throw new ServiceException(Constants.CODE_600,"用户名已存在");
//        }
        return one;
    }

    private User getUserInfo(UserDTO userDTO){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userDTO.getUserName());
        queryWrapper.eq("password",userDTO.getPassWord());
        //防止用户名和密码都相同的
//        List<User> userList = list(queryWrapper);
//        return userList.size() != 0;
        User one;
        try{
//             one = getOne(queryWrapper);
             one = getOne(queryWrapper.select("*"));
            System.out.println("角色："+ one.toString());
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
        return one;
    }
}


