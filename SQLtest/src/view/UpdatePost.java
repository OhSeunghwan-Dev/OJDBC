package view;

import java.sql.SQLException;
import java.util.Scanner;

import board.controller.BoardController;
import board.dto.PostDTO;

public class UpdatePost {

	Scanner sc = new Scanner(System.in);
	BoardController boardController = new BoardController();
	
	public void show(PostDTO postDTO) throws SQLException {
		
		System.out.print("수정할 제목 : ");
		postDTO.setBtitle(sc.nextLine());
		System.out.print("수정할 내용 : ");
		postDTO.setBcontent(sc.nextLine());
		
		boardController.updatePost(postDTO);
	}
}
