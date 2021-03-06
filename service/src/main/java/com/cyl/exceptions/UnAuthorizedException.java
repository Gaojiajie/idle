package com.cyl.exceptions;

import com.cyl.enums.ErrorCode;
import com.cyl.enums.ErrorMessage;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UnAuthorizedException extends BusinessException{
    private ErrorCode errorCode;
    private ErrorMessage errorMessage;

    public UnAuthorizedException() {
        super();
    }

    public UnAuthorizedException(ErrorCode errorCode, ErrorMessage errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode()
    {
        return errorCode.getCode();
    }
    public String getErrorMessage() {
        return errorMessage.getMessage();
    }
}
