package chap03_forward;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SecondForward")
public class SecondForward extends HttpServlet {

  private static final long serialVersionUID = 1L;
      
  //FirstForward로부터 전달(forward)된 요청(request)과 응답(response)은 doGet() 메소드가 받습니다.
  //최초 요청 방식(여기선 a 태그/method="get")이 forward할 때도 그대로 유지되기 때문에 doGet() 메소드가 받습니다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  //요청 파라미터의 전달 확인하기
	  String p = request.getParameter("p");
	  
	  //응답 만들기
	  response.setHeader("Content-Type", "text/html; charset=UTF-8");
	  PrintWriter out = response.getWriter();
	  out.println("<script>");
	  out.println("alert('" + p + " 전달 완료')");
	  out.println("history.back()");
	  out.println("</script>");
	  out.close();
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  doGet(request, response);
	}

}





