package com.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("news")
public class News {
   @TableId(value = "news_id",type = IdType.AUTO)
   private int newsId;

   private String title;

   private String image;

   @TableField(value = "news_abstract")//指定数据库字段名称
   private String newsAbstract;

   private String url;
   private String source;
   private String content;

   @TableField(value = "publish_date")//指定数据库字段名称
   private Date publishDate;
}
