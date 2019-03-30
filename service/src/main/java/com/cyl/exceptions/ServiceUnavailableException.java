package com.cyl.exceptions;

import com.cyl.enums.ErrorCode;
import com.cyl.enums.ErrorMessage;
import org.springframework.stereotype.Service;

/**
 * Created by cyl on 2019-03-07.
 */
@Service
public class ServiceUnavailableException extends BusinessException{
    private ErrorCode errorCode;
    private ErrorMessage errorMessage;

    public ServiceUnavailableException() {
        super();
    }

    public ServiceUnavailableException(ErrorCode errorCode, ErrorMessage errorMessage) {
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
