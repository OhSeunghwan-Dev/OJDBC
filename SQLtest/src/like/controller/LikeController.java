package like.controller;

import java.sql.SQLException;

import board.dto.PostDTO;
import like.service.LikeService;
import member.dto.MemberDTO;

public class LikeController {

	LikeService likeService = new LikeService();
	
	public boolean likePost(MemberDTO session, PostDTO postDTO) throws SQLException {
		return likeService.likePost(session.getId(), postDTO.getBid());			
	}

	public int countLike(PostDTO postDTO) throws SQLException {
		return likeService.countLike(postDTO.getBid());
	}
	
}
