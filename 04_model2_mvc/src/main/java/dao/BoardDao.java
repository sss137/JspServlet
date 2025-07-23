package dao;

import java.sql.Connection;
import java.util.List;

import model.dto.BoardDTO;

public interface BoardDao {

  public abstract Connection getConnection();
  public abstract void close(); 
  
  public abstract List<BoardDTO> getBoards();
  public abstract BoardDTO getBoardById(int bid);
  public abstract int insertBoard(BoardDTO board);
  public abstract int deleteBoard(int bid);
  public abstract int updateBoard(BoardDTO board);
  
}
