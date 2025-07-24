package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import dao.BoardDaoImpl;
import model.dto.BoardDTO;
import service.BoardService;
import service.BoardServiceImpl;

/*
 * MVC 흐름
 * view - filter - controller - service - dao
 * ----   ------   ----------   -------------
 * JSP    Filter   Servlet      Interface/Class
 */

/*
 * 요청 방식  URL                                      설명
 * -----------------------------------------------------------------------------------------------
 * GET        /board/list.do                           게시판 목록 보기(전체 게시글 리스트)
 * GET        /board/detail.do?bid=1&code=detail       게시글 상세 보기(번호가 1인 글)
 * GET        /board/registForm.do                     게시글 작성 폼 보기(글쓰기 페이지 열기)
 * POST       /board/regist.do                         게시글 실제 등록 요청(폼 제출 처리)
 * GET        /board/modifyForm.do?bid=1&code=modify   게시글 수정 폼 보기(1번 글 수정 화면 열기)
 * POST       /board/modify.do                         게시글 실제 수정 요청(수정 폼 제출 처리)
 * GET        /board/remove.do?bid=1                   게시글 삭제 요청(1번 글 삭제)
 */

//.do로 끝나는 모든 요청을 처리하는 컨트롤러
@WebServlet("*.do")

public class BoardController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    //BoardService 객체 생성
    BoardService boardService = new BoardServiceImpl();
    
    //요청 확인
    String servletPath = request.getServletPath();
    
    //ActionForward 객체 선언
    ActionForward af = null;
    
    //요청에 따른 구분
    switch(servletPath) {
    case "/main.do":
      af = new ActionForward("/main.jsp", false);
      break;
    case "/board/list.do": 
      af = boardService.getBoards(request);
      break;
    case "/board/detail.do": 
      af = boardService.getBoardById(request);
      break;
    case "/board/registForm.do": 
      af = new ActionForward("/board/regist.jsp", false);
      break;
    case "/board/regist.do": 
      af = boardService.registBoard(request);
      break;
    case "/board/modifyForm.do": 
      af = boardService.getBoardById(request);
      break;
    case "/board/modify.do":
      af = boardService.modifyBoard(request);
      break;
    case "/board/remove.do":
      af = boardService.removeBoard(request);
      break;
      
    default:   //잘못된 요청이 왔을 경우
      af = new ActionForward(request.getContextPath() + "/main.do", true);
    }
    
    //이동
    if(af.isRedirect()) {
      response.sendRedirect(af.getView());
    } else {
      request.getRequestDispatcher(af.getView()).forward(request, response);
    }
    
  }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  doGet(request, response);
	
	}

}




