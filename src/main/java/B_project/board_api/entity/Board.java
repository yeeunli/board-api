package B_project.board_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // 나는 엔티티다!!
@Getter @Setter // 생성자 함수
@NoArgsConstructor // 기본 생성자 생성
public class Board {

    @Id // 해당 변수를 ID 값으로 선언\
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB 기본키 지정
    private Long boardId;

    private String title;

    private String content;
}
