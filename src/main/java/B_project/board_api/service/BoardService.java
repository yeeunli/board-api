package B_project.board_api.service;

import B_project.board_api.dto.BoardPostDto;
import B_project.board_api.entity.Board;
import B_project.board_api.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // 내가 서비스다 !! 비즈니스 로직을 담당하지
@RequiredArgsConstructor // 생성자 중 final과 같은 필수 인자 생성
public class BoardService {

    private final BoardRepository boardRepository;

    public Long createBoard(BoardPostDto boardPostDto) { // Dto의 값들을 들고 오자
        Board board = new Board(); // Board 엔티티도 들고오자

        board.setTitle(boardPostDto.getTitle()); // board 엔티티에 Dto에 있는 title을 넣어주자
        board.setContent(boardPostDto.getContent());

        return boardRepository.save(board).getBoardId(); // 레포지토리에 board 엔티티를 save한 뒤, Id를 받아서 Long 타입으로 반환하자
    }
}