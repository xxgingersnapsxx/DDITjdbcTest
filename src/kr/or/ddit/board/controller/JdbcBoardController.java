package kr.or.ddit.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.board.service.IJdbcBoardService;
import kr.or.ddit.board.service.JdbcBoardServiceImpl;
import kr.or.ddit.board.vo.JdbcBoardVO;

public class JdbcBoardController {
	private Scanner scan = new Scanner(System.in);
	private IJdbcBoardService service;

	public JdbcBoardController() {
		service = JdbcBoardServiceImpl.getInstance();
	}

	public static void main(String[] args) {
		new JdbcBoardController().boardStart();
	}

	private void boardStart() {
		while (true) {
			int choice = displayMenu();
			switch (choice) {
			case 1: // 새 글 작성
				insertBoard();
				break;
			case 2: // 게시글 보기
				readBoard();
				break;
			case 3: // 검색
				searchBoard();
				break;
			case 0: // 종료
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("번호를 잘못 입력 했습니다.");
				System.out.println("다시 입력 하세요.");
			}
		}

	}

	/**
	 * 선택한 게시글을 조회하는 메서드
	 */
	private void readBoard() {
		System.out.print("보기를 원하는 게시물 번호 입력 >> ");
		int boardNo = scan.nextInt();
		System.out.println();
		
		
		JdbcBoardVO boardVo = service.getReadBoard(boardNo);
		
		int cnt = service.getBoardCount(boardNo);
		if (cnt == 1) {
			System.out.println(" " + boardNo + "번글 내용");
			System.out.println("------------------------------------------------------------");
			System.out.println("- 제  목 : " + boardVo.getBoard_title());
			System.out.println("- 작성자 : " + boardVo.getBoard_writer());
			System.out.println("- 내  용 : " + boardVo.getBoard_content());
			System.out.println("- 작성일 : " + boardVo.getBoard_date());
			System.out.println("- 조회수 : " + boardVo.getBoard_cnt());
			System.out.println("-------------------------------------------------------------");

			while (true) {
				int choice = displayContentMenu();
				switch (choice) {
				case 1: // 수정
					updateContent(boardNo);
					break;
				case 2: // 삭제
					service.deleteBoard(boardNo);
					break;
				case 3: // 리스트로 가기
					System.out.println("목록으로 돌아갑니다..");
					return;
				default:
					System.out.println("번호를 잘못 입력 했습니다.");
					System.out.println("다시 입력 하세요.");
				}
			}
		} else {
			System.out.println(boardNo + "번 글이 없습니다.");
		}
	}

	
	/**
	 * 게시글 수정 메소드
	 */
	private void updateContent(int boardNo) {
		scan.nextLine();
		System.out.println();
		System.out.println("수정 작업하기");
		System.out.println("-----------------------------------");
		System.out.print("- 제  목 : ");
		String boardTitle = scan.nextLine();
		System.out.print("- 내  용 : ");
		String boardContent = scan.nextLine();
		System.out.println();
		
		JdbcBoardVO boardVo = new JdbcBoardVO();
		
		boardVo.setBoard_no(boardNo);
		boardVo.setBoard_title(boardTitle);
		boardVo.setBoard_content(boardContent);
		
		int cnt = service.updateBoard(boardVo);
		
		if (cnt > 0) {
			System.out.println(boardNo + "번글이 수정되었습니다.");
		} else {
			System.out.println("수정 작업 실패!!");
		}
	}

	private int displayContentMenu() {
		System.out.println("메뉴 : 1. 수정    2. 삭제    3. 리스트로 가기");
		System.out.print("작업선택 >> ");
		
		return scan.nextInt();
	}

	/**
	 * 게시글을 검색하는 메서드
	 */
	private void searchBoard() {
		System.out.println();
		System.out.println("검색 작업");
		System.out.println("--------------------------------------------");
		System.out.print("- 검색할 제목 입력 : ");
		scan.nextLine();
		String keyWord = scan.nextLine();
		
		System.out.println("-------------------------------------------------------------");
		System.out.println(" No	        제 목            작성자 	조회수   ");
		System.out.println("-------------------------------------------------------------");
		
		List<JdbcBoardVO> boardList = service.getSearchBoard(keyWord);
		
		if (boardList == null || boardList.size() == 0) {
			System.out.println("출력할 게시물이 하나도 없습니다.");
		} else {
			for(JdbcBoardVO boardVo : boardList) {
				System.out.println(boardVo.getBoard_no() + "\t\t" + boardVo.getBoard_title() + "\t\t" + boardVo.getBoard_writer() + "\t\t" + boardVo.getBoard_cnt());
			}
			System.out.println("-------------------------------------------------------------");
		}
		System.out.println();
	}

	/**
	 * 게시글을 추가하는 메서드
	 */
	private void insertBoard() {
		System.out.println();
		scan.nextLine();
		System.out.println("새글 작성하기");
		System.out.println("-----------------------------------");
		System.out.print("- 제  목 : ");
		String boardTitle = scan.nextLine();
		System.out.print("- 작성자 : ");
		String boardWriter = scan.next();
		scan.nextLine();
		System.out.print("- 내  용 : ");
		String boardContent = scan.nextLine();

		// 입력 데이터가 저장될 VO 객체 생성
		JdbcBoardVO boardVo = new JdbcBoardVO();

		boardVo.setBoard_title(boardTitle);
		boardVo.setBoard_writer(boardWriter);
		boardVo.setBoard_content(boardContent);

		int cnt = service.insertBoard(boardVo);
		if (cnt > 0) {
			System.out.println("게시글 추가 성공!!!");
		} else {
			System.out.println("게시글 추가 실패~~");
		}
	}

	/**
	 * 메뉴를 출력하고 실행할 작업번호를 입력받아서 반환하는 메서드
	 * 
	 * @return
	 */
	private int displayMenu() {
		List<JdbcBoardVO> boardList = service.getAllBoardList();
		
		System.out.println("-------------------------------------------------------------");
		System.out.println(" No	        제 목            작성자 	조회수   ");
		System.out.println("-------------------------------------------------------------");
		
		
		if (boardList == null || boardList.size() == 0) {
			System.out.println("출력할 게시물이 하나도 없습니다.");
		} else {
			for(JdbcBoardVO boardVo : boardList) {
				System.out.println(boardVo.getBoard_no() + "\t\t" + boardVo.getBoard_title() + "\t\t" + boardVo.getBoard_writer() + "\t\t" + boardVo.getBoard_cnt());
			}
		}
		
		System.out.println();
		System.out.println("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
		System.out.print("작업 선택 >> ");
		return scan.nextInt();
	}

}
