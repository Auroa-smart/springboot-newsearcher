package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Constants;
import com.example.common.Result;
import com.example.controller.dto.UserDTO;
import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

//    @Autowired
//    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findALl(){
//        return userMapper.findAll();
        return userService.list();
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        String username = userDTO.getUserName();
        String password = userDTO.getPassWord();
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return Result.error(Constants.CODE_400,"参数不足");
        }
//        return userService.login(userDTO);
        UserDTO userDTO1 = userService.login(userDTO);
        return Result.success(userDTO1);
    }
//
    @PostMapping
    public boolean save(@RequestBody User user){
        System.out.println(user.getRole());
        return userService.saveUser(user);
    }
    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO){
        String username = userDTO.getUserName();
        String password = userDTO.getPassWord();
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return Result.error(Constants.CODE_400,"参数不足");
        }
        return Result.success(userService.register(userDTO));
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
          return userService.removeBatchByIds(ids);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
//        return userMapper.deleteById(id);
        return  userService.removeById(id);
    }
//
//
////    @RequestParam 接收 ？pageNum=1&pageSize=10 映射过来
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String userName, @RequestParam String tel, @RequestParam String role){
        System.out.println("当前页："+pageNum+"当前条数："+pageSize);
//        pageNum = (pageNum-1)*pageSize;
//        List<User> all = userMapper.selectPage(pageNum,pageSize,username,tel,role);
//        Integer total = userMapper.selectTotal(username,tel,role);
//        Map<String,Object> res = new HashMap<>();
//        res.put("data",all);
//        res.put("total",total);
//        return  res;

        IPage<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        if(!"".equals(userName)){
            queryWrapper.like("username",userName);
        }
        if(!"".equals(tel)) {
            queryWrapper.like("tel", tel);
        }
        if(!"".equals(role)) {
            queryWrapper.like("role", role);
//        queryWrapper.or().like("role",role);//无效的
        }
        System.out.println(userService.page(page, queryWrapper));
        return userService.page(page, queryWrapper);
    }
}
