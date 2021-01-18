package SimpleBoard.controller;

import SimpleBoard.domain.ExceptionDTO;
import SimpleBoard.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ResponseBody
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ExceptionDTO> exceptionHandle(BaseException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setClassName(e.getClass().getSimpleName());
        exceptionDTO.setErrorMessage(e.getMessage());
        exceptionDTO.setErrorTrace(e.getStackTrace()[0].getClassName());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }
}
