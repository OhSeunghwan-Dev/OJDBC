package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import board.dto.PostDTO;
import like.controller.LikeController;
import member.dto.MemberDTO;

public class PostPage {
	
	Scanner sc = new Scanner(System.in);
	LikeController likeController = new LikeController();
	
	public ArrayList<PostDTO> show(PostDTO postDTO, MemberDTO session, ArrayList<PostDTO> postList) throws SQLException {
		
		int count = likeController.countLike(postDTO);
		
		while (true) {
			
			System.out.println("\n제목 : " + postDTO.getBtitle());
			System.out.println("작성자 : " + postDTO.getBauthor());
			System.out.println("등록일 : " + postDTO.getBdate());
			System.out.println("내용 : " + postDTO.getBcontent());
			System.out.println("좋아요 수 : " + count + "개\n");

			if (postDTO.getBauthor().equals(session.getId())) {
				System.out.println("1. 게시글 수정");
				System.out.println("2. 게시글 삭제");
				System.out.println("3. 댓글 보기");
				System.out.println("4. 댓글 달기");
				System.out.println("5. 좋아요한 사용자 목록 보기");
				System.out.println("6. 뒤로 가기");
				System.out.println("like 를 입력하면 게시물 좋아요가 됩니다.");
				System.out.print(">>> ");
				String select = sc.next();
				
				if (select.equals("1")) {
					new UpdatePost().show(postDTO);
					break;
				}
				else if (select.equals("2")) {
					postList = new DeletePost().show(postDTO, postList);
					break;
				}
				else if (select.equals("3"))	new CommentListPage().show(postDTO);
				else if (select.equals("4"))	new CreateCommentPage().show(session, postDTO);
				else if (select.equals("5"))	new LikeMemberListPage().show(postDTO);
				else if (select.equals("6"))	break;
				else if (select.equals("like"))	{
					if (!likeController.likePost(session, postDTO)) {
						System.out.println("이미 좋아요를 누른 게시물입니다.\n");
					}
					else {
						count++;
					}
				}
				else 							System.out.println("잘못된 입력입니다.");
				
			}
			else {
				System.out.println("1. 댓글 보기");
				System.out.println("2. 댓글 달기");
				System.out.println("3. 좋아요한 사용자 목록 보기");
				System.out.println("4. 뒤로 가기");
				System.out.println("like 를 입력하면 게시물 좋아요가 됩니다.");
				System.out.print(">>> ");
				String select = sc.next();
				
				if (select.equals("4"))			break;
				else if (select.equals("1"))	new CommentListPage().show(postDTO);
				else if (select.equals("2"))	new CreateCommentPage().show(session, postDTO);
				else if (select.equals("3"))	new LikeMemberListPage().show(postDTO);
				else if (select.equals("like")) {
					if (!likeController.likePost(session, postDTO)) {
						System.out.println("이미 좋아요를 누른 게시물입니다.");
					}
					else {
						count++;
					}
				}
				else 							System.out.println("잘못된 입력입니다.");
			}
			
			
		}
		return postList;
	}

}
