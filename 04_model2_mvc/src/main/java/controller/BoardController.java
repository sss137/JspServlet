package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * MVC 흐름
 * view - filter - controller - service - dao
 * ----   ------   ----------   -------------
 * JSP    Filter   Servlet      Interface/Class
 */

/*
 * 요청 방식  URL                          설명
 * -----------------------------------------------------------------------------------
 * GET        /board/list.do               게시판 목록 보기(전체 게시글 리스트)
 * GET        /board/detail.do?bid=1       게시글 상세 보기(번호가 1인 글)
 * GET        /board/registForm.do         게시글 작성 폼 보기(글쓰기 페이지 열기)
 * POST       /board/regist.do             게시글 실제 등록 요청(폼 제출 처리)
 * GET        /board/modifyForm.do?bid=1   게시글 수정 폼 보기(1번 글 수정 화면 열기)
 * POST       /board/modify.do             게시글 실제 수정 요청(수정 폼 제출 처리)
 * GET        /board/remove.do?bid=1       게시글 삭제 요청(1번 글 삭제)
 */

//.do로 끝나는 모든 요청을 처리하는 컨트롤러
@WebServlet("*.do")

public class BoardController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    //요청 확인
    String servletPath = request.getServletPath();
    
    //ActionForward 객체 선언
    ActionForward af = null;
    
    //요청에 따른 구분
    switch(servletPath) {
    case "/board/list.do": 
      af = new ActionForward("/board/list.jsp", false);
      break;
    case "/board/detail.do": 
      af = new ActionForward("/board/detail.jsp", false);
      break;
    case "/board/registForm.do": 
      af = new ActionForward("/board/registForm.jsp", false);
      break;
    case "/board/regist.do": 
      af = new ActionForward("/board/list.jsp", true);      //확인 필요
      break;
    case "/board/modifyForm.do": 
      af = new ActionForward("/board/modify.jsp", false);
      break;
    case "/board/modify.do":
      af = new ActionForward("/board/detail.jsp", false);   //확인 필요
      break;
    case "/board/remove.do":
      af = new ActionForward("/board/list.jsp", true);      //확인 필요
      break;
      
    default:   //잘못된 요청이 왔을 경우
      af = new ActionForward("/main.jsp", false);
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




