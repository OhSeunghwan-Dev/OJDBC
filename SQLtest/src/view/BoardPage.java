package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import board.controller.BoardController;
import board.dto.PostDTO;
import member.dto.MemberDTO;

public class BoardPage {

	Scanner sc = new Scanner(System.in);
	BoardController boardController = new BoardController();
	
	public void show(MemberDTO session) throws SQLException {
		
		boolean run = true;
		ArrayList<PostDTO> postList = new ArrayList<>();
		
		postList = boardController.getAllPost();
		
		while (run) {
			
			List<Integer> numList = new ArrayList<>();
			int index = 1;
			
			System.out.println("\n======MBC 게시판======\n");
			if (postList.isEmpty()) {
				System.out.println("등록된 게시물이 없습니다.");
				run = false;
			}
			else {
				for (PostDTO post : postList) {
					System.out.println(index + ".\t" + post.getBtitle() + "\t\t" + post.getBauthor() + "\t" + post.getBdate());
					numList.add(index++);
				}
				
				System.out.println("\n자세히 보고싶은 게시물의 번호를 입력하세요");
				System.out.println("뒤로 가고싶으면 exit 을 입력하세요");
				System.out.print(">>> ");
				String select = sc.next();
				
				try {
					if (select.equals("exit")) {run = false;}
					else if (numList.contains(Integer.parseInt(select))) {
						
						PostDTO postDTO = postList.get(Integer.parseInt(select) - 1);
						postList = new PostPage().show(postDTO, session, postList);
						
					}
					
				} catch (NumberFormatException e) {
					System.out.println("존재하지 않는 게시물 번호입니다.");
				}
			}
		}
	}
}
