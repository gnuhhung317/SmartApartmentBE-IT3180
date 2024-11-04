package com.hust.smart_apartment.dto;


import lombok.Data;

@Data
public class BaseResponse<T> {

    private Integer code;

    private String message;

    private T data;

    public BaseResponse(){
        this.code=200;
        this.message="success";
    }

    public static <T> BaseResponse<T> ok(T body){
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(200);
        baseResponse.setMessage("success");
        baseResponse.setData(body);
        return baseResponse;
    }

    public static <T> BaseResponse<T> error(Integer code, String message){
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(code);
        baseResponse.setMessage(message);
        return baseResponse;
    }
    public static <T> BaseResponse<T> error(Integer code, String msg, T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(code);
        response.setMessage(msg);
        response.setData(data);
        return response;
    }
}
