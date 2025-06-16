package view;

import java.sql.SQLException;
import java.util.ArrayList;

import board.dto.PostDTO;
import member.controller.MemberController;
import member.dto.MemberDTO;

public class LikeMemberListPage {
	
	MemberController memberController = new MemberController();
	
	public void show(PostDTO postDTO) throws SQLException {
		
		ArrayList<MemberDTO> memberList = memberController.getLikeMemberList(postDTO);
		
		if (memberList.isEmpty()) {
			System.out.println("좋아요를 누른 사용자가 없습니다.");
		}
		else { 
			System.out.println("아이디\t 성별\t 주소\t\t 번호\t\t 이메일\t\t 가입일");
			for (MemberDTO memberDTO : memberList) {
				System.out.println(memberDTO.getId() + "\t " +
								   memberDTO.getGender() + "\t " +
								   memberDTO.getAddress() + "\t " + 
								   memberDTO.getPhoneNumber() + "\t " +
								   memberDTO.getEmail() + "\t" + 
								   memberDTO.getRegDate());
			}
		}
		
	}
	
}
