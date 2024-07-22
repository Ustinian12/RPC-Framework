package org.ustinian.exception;

import org.ustinian.enumeration.RpcError;

public class RpcException extends RuntimeException{
    public RpcException(RpcError error, String message){super(error.getMessage() + ":" + message);}
    public RpcException(RpcError error){super(error.getMessage());}
    public RpcException(String message, Throwable cause){super(message, cause);}
}
