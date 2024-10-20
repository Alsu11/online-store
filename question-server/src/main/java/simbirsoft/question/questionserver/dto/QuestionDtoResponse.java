package simbirsoft.question.questionserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class QuestionDtoResponse {
    private Long id;
    private String questionText;
    private String answer;
    private LocalDateTime sendDate;
}
