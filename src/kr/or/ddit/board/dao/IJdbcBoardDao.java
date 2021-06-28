package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.JdbcBoardVO;

public interface IJdbcBoardDao {
	/**
	 * JdbcBoardVO에 담겨진 데이터를 DB에 insert 하는 메서드
	 * @param boardVo  DB에 insert할 자료가 저장될 MemberVO객체
	 * @return 작업 성공 : 1이상, 작업실패 : 0
	 */
	public int insertBoard(JdbcBoardVO boardVo);
	
	/**
	 * DB의 게시판 테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * @return 전체 데이터가 저장된 List객체
	 */
	public List<JdbcBoardVO> getAllBoardList();
	
	/**
	 * DB의 게시판 테이블에서 keyWord를 포함하는 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * @param keyWord 검색어
	 * @return 전체 검색 결과 데이터가 저장된 List객체
	 */
	public  List<JdbcBoardVO> getSearchBoard(String keyWord);
	
	/**
	 * DB의 게시판 테이블에서 입력받은 boardNo에 해당하는 게시글 하나를 보여주는 메서드
	 * @param boardNo 게시글 번호
	 * @return 게시글 내용
	 */
	public JdbcBoardVO getReadBoard(int boardNo);
	
	
	/**
	 * JdbcBoardVO 자료를 이용하여 DB에 update하는 메서드
	 * @param boardVo update할 게시글정보가 저장된 JdbcBoardVO객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateBoard(JdbcBoardVO boardVo);
	
	/**
	 * 게시글 번호를 매개변수로 받아서 해당 게시글을 삭제하는 메서드
	 * @param boardNo 삭제할 게시글 번호
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int deleteBoard(int boardNo);
	
	/**
	 * 검색어를 매개변수로 받아서 해당하는 게시글의 개수를 반환하는 메서드
	 * @param keyWord 검색어
	 * @return 검색된 게시글의 갯수
	 */
	public int getBoardCount(int boardNo);

	/**
	 * 게시글 번호를 받아 조회수를 업데이트 하는 메서드
	 * @param boardNo 게시글 번호
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateBoardCnt(int boardNo);
}
