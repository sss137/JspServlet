package chap01_request;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RequestExServlet")
public class RequestExServlet extends HttpServlet {
  
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    //요청 파라미터 UTF-8 인코딩
    request.setCharacterEncoding("UTF-8");
    
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String gender = request.getParameter("gender");
    String[] hobbies = request.getParameterValues("hobbies");
    String[] mobile = request.getParameterValues("mobile");
    
    System.out.println("name: " + name);
    System.out.println("email: " + email);
    System.out.println("gender: " + gender);
    System.out.println("hobbies: " + Arrays.toString(hobbies));
    System.out.println("mobile: " + Arrays.toString(mobile));

    response.getWriter().append("Served at: ").append(request.getContextPath());
    
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}