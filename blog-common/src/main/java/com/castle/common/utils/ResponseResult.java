package com.castle.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static com.castle.common.utils.MessageConstants.REQUEST_SUCCESS;
import static com.castle.common.utils.MessageConstants.UN_KNOWN_ERROR;

/**
 * @author YuLong
 * Date: 2023/2/24 15:13
 * @Classname ResponseResult
 * @Description 响应结果类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据
     */
    private T data;

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /* 响应成功消息结果 */

    public static <T> ResponseResult<T> success() {
        return success(HttpStatus.OK.value(), REQUEST_SUCCESS);
    }

    public static <T> ResponseResult<T> success(Integer code, String msg) {
        return new ResponseResult<>(code, msg);
    }

    public static <T> ResponseResult<T> success(Integer code, String msg, T data) {
        return new ResponseResult<>(code, msg, data);
    }

    /* 响应失败消息结果 */


    public static <T> ResponseResult<T> error() {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), UN_KNOWN_ERROR);
    }

    public static <T> ResponseResult<T> error(Integer code, String msg) {
        return new ResponseResult<>(code, msg);
    }

    public static <T> ResponseResult<T> error(Integer code, String msg, T data) {
        return new ResponseResult<>(code, msg, data);
    }

}
