package com.lemon.common.vo;

import com.lemon.common.constant.HttpStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 接口返回数据格式
 *
 * @author xubb
 */
@Data
@ApiModel(value = "接口返回对象", description = "接口返回对象")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功标志
     */
    @ApiModelProperty(value = "成功标志")
    private boolean success = true;

    /**
     * 返回处理消息
     */
    @ApiModelProperty(value = "返回处理消息")
    private String message = "操作成功！";

    /**
     * 返回代码
     */
    @ApiModelProperty(value = "返回代码")
    private Integer code = 0;

    /**
     * 返回数据对象 data
     */
    @ApiModelProperty(value = "返回数据对象")
    private T result;

    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private long timestamp = System.currentTimeMillis();


    public Result() {

    }


    public static <T> Result<T> success() {
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setCode(HttpStatus.SUCCESS);
        r.setMessage("操作成功！");
        return r;
    }

    public static <T> Result<T> success(T data) {
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setCode(HttpStatus.SUCCESS);
        r.setResult(data);
        return r;
    }

    public static <T> Result<T> success(T data, String msg) {
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setCode(HttpStatus.SUCCESS);
        r.setMessage(msg);
        r.setResult(data);
        return r;
    }

    public static Result<Object> error(String msg) {
        return error(HttpStatus.ERROR, msg);
    }

    public static Result<Object> error(int code, String msg) {
        Result<Object> r = new Result<Object>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }

    public Result<T> error500(String msg) {
        this.message = msg;
        this.code = HttpStatus.ERROR;
        this.success = false;
        return this;
    }

    public Result<T> success200(T data, String msg) {
        this.message = msg;
        this.code = HttpStatus.SUCCESS;
        this.success = true;
        this.result = data;
        return this;
    }

    public Result<T> success200(T data) {
        this.code = HttpStatus.SUCCESS;
        this.success = true;
        this.result = data;
        return this;
    }

    public Result<T> success200(String msg) {
        this.message = msg;
        this.code = HttpStatus.SUCCESS;
        this.success = true;
        return this;
    }

}