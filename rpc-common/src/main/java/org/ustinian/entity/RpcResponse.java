package org.ustinian.entity;

import lombok.Data;
import org.ustinian.enumeration.ResponseCode;

import java.io.Serializable;
@Data
public class RpcResponse<T> implements Serializable {
    private Integer statusCode;
    private String message;
    private T data;
    public static <T> RpcResponse<T> success(T data) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setStatusCode(ResponseCode.SUCCESS.getCode());
        response.setMessage(ResponseCode.SUCCESS.getMessage());
        response.setData(data);
        return response;
    }

    public static <T> RpcResponse<T> fail(ResponseCode code) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setStatusCode(code.getCode());
        response.setMessage(code.getMessage());
        return response;
    }
}
