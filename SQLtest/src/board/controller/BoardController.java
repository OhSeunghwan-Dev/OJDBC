package board.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import board.dto.PostDTO;
import board.service.BoardService;
import member.dto.MemberDTO;

public class BoardController {
	
	BoardService boardService = new BoardService();
	
	public ArrayList<PostDTO> getAllPost() throws SQLException {
		return boardService.getAllPost();
	}

	public void createPost(PostDTO postDTO) throws SQLException {
		boardService.creatPost(postDTO);
	}

	public ArrayList<PostDTO> getMyPosts(MemberDTO session) throws SQLException {
		return boardService.getPostsById(session.getId());
	}

	public ArrayList<PostDTO> deletePost(PostDTO postDTO, ArrayList<PostDTO> postList) throws SQLException {
		return boardService.deletePost(postDTO, postList);
	}

	public void updatePost(PostDTO postDTO) throws SQLException {
		boardService.updatePost(postDTO);
	}
	
	

}
