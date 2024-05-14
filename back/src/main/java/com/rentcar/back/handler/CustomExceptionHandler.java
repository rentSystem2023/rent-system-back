package com.rentcar.back.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.rentcar.back.dto.response.ResponseDto;

// Request의 데이터 유효성 검사에서 발생하는 예외 처리
// (데이터베이스 작업 중 에러가 발생하면 'DBE' 응답 처리 )

@RestControllerAdvice
public class CustomExceptionHandler {

    // RequestBody의 데이터 유효성 검사 중 발생하는 예외 핸들링
    // - MethodArgumentNotValidException : 유효하지 않은 데이터
    // - HttpMessageNotReadableException : RequestBody가 없어서 유효성 검사를 하지 못할 때

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class
    }) // 전달할때 배열로 전달
    public ResponseEntity<ResponseDto> validationExceptionHandler(
            Exception exception) {
        exception.printStackTrace();
        return ResponseDto.validationFailed();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ResponseDto>noHandlerFoundExceptionHandler(
        Exception exception
    ){
        exception.printStackTrace();
        return ResponseDto.notFound();
    }

}
