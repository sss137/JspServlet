package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.ActionForward;
import dao.UserDao;
import model.dto.UserDTO;

public class UserServiceImpl implements UserService {

  //서비스는 DAO를 사용합니다.
  private UserDao userDao = UserDao.getInstance();
  
  @Override
  public ActionForward login(HttpServletRequest request) {
    
    //email, password 받기
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    
    //email, password를 가진 UserDTO 객체 생성
    UserDTO user = new UserDTO();
    user.setEmail(email);
    user.setPassword(password);
    
    //사용자 조회
    UserDTO foundUser = userDao.getUser(user);
    
    //foundUser가 존재하면 "로그인 처리" 수행, 아니면 로그인 화면(/user/login.jsp)으로 이동(리다이렉트)
    String view = null;
    boolean isRedirect = false;
    if(foundUser != null) {
      //로그인 성공(foundUser 객체를 세션에 올려두고, 메인 화면(/index.jsp)으로 이동(리다이렉트))
      HttpSession session = request.getSession();
      session.setAttribute("loginUser", foundUser);
      view = request.getContextPath() + "/main";
      isRedirect = true;
    } else {
      //로그인 실패("아이디나 비밀번호를 확인하세요" 메시지를 로그인 화면으로 포워드)
      request.setAttribute("error", "아이디나 비밀번호를 확인하세요");
      view = "/view/user/login.jsp";
    }
    
    return new ActionForward(view, isRedirect);
  }
  
  @Override
  public ActionForward logout(HttpServletRequest request) {
    //세션 초기화
    request.getSession().invalidate();
    
    //메인 화면(/index.jsp)으로 이동(리다이렉트)
    return new ActionForward(request.getContextPath() + "/main", true);
  }
  
}
