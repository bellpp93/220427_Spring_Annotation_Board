package com.company.view.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.annotation.board.BoardDAO;
import com.company.annotation.board.BoardDO;

@Controller
public class BoardController {  // 통합 컨트롤러

	// 전체 게시글 목록 보기
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardDO boardDO, BoardDAO boardDAO, Model model,  // 모델 객체는 Select일 때만 필요 
								String searchField, String searchText) {
		model.addAttribute("boardList", boardDAO.getBoardList(searchField, searchText));
		return "getBoardList.jsp";
	}
	
	// 해당 게시글 상세 보기
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardDO boardDO, BoardDAO boardDAO, Model model) {
		model.addAttribute("board", boardDAO.getBoard(boardDO));
		return "getBoard.jsp";
	}
	
	// 게시글 수정
	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardDO boardDO, BoardDAO boardDAO) {
		boardDAO.updateBoard(boardDO);
		return "getBoardList.do";
	}
}
