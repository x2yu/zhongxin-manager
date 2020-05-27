package com.zhongxin.manager.utils;

/**
 * @Author: x2yu
 * @Date: 2020/2/11 16:21
 * @Describe：返回码工具类
 */
public class ResultUtil {

    /**
     * 成功有返回
     */
    public static Result success(Object object) {
        Result result = new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
        result.setData(object);
        return result;
    }

    /**
     *  成功无返回
     */
    public static Result success() {
        return ResultUtil.success(null);
    }

    /**
     * 返回自定义状态码及msg
     */
    public static Result customResultParam(String code, String msg) {
        return new Result(code, msg);
    }

    /**
     * 提交错误
     * */
    public static Result submitError(){
        return new Result(ResultEnum.SUBMIT_ERROR.getCode(),ResultEnum.SUBMIT_ERROR.getMessage());
    }

    public static Result submitError(Object object){
        Result result = new Result(ResultEnum.SUBMIT_ERROR.getCode(),ResultEnum.SUBMIT_ERROR.getMessage());
        result.setData(object);
        return result;
    }

    public static Result error(String code, String msg) {
        Result result = new Result(code, msg);
        return result;
    }
}
