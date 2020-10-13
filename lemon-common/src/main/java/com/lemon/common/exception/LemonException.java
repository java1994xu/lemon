package com.lemon.common.exception;

/**
 * @author xubb
 * @Description  自定义异常
 */
public class LemonException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LemonException(String message){
        super(message);
    }

    public LemonException(Throwable cause)
    {
        super(cause);
    }

    public LemonException(String message, Throwable cause)
    {
        super(message,cause);
    }
}
