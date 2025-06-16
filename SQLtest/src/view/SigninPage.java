package view;

import java.sql.SQLException;
import java.util.Scanner;

import member.controller.MemberController;
import member.dto.MemberDTO;
import member.service.MemberService;

public class SigninPage {

	Scanner sc = new Scanner(System.in);
	MemberController memberController = new MemberController();
	MemberService memberService = new MemberService();
	MemberDTO memberDTO = new MemberDTO();
	
	public void show() throws SQLException {
		
		System.out.println("======회원가입======");
		
		while (true) {
			System.out.print("아이디 : ");
			memberDTO.setId(sc.next());
			if (!memberService.isDuplicatedId(memberDTO.getId())) break;
			System.out.println("이미 존재하는 아이디입니다.");
		}
		System.out.print("비밀번호 : ");
		memberDTO.setPw(sc.next());
		System.out.print("성별(male/female) : ");
		memberDTO.setGender(sc.next());
		sc.nextLine();
		System.out.print("주소 : ");
		memberDTO.setAddress(sc.nextLine());
		System.out.print("휴대폰 번호 : ");
		memberDTO.setPhoneNumber(sc.next());
		System.out.print("이메일 : ");
		memberDTO.setEmail(sc.next());
		
		memberController.signin(memberDTO);
	}

}
