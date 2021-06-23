package kr.or.ddit.mvc.service;

import java.util.List;

import kr.or.ddit.mvc.vo.MemberVO;

/**
 * Service객체는 DAO에 설정된 메서드를 원하는 작업에 맞게 호출하여
 * 결과를 받아오고, 받아온 결과 자료를 Controller에게 보내주는 역할을 한다.
 * 
 * 보통 DAO의 메서드 구조와 같게 만든다.
 *
 */
public interface IMemberService {
	/**
	 * MemberVO에 담겨진 데이터를 DB에 insert하는 메서드
	 * @param memVo DB에 insert할 자료가 저장될 MemberVO객체
	 * @return 작업 성공 : 1이상, 작업 실패 : 0
	 */
	public int insertMember(MemberVO memVo);

	/**
	 * 회원ID를 매개변수로 받아서 해당 회원 정보를 삭제하는 메서드
	 * @param memId 삭제할 회원 ID
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int deletetMember(String memId);

	/**
	 * MemberVO자료를 이용하여 DB에 update하는 메서드
	 * @param MemberVO update할 회원정보가 저장된 MemberVO객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateMember(MemberVO memVo);

	/**
	 * DB의 MYMEMBER테이블의 전체 레코드를 가져와서 List에 담아 반환하는 메서드
	 * @return 검색된 데이터가 저장된 List객체
	 */
	// 한 회원의 자료는 MemberVO에 들어가므로 여러명의 자료는 List에 MemberVO 타입으로 저장된다.
	public List<MemberVO> getAllMemberList();
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원의 개수를 반환하는 메서드
	 * @param memId 검색할 회원ID
	 * @return 검색된 회원의 갯수
	 */
	public int getMemberCount(String memId);
}
