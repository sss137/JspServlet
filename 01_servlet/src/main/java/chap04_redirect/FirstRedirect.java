package chap04_redirect;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FirstRedirect")

public class FirstRedirect extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  /*
	   * redirect
	   * 1. 서버가 클라이언트에게 "새로운 주소로 다시 요청하세요"라고 지시하는 방식입니다.(응답의 일종입니다.)
	   * 2. redirect 지시를 받은 클라이언트는 다시 새로운 주소로 이동하므로 URL 변경을 확인할 수 있습니다.
	   * 3. 최초 요청 시 사용한 request와 response는 전달되지 않습니다.(redirect는 별개의 새로운 요청이기 때문입니다.)
	   * (= 서버가 redirect를 시킨 후 클라이언트가 새로 보내는 요청에는 원래의 request/response 정보가 담겨 있지 않다.)
	   * 4. redirect 시 다른 서버(혹은 애플리케이션)로 이동합니다.
	   * 5. DB의 데이터 변경 작업(UPDATE, INSERT, DELETE) -> redirect -> 새로 고침 시 중복 제출(서브밋) 등을 방지할 수 있습니다.
	   *    PRG(Post-Redirect-Get) 패턴을 수행합니다.(redirect 이후 모든 요청은 get방식입니다.)
	   *
	   * 단순하게 DB의 데이터 변경 작업(UPDATE, INSERT, DELETE) 했다? redirect
	   *          SELECT 결과 전달 또는 단순 페이지 이동하고 싶다? forward 
	   */
	  
	  //redierct(새로운 주소로 다시 요청하라는 응답)
	  response.sendRedirect("/01_servlet/SecondRedirect?p=" + URLEncoder.encode(request.getParameter("p"), "UTF-8")); //contextPath와 servletPath 모두 작성
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  doGet(request, response);
	
	}

}





