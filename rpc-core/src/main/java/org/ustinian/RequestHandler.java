package org.ustinian;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ustinian.entity.RpcRequest;
import org.ustinian.entity.RpcResponse;
import org.ustinian.enumeration.ResponseCode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RequestHandler {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    public Object handle(RpcRequest rpcRequest, Object service){
        Object result = null;
        try{
            result = invokeTargetMethod(rpcRequest, service);
            logger.info("服务:{} 成功调用方法:{}", rpcRequest.getInterfaceName(), rpcRequest.getMethodName());
        } catch (InvocationTargetException | IllegalAccessException e) {
            logger.error("调用或发送时有错误发生：", e);
        }
        return result;
    }
    private Object invokeTargetMethod(RpcRequest request, Object service) throws InvocationTargetException, IllegalAccessException {
        Method method;
        try {
            method = service.getClass().getMethod(request.getMethodName(),request.getParamTypes());
        } catch (NoSuchMethodException e) {
            return RpcResponse.fail(ResponseCode.NOT_FOUND_METHOD);
        }
        return method.invoke(service, request.getParameters());
    }
}
