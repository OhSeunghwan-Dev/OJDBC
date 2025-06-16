package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import board.dto.PostDTO;
import comment.controller.CommentController;
import comment.dto.CommentDTO;

public class CommentListPage {
	
	Scanner sc = new Scanner(System.in);
	CommentController commentController = new CommentController();
	
	public void show(PostDTO postDTO) throws SQLException {
		
		ArrayList<CommentDTO> commentList = commentController.getCommentsByBid(postDTO);
		
		while (true) {
			
			List<Integer> numList = new ArrayList<>();
			int index = 1;
			
			if (commentList.isEmpty()) {
				System.out.println("\n등록된 댓글이 없습니다.\n");
				break;
			}
			else {
				for (CommentDTO commentDTO : commentList) {
					System.out.println(index + ".\t" + commentDTO.getCcontent() + "\t\t" + commentDTO.getCauthor() + "\t" + commentDTO.getCdate());
					System.out.println("------------------------------------------------------------------------------------------------------------------\n");
					numList.add(index++);
				}
				System.out.println("뒤로 가시려면 exit 을 입력하세요");
				System.out.print(">>>");
				String select = sc.next();
				
				if (select.equals("exit"))	break;
				else						System.out.println("잘못된 입력입니다.");
			}
		}
		
		
	}

}
