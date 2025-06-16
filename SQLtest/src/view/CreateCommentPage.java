package view;

import java.sql.SQLException;
import java.util.Scanner;

import board.dto.PostDTO;
import comment.controller.CommentController;
import comment.dto.CommentDTO;
import member.dto.MemberDTO;

public class CreateCommentPage {

	Scanner sc = new Scanner(System.in);
	CommentController commentController = new CommentController();
	
	public void show(MemberDTO session, PostDTO postDTO) throws SQLException {
		
		CommentDTO commentDTO = new CommentDTO();
		
		commentDTO.setCauthor(session.getId());
		commentDTO.setBid(postDTO.getBid());
		
		System.out.println("댓글 장성하기");
		System.out.print(">>> ");
		commentDTO.setCcontent(sc.nextLine());
		
		commentController.createComment(commentDTO);
	}
}
