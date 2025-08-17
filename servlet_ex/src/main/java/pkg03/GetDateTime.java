package pkg03;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetDateTime extends HttpServlet {
  
  private static final long serialVersionUID = 1L;
       
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // 요청 인코딩
   request.setCharacterEncoding("UTF-8");
    
    // 요청 파라미터
    String type = request.getParameter("type");
    
    String result = null;
    switch(type) {
    case "1":  // 현재 날짜 요청
      result = LocalDate.now().toString();
      break;
    case "2":  // 현재 시간 요청
      result = LocalTime.now().toString();
      result = result.substring(0, result.indexOf("."));
      break;
    }
    
    // 응답 타입과 인코딩
    response.setContentType("text/html; charset=UTF-8");
    
    // 응답 출력 스트림 생성
    PrintWriter out = response.getWriter();
    
    // 응답 만들기
    out.println("<script>");
    out.println("alert('요청 결과는 " + result + "입니다.')");
    out.println("history.back()");  // 이전 화면으로 이동하기
    out.println("</script>");
    out.flush();
    out.close();
    
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
