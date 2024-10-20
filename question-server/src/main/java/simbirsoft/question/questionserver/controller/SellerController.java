package simbirsoft.question.questionserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simbirsoft.question.questionserver.dto.AnswerDtoRequest;
import simbirsoft.question.questionserver.dto.QuestionDtoResponse;
import simbirsoft.question.questionserver.service.QuestionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seller/{seller-id}/{product-id}")
public class SellerController {

    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<QuestionDtoResponse>> getQuestionsByProduct(
            @PathVariable("seller-id") Long sellerId,
            @PathVariable("product-id") Long productId
    ) {
        return ResponseEntity.ok(questionService.getQuestionsByProduct(sellerId, productId));
    }

    @GetMapping("/{question-id}")
    public ResponseEntity<QuestionDtoResponse> getQuestionById(
            @PathVariable("seller-id") Long sellerId,
            @PathVariable("question-id") Long questionId
    ) {
        return ResponseEntity.ok(questionService.getQuestionById(sellerId, questionId));
    }

    @PostMapping("/{question-id}")
    public ResponseEntity<QuestionDtoResponse> answer(
            @PathVariable("seller-id") Long sellerId,
            @PathVariable("question-id") Long questionId,
            @RequestBody AnswerDtoRequest answerDtoRequest
    ) {
        return ResponseEntity.ok(questionService.answer(sellerId, questionId, answerDtoRequest));
    }

}
