package pkg04;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Save extends HttpServlet {
  
  private static final long serialVersionUID = 1L;
       
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // 저장할 파일명 선언
    String filename = null;
    
    // 파일 생성 시 예외 발생 여부에 따라 응답 메시지를 다르게 생성하기 위해서 try catch 사용함
    try {
      
      // 요청 인코딩
      request.setCharacterEncoding("UTF-8");
      
      // 요청 파라미터
      String writer = request.getParameter("writer");
      String title = request.getParameter("title");
      String contents = request.getParameter("contents");
      
      // 저장할 디렉터리 (없으면 만들기)
      File dir = new File("\\storage");  // 루트 디렉터리 아래 storage 디렉터리
      if(!dir.exists()) {
        dir.mkdirs();
      }
      
      // 저장할 파일명 (날짜-작성자-제목.txt)
      filename = LocalDate.now().toString() + "-" + writer + "-" + title + ".txt";
      
      // 저장할 파일의 File 객체
      File file = new File(dir, filename);
      BufferedWriter bw = new BufferedWriter(new FileWriter(file));
      bw.write(contents);
      bw.flush();
      bw.close();
      
      /* 정상 처리 상황의 응답 처리 */
      
      // 응답 타입과 인코딩
      response.setContentType("text/html; charset=UTF-8");
      
      // 응답 출력 스트림 생성
      PrintWriter out = response.getWriter();
      
      // 응답하기
      out.println("<script>");
      out.println("alert('" + filename + "  파일이 생성되었습니다.')");
      out.println("location.href='/servlet_ex/pkg04/NewFile.html'");
      out.println("</script>");
      out.flush();
      out.close();
      
    } catch(Exception e) {
      
      /* 예외 상황의 응답 처리 */
      
      // 응답 타입과 인코딩
      response.setContentType("text/html; charset=UTF-8");
      
      // 응답 출력 스트림 생성
      PrintWriter out = response.getWriter();
      
      // 응답하기
      out.println("<script>");
      out.println("alert('" + filename + "  파일 생성에 실패했습니다.')");
      out.println("history.back()");
      out.println("</script>");
      out.close();
      
    }
    
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
