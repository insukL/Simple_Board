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
    public ResponseEntity<ExceptionDTO> exceptionHandle(BaseException ex){
        ExceptionDTO e = new ExceptionDTO();
        e.setClassName(ex.getClass().getSimpleName());
        e.setErrorMessage(ex.getMessage());
        e.setErrorTrace(ex.getStackTrace()[0].getClassName());
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
