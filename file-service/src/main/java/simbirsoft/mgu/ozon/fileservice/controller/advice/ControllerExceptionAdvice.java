package simbirsoft.mgu.ozon.fileservice.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import simbirsoft.mgu.ozon.fileservice.dto.MessageDtoResponse;
import simbirsoft.mgu.ozon.fileservice.exception.FileException;

@ControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(FileException.class)
    public ResponseEntity<MessageDtoResponse> catchFileException(FileException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        new MessageDtoResponse(exception.getMessage())
                );
    }

}
