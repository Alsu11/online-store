package simbirsoft.question.questionserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simbirsoft.question.questionserver.dto.QuestionDtoRequest;
import simbirsoft.question.questionserver.dto.QuestionDtoResponse;
import simbirsoft.question.questionserver.service.QuestionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer/{customer-id}/{product-id}")
public class CustomerController {

    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<QuestionDtoResponse> askQuestion(
            @PathVariable("customer-id") Long customerId,
            @PathVariable("product-id") Long productId,
            @RequestBody QuestionDtoRequest questionDtoRequest) {
        return ResponseEntity.ok(
                questionService.askQuestion(customerId, productId, questionDtoRequest)
        );
    }

    @GetMapping("/{question-id}")
    public ResponseEntity<QuestionDtoResponse> getAnswerByQuestion(
            @PathVariable("customer-id") Long customerId,
            @PathVariable("question-id") Long questionId) {
        return ResponseEntity.ok(questionService.getAnswerByQuestion(customerId, questionId));
    }

}
