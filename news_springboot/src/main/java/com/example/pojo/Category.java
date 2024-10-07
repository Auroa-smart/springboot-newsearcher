package com.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("news_category")
public class Category {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;

    @TableField(value = "news_id",jdbcType = JdbcType.INTEGER)//指定数据库字段名称
    private int newsId;

    @TableField(value = "category_id",jdbcType = JdbcType.INTEGER)//指定数据库字段名称
    private int categoryId;
}
