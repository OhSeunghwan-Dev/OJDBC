package comment.service;

import java.sql.SQLException;
import java.util.ArrayList;

import comment.dao.CommentDAO;
import comment.dto.CommentDTO;

public class CommentService {

	CommentDAO commentDAO = new CommentDAO();
	
	public void createComment(CommentDTO commentDTO)  throws SQLException {
		commentDAO.createComment(commentDTO);
	}

	public ArrayList<CommentDTO> getCommentsByBid(int bid) throws SQLException {
		return commentDAO.getCommentsByBid(bid);
	}

}
