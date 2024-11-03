package B_project.board_api.repository;

import B_project.board_api.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 내가 레포지토리다!!
public interface BoardRepository extends JpaRepository <Board, Long> {
    // Repository에 사용될 Board 엔티티와 Long 타입

}
