package board.service;

import java.sql.SQLException;
import java.util.ArrayList;

import board.dao.BoardDAO;
import board.dto.PostDTO;

public class BoardService {
	
	BoardDAO boardDAO = new BoardDAO();

	public ArrayList<PostDTO> getAllPost() throws SQLException {
		return boardDAO.getAllPost();
	}

	public void creatPost(PostDTO postDTO) throws SQLException {
		boardDAO.createPost(postDTO);
	}

	public ArrayList<PostDTO> getPostsById(String id) throws SQLException {
		return boardDAO.getPostsById(id);
	}

	public ArrayList<PostDTO> deletePost(PostDTO postDTO, ArrayList<PostDTO> postList) throws SQLException {
		boardDAO.deletePost(postDTO);
		postList.remove(postDTO);
		return postList;
	}

	public void updatePost(PostDTO postDTO) throws SQLException {
		boardDAO.updatePost(postDTO);
	}

}
