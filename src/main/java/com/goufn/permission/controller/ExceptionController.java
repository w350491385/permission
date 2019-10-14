package com.goufn.permission.controller;

import com.goufn.permission.common.result.Result;
import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionController {

    @ExceptionHandler(ShiroException.class)
    public Result handle401() {
        return Result.unauthorized(null);
    }
}
