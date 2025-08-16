package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.dto.BoardDTO;
import model.dto.UserDTO;

/*
 * DAO(Data Access Object)
 * 1. 데이터베이스와의 연결, 데이터 생성/조회/수정/삭제(CRUD) 등 데이터 접근 작업을 수행하는 객체입니다.
 * 2. 비지니스 로직과 데이터베이스 로직을 분리하여 코드 구조화, 쉬운 유지보수, 코드 재사용성 등을 얻을 수 있습니다.
 * 3. 인터페이스를 통해 DAO 객체를 생성하면 구현체를 자유롭게 변경할 수 있습니다.
 */
public class BoardDAO {

  /*
   * Singleton Pattern으로 객체 생성하기(애플리케이션 전체에서 객체를 하나만 제공)
   * 1. Private 생성자(외부에서는 BoardDAO 객체 생성 금지)
   * 2. 내부에서 BoardDAO 객체 생성
   * 3. 생성한 BoardDAO 객체를 반환하는 메소드 제공
   */
  
  private BoardDAO() {}
  
  private static BoardDAO dao = new BoardDAO();
  
  public static BoardDAO getInstance() {
    return dao;
  }
  
  //모든 메소드가 공통으로 사용할 필드
  private Connection con;
  private PreparedStatement ps;
  private ResultSet rs;
  private String sql;

  //조회(목록)
  public List<BoardDTO> getBoards() {
    List<BoardDTO> boards = new ArrayList<>();
    try {
      con = DBUtils.getConnection();
        StringBuilder sb = new StringBuilder(); 
        sb.append("SELECT b.bid, u.uid, u.nickname, b.title, b.content, b.created_at, b.modified_at");
        sb.append("  FROM tbl_board b INNER JOIN tbl_user u");
        sb.append("    ON b.uid = u.uid");
        sb.append(" ORDER BY bid DESC");
        sb.append(" LIMIT 0, 10");
        sql = sb.toString();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
          
        //DB에서 가져온 결과 rs
        while(rs.next()) {
          int bid = rs.getInt(1);
          int uid = rs.getInt(2);
          String nickname = rs.getString(3);
          String title = rs.getString(4);
          String content = rs.getString(5);
          Timestamp createdAt = rs.getTimestamp(6);
          Timestamp modifiedAt = rs.getTimestamp(7);
          UserDTO user = new UserDTO();
          user.setUid(uid);
          user.setNickname(nickname);
          BoardDTO board = new BoardDTO();
          board.setBid(bid);
          board.setUser(user);
          board.setTitle(title);
          board.setContent(content);
          board.setCreatedAt(createdAt);
          board.setModifiedAt(modifiedAt);
          //변환된 BoardDTO를 List에 저장
          boards.add(board);
            
      }
    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      DBUtils.close(con, ps, rs);
    }
    return boards;
    
  }
  
  //조회(세부 항목)
  public BoardDTO getBoardById(int bid) {
    BoardDTO board = null;
    try {
      con = DBUtils.getConnection();
      StringBuilder sb = new StringBuilder(); 
      sb.append("SELECT b.bid, u.uid, u.nickname, b.title, b.content, b.created_at, b.modified_at");
      sb.append("  FROM tbl_board b INNER JOIN tbl_user u");
      sb.append("    ON b.uid = u.uid");
      sb.append(" WHERE bid = ?");
      sql = sb.toString();
      ps = con.prepareStatement(sql);
      ps.setInt(1, bid);               //쿼리문의 1번째 ?에 bid 전달하기
      rs = ps.executeQuery();
      
      //DB에서 가져온 결과 ResultSet를 BoardDTO로 변환
      if(rs.next()) {
        UserDTO user = new UserDTO();
        user.setUid(rs.getInt(2));
        user.setNickname(rs.getString(3));
        board = new BoardDTO();
        board.setBid(rs.getInt(1));
        board.setUser(user);
        board.setTitle(rs.getString("title"));
        board.setContent(rs.getString("content"));
        board.setCreatedAt(rs.getTimestamp("created_at"));
        board.setModifiedAt(rs.getTimestamp("modified_at"));
        
      }
    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      DBUtils.close(con, ps, rs);
    }
    return board;
    
  }
  
  //삽입(삽입된 행의 개수 반환)
  public int insertBoard(BoardDTO board) {
    int count = 0;
    try {
      con = DBUtils.getConnection();
      sql = "INSERT INTO tbl_board(uid, title, content) VALUES (?, ?, ?)";
      ps = con.prepareStatement(sql);
      ps.setInt(1, board.getUser().getUid());
      ps.setString(2, board.getTitle());
      ps.setString(3, board.getContent());
      count = ps.executeUpdate();
      
    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      DBUtils.close(con, ps, rs);
    }
    return count;
    
  }
  
  //삭제(삭제된 행의 개수 반환)
  public int deleteBoard(int bid) {
    int count = 0;
    try {
      con = DBUtils.getConnection();
      sql = "DELETE FROM tbl_board WHERE bid = ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, bid);
      count = ps.executeUpdate();
      
    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      DBUtils.close(con, ps, rs);
    }
    return count;
    
  }
  
}







