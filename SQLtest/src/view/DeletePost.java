package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import board.controller.BoardController;
import board.dto.PostDTO;

public class DeletePost {
	
	Scanner sc = new Scanner(System.in);
	BoardController boardController = new BoardController();
	
	public ArrayList<PostDTO> show(PostDTO postDTO, ArrayList<PostDTO> postList) throws SQLException {
		
		while (true) {
			System.out.println("정말로 삭제하시겠습니까? (yes/no)");
			System.out.print(">>> ");
			String select = sc.next();
			
			if (select.equals("yes")) {
				postList = boardController.deletePost(postDTO, postList);
				break;
			}
			else if (select.equals("no"))	break;
		}
		
		return postList;
	}

}
