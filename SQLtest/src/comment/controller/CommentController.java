package comment.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import board.dto.PostDTO;
import comment.dto.CommentDTO;
import comment.service.CommentService;

public class CommentController {
	
	CommentService commentService = new CommentService();

	public void createComment(CommentDTO commentDTO) throws SQLException {
		commentService.createComment(commentDTO);
	}

	public ArrayList<CommentDTO> getCommentsByBid(PostDTO postDTO) throws SQLException {
		return commentService.getCommentsByBid(postDTO.getBid());
	}
	

}
