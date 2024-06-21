package com.rentcar.back.dto.request.board.qnaboard;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PutQnaBoardRequestDto {
    
    @NotBlank
    private String title;
    @NotBlank
    private String contents;
    @NotBlank
    private String category;
    private String imageUrl;
    @NotNull
    private Boolean publicState;
}
