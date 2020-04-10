package com.project.project5.exception;

import com.project.project5.enums.ExceptionEnums;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName GlobalException
 * @date 2020/4/9 18:39
 */
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ExceptionEnums exceptionEnums;

    public GlobalException(ExceptionEnums exceptionEnums) {
        super();
        this.exceptionEnums = exceptionEnums;
    }

    public ExceptionEnums getEnums(){
        return exceptionEnums;
    }
}
