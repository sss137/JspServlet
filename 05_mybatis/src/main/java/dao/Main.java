package dao;

import java.util.Map;

import model.dto.BoardDTO;
import model.dto.UserDTO;

public class Main {

  public static void main(String[] args) {

    BoardDao dao = BoardDao.getInstance();
    
//    Map<String, Object> map = Map.of("target", "title", "query", "호랑이");
//    for(BoardDTO foundBoard : dao.findBoardsUsingQuery(map)) {
//      System.out.println(foundBoard);
//    }
    
//    int bid = 1;
//    System.out.println(dao.getBoardById(bid));
    
//    System.out.println(dao.getBoardCount());
    
//    String sort = "ASC";
//    for(BoardDTO board : dao.getBoards(sort)) {
//      System.out.println(board);
//    }
    
//    Map<String, Object> map2 = Map.of(
//        "target", "title", 
//        "query", "상어", 
//        "beginDate", "2025-07-24", 
//        "endDate", "2025-07-25"
//    );
//    for(BoardDTO foundBoard : dao.findBoards1(map2)) {
//      System.out.println(foundBoard);
//    }
    
//    Map<String, Object> map2 = Map.of(
//        "title", "", 
//        "content", "나", 
//        "beginDate", "2025-07-24", 
//        "endDate", "2025-07-25"
//    );
//    for(BoardDTO foundBoard : dao.findBoards2(map2)) {
//      System.out.println(foundBoard);
//    }
    
  }

}
