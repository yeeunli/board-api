package B_project.board_api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardPostDto {

    @NotEmpty // 빈 값 들어가면 에러나게끔
    private String title;

    @NotEmpty
    private String content;
}