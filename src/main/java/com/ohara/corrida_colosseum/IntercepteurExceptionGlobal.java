package com.ohara.corrida_colosseum;


import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class IntercepteurExceptionGlobal {

//    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    @ResponseBody
//    public Map<String, Object> handleAccessDeniedException(AccessDeniedException ex) {
//        Map<String, Object> error = new HashMap<>();
//        error.put("error", "Access denied");
//        error.put("message", ex.getMessage());
//        return error;
//    }
//
//
//    public Map<String,Object> handleBadRequestException(BadRequestException ex) {
//        Map<String, Object> error = new HashMap<>();
//        error.put("error", "Bad request");
//        error.put("message", ex.getMessage());
//        return error;
//    }




 //   à déceommenter uniqyement pour pouvoir voir les erreurs en production/staging en l'absence de console

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Code de retour
//    @ResponseBody
//    protected Map<String, Object> handleAllError(RuntimeException ex) {
//
//        StringWriter sw = new StringWriter();
//        PrintWriter pw = new PrintWriter(sw);
//        ex.printStackTrace(pw);
//        String bodyOfResponse = sw.toString();
//
//        return Map.of("message", bodyOfResponse);
//    }
}
