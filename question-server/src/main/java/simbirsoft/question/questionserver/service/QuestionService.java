package simbirsoft.question.questionserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simbirsoft.question.questionserver.domain.Product;
import simbirsoft.question.questionserver.domain.Question;
import simbirsoft.question.questionserver.domain.User;
import simbirsoft.question.questionserver.dto.AnswerDtoRequest;
import simbirsoft.question.questionserver.dto.QuestionDtoRequest;
import simbirsoft.question.questionserver.dto.QuestionDtoResponse;
import simbirsoft.question.questionserver.exception.QuestionException;
import simbirsoft.question.questionserver.exception.UserException;
import simbirsoft.question.questionserver.repository.ProductRepository;
import simbirsoft.question.questionserver.repository.QuestionRepository;
import simbirsoft.question.questionserver.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public QuestionDtoResponse askQuestion(Long customerId, Long productId, QuestionDtoRequest questionDtoRequest) {
        Product product = getProductFromDbDById(productId);
        User customer = getUserFromDbById(customerId);

        Question question = Question.builder()
                .questionText(questionDtoRequest.getQuestionText())
                .sendDate(LocalDateTime.now())
                .product(product)
                .customer(customer)
                .build();
        question = questionRepository.save(question);

        return getQuestionDtoResponse(question);
    }

    public QuestionDtoResponse getAnswerByQuestion(Long customerId, Long questionId) {
        Question question = getQuestionFromDbById(questionId);

        if (question.getCustomer().getId() != customerId) {
            throw new UserException("Данный вопрос не принадлежит пользователю");
        }

        return getQuestionDtoResponse(question);
    }

    public List<QuestionDtoResponse> getQuestionsByProduct(Long sellerId, Long productId) {
        Product product = getProductFromDbDById(productId);
        if (product.getOwner().getId() != sellerId) {
            throw new IllegalArgumentException();
        }
        List<Question> questionList = product.getQuestions();

        return getListQuestionDtoResponse(questionList);
    }

    public QuestionDtoResponse getQuestionById(Long sellerId, Long questionId) {
        Question question = getQuestionFromDbById(questionId);

        return getQuestionDtoResponse(question);
    }

    public QuestionDtoResponse answer(Long sellerId, Long questionId, AnswerDtoRequest answerDtoRequest) {
        Question question = getQuestionFromDbById(questionId);
        question.setAnswer(answerDtoRequest.getAnswer());

        question = questionRepository.save(question);

        return getQuestionDtoResponse(question);
    }

    private List<QuestionDtoResponse> getListQuestionDtoResponse(List<Question> questions) {
        List<QuestionDtoResponse> result = new ArrayList<>();
        for (Question question : questions) {
            result.add(getQuestionDtoResponse(question));
        }
        return result;
    }

    private QuestionDtoResponse getQuestionDtoResponse(Question question) {
        return QuestionDtoResponse.builder()
                .id(question.getId())
                .questionText(question.getQuestionText())
                .sendDate(question.getSendDate())
                .answer(question.getAnswer())
                .build();
    }

    private Product getProductFromDbDById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    private User getUserFromDbById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException("Пользователя с id = " + userId + "нет в базе данных"));
    }

    private Question getQuestionFromDbById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionException("Вопроса с id = " + questionId + " нет в базе данных"));
    }

}
