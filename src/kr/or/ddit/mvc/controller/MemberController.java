package kr.or.ddit.mvc.controller;

import java.util.Scanner;
// TODO : 수정, 과제
import kr.or.ddit.mvc.service.IMemberService;
import kr.or.ddit.mvc.service.MemberServiceImpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberController {
	private Scanner scan = new Scanner(System.in);
	private IMemberService service;

	public MemberController() {
		service = new MemberServiceImpl();
	}

	public static void main(String[] args) {
		new MemberController().memberStart();
	}

	private void memberStart() {
		while(true) {
			int choice = displayMenu();
			switch(choice) {
				case 1 :			// 추가
					insertMember();
					break;
				case 2 :			// 삭제
					deleteMember();
					break;
				case 3 :			// 수정
					updateMember();
					break;
				case 4 :			// 전체 자료 출력
					displayAllMember(); break;
				case 5 :			// 전체 자료 출력
					updateMember2(); break;
				case 0 :
					System.out.println("프로그램을 종료합니다.");
					return;
				default:
					System.out.println("번호를 잘못 입력 했습니다.");
					System.out.println("다시 입력 하세요.");
			}
		}
	}
	
	// 회원 정보를 추가하는 메서드
	private void insertMember() {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요");

		int count = 0; // 중복 여부를 검사하기 위한 변수 (회원ID개수가 저장될 변수)
		String memId = null;
		do {
			System.out.print("회원ID >> ");
			memId = scan.next();

			if (count > 0) {
				System.out.println(memId + "은(는) 이미 등록된 회원ID 입니다.");
				System.out.println("다른 회원ID를 입력하세요.");
			}

		} while (count > 0);

		System.out.print("패스워드 >> ");
		String memPass = scan.next();

		System.out.print("회원이름 >> ");
		String memName = scan.next();

		System.out.print("전화번호 >> ");
		String memTel = scan.next();

		scan.nextLine(); // 입력 버퍼 비우기
		System.out.print("회원주소 >> ");
		String memAddr = scan.nextLine();

		// 입력한 데이터가 저장될 VO객체 생성
		MemberVO memVo = new MemberVO();

		// 입력한 데이터를 VO에 저장한다.
		memVo.setMem_id(memId);
		memVo.setMem_pass(memPass);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);

		int cnt = service.insertMember(memVo);

		if (cnt > 0) {
			System.out.println("회원 추가 성공!!!");
		} else {
			System.out.println("회원 추가 실패!!!");
		}

	}
}
