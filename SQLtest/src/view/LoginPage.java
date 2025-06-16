package view;

import java.sql.SQLException;
import java.util.Scanner;

import member.controller.MemberController;
import member.dto.MemberDTO;

public class LoginPage {
	
	Scanner sc = new Scanner(System.in);
	MemberController memberController = new MemberController();
	MemberDTO memberDTO = new MemberDTO();
	
	public MemberDTO show() throws SQLException {
		
		System.out.println("======로그인======");
		System.out.print("아이디 : ");
		memberDTO.setId(sc.next());
		System.out.print("비밀번호 : ");
		memberDTO.setPw(sc.next());
		
		if (memberController.login(memberDTO) != null) {
			return memberController.login(memberDTO);
		}
		
		return null;
	}
}
