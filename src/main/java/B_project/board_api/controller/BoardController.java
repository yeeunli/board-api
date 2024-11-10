package B_project.board_api.controller;

import B_project.board_api.dto.BoardPostDto;
import B_project.board_api.dto.BoardUpdateDto;
import B_project.board_api.entity.Board;
import B_project.board_api.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
@RestController // Json 타입으로 값을 반환해주는 RestController
                // @Controller: view 값(ex. html)을 바로 끌고 와주는 컨트롤러
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping // Post 요청 받아요~
    public ResponseEntity postBoard(@RequestBody @Validated BoardPostDto boardPostDto) {

        // ResponseEntity: 응답 상태와 body 값 설정 가능
        // @RequestBody: client가 보낸 Json 값을 우리 환경에 맞게 변환해줌 (즉, BoardPostDto에 짜맞춤)
        // @Validated: 유효성 검증 -> 통과 or 에러

        Long boardId= boardService.createBoard(boardPostDto); // 서비스야 Id 리턴해줘, 우리 boardId 필요하거든~

        Map<String, Long> postBoard = new HashMap<>();
        postBoard.put("id", boardId);

        return ResponseEntity.ok(postBoard);

//        return ResponseEntity.ok(boardId);
//        return ResponseEntity.status(HttpStatus.CREATED).body(boardId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoard(@PathVariable("id") Long boardId) {
        try {
            Board board = boardService.findByBoardId(boardId);
            return ResponseEntity.ok().body(board);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Board>> getBoardList() {
        List<Board> boards = boardService.findAllBoards();
        return ResponseEntity.ok().body(boards);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateBoard(@PathVariable("id") Long boardId, @RequestBody @Validated BoardUpdateDto boardUpdateDto) {
        boardService.updateBoard(boardUpdateDto, boardId);

        return ResponseEntity.ok().build(); // build(): 본문 없이 단순 성공 여부만 알려줌
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable("id") Long boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.ok().build();
//        return ResponseEntity.noContent().build(); // 204 no content
    }

}
