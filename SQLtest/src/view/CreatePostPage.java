package view;

import java.sql.SQLException;
import java.util.Scanner;

import board.controller.BoardController;
import board.dto.PostDTO;
import member.dto.MemberDTO;

public class CreatePostPage {
	
	Scanner sc = new Scanner(System.in);
	BoardController boardController = new BoardController();
	
	public void show(MemberDTO session) throws SQLException {
		
		PostDTO postDTO = new PostDTO();
		
		System.out.println("======게시글 쓰기======");
		System.out.print("제목 : ");
		postDTO.setBtitle(sc.nextLine());
		System.out.println("내용 : ");
		System.out.print(">>> ");
		postDTO.setBcontent(sc.nextLine());
		postDTO.setBauthor(session.getId());

		boardController.createPost(postDTO);
	}

}
