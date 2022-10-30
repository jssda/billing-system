package org.team.cuc.billingsystem.bean.bo;

import lombok.Data;
import org.team.cuc.billingsystem.exception.CustomException;
import org.team.cuc.billingsystem.exception.ExceptionCode;

import java.io.Serializable;

/**
 * 前端响应结果类
 *
 * @author jssdjing@gmail.com
 */
@Data
public class AjaxResponse<T> implements Serializable {

    /**
     * 请求响应状态码
     */
    private int code;

    /**
     * 请求结果描述信息
     */
    private String message;

    /**
     * 请求结果数据（通常用于查询操作）
     */
    private T data;

    private AjaxResponse() {
    }

    /**
     * 请求出现异常时的响应数据封装
     *
     * @param e 异常信息
     * @return 返回响应
     */
    public static <T> AjaxResponse<T> error(CustomException e) {
        AjaxResponse<T> resultBean = new AjaxResponse<T>();
        resultBean.setCode(e.getCode());
        resultBean.setMessage(e.getInfo());
        return resultBean;
    }


    public static <T> AjaxResponse<T> error(ExceptionCode exceptionCode, String errorMessage, T data) {
        AjaxResponse<T> resultBean = new AjaxResponse<T>();
        resultBean.setCode(exceptionCode.getCode());
        resultBean.setMessage(errorMessage);
        resultBean.setData(data);
        return resultBean;
    }

    /**
     * 请求出现异常时的响应数据封装
     */
    public static <T> AjaxResponse<T> error(ExceptionCode exceptionCode, String errorMessage) {
        AjaxResponse<T> resultBean = new AjaxResponse<T>();
        resultBean.setCode(exceptionCode.getCode());
        resultBean.setMessage(errorMessage);
        return resultBean;
    }

    /**
     * 请求成功的响应，不带查询数据（用于删除、修改、新增接口）
     */
    public static <T> AjaxResponse<T> success() {
        AjaxResponse<T> ajaxResponse = new AjaxResponse<T>();
        ajaxResponse.setCode(200);
        ajaxResponse.setMessage("请求响应成功!");
        return ajaxResponse;
    }

    /**
     * 请求成功的响应，带有查询数据（用于数据查询接口）
     */
    public static <T> AjaxResponse<T> success(T obj) {
        AjaxResponse<T> ajaxResponse = new AjaxResponse<T>();
        ajaxResponse.setCode(200);
        ajaxResponse.setMessage("请求响应成功!");
        ajaxResponse.setData(obj);
        return ajaxResponse;
    }

    /**
     * 请求成功的响应，带有查询数据（用于数据查询接口）
     */
    public static <T> AjaxResponse<T> success(T obj, String message) {
        AjaxResponse<T> ajaxResponse = new AjaxResponse<T>();
        ajaxResponse.setCode(200);
        ajaxResponse.setMessage(message);
        ajaxResponse.setData(obj);
        return ajaxResponse;
    }


}