package com.example.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User implements Serializable {
    @TableId(value = "userid")
    private int userId;

    @TableField(value = "username")//指定数据库字段名称
    private String userName;

    @Ignore
    @TableField(value = "password")
    private String passWord;

    private String tel;

    private String role;
}
