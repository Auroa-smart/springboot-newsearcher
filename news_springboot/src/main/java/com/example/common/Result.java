package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//接口统一返回包装类

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private String code;
    private String msg;//失败原因
    private Object data;

    public static Result success(){
        return  new Result(Constants.CODE_200,"",null);
    }
    public static Result success(Object data){
        System.out.println("成功码200");
        return  new Result(Constants.CODE_200,"",data);
    }
    public static Result error(String code,String msg){
        return  new Result(code,msg,null);
    }
    public static Result error(){
        return  new Result(Constants.CODE_500,"系统错误",null);
    }
}
