package simbirsoft.question.questionserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import simbirsoft.question.questionserver.dto.MessageDtoResponse;
import simbirsoft.question.questionserver.exception.QuestionException;
import simbirsoft.question.questionserver.exception.UserException;

@ControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(QuestionException.class)
    public ResponseEntity<MessageDtoResponse> catchQuestionException(QuestionException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new MessageDtoResponse(exception.getMessage())
                );
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<MessageDtoResponse> catchUserException(UserException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new MessageDtoResponse(exception.getMessage())
                );
    }
}
