package B_project.board_api.service;

import B_project.board_api.dto.BoardPostDto;
import B_project.board_api.dto.BoardUpdateDto;
import B_project.board_api.entity.Board;
import B_project.board_api.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public Board findByBoardId(Long boardId) {
        // findById 메서드가 Optional<Board> 타입을 반환하기에
        // Board가 없는 경우도 처리해줘야함
        // 자비: 타입 불일치 -> 에러 발생
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("no board: " + boardId));
    }

    @Transactional(readOnly = true)
    public List<Board> findAllBoards() {
        return boardRepository.findAll();
    }

    public Long updateBoard(BoardUpdateDto boardUpdateDto, Long boardId) {

        Board board = findByBoardId(boardId); // boardId에 맞는 board 들고오자

        board.setTitle(boardUpdateDto.getTitle()); // board 엔티티에 수정된 title을 넣어주자
        board.setContent(boardUpdateDto.getContent());

        return boardRepository.save(board).getBoardId();
    }

    public void deleteBoard(Long boardId) {
        findByBoardId(boardId);
        boardRepository.deleteById(boardId);
    }
}