package com.rentcar.back.dto.request.board.qnaboard;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostQnaCommentRequestDto {
    
    @NotBlank
    private String comment;
}
