package view;

import java.sql.SQLException;
import java.util.ArrayList;

import member.controller.MemberController;
import member.dto.MemberDTO;

public class ShowMemberListPage {
	
	MemberController memberController  = new MemberController();
	
	public void show() throws SQLException {
		
		ArrayList<MemberDTO> memberList = new ArrayList<>();
		memberList = memberController.getAllMember();
		
		System.out.println("아이디\t 성별\t 주소\t\t 번호\t 이메일\t 가입일");
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
