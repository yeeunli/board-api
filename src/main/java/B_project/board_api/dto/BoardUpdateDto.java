package B_project.board_api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardUpdateDto {

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;
}
