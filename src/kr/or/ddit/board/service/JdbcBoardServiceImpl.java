package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.IJdbcBoardDao;
import kr.or.ddit.board.dao.JdbcBoardDaoImpl;
import kr.or.ddit.board.vo.JdbcBoardVO;

public class JdbcBoardServiceImpl implements IJdbcBoardService {
	
	private IJdbcBoardDao boardDao;
	
	private static JdbcBoardServiceImpl service;
	
	private JdbcBoardServiceImpl() {
		boardDao = JdbcBoardDaoImpl.getInstance();
	}

	public static JdbcBoardServiceImpl getInstance() {
		if(service == null)
			service = new JdbcBoardServiceImpl();
		return service;
	}
	
	@Override
	public int insertBoard(JdbcBoardVO boardVo) {
		return boardDao.insertBoard(boardVo);
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		return boardDao.getAllBoardList();
	}

	@Override
	public List<JdbcBoardVO> getSearchBoard(String keyWord) {
		return boardDao.getSearchBoard(keyWord);
	}

	@Override
	public JdbcBoardVO getReadBoard(int boardNo) {
		boardDao.updateBoardCnt(boardNo);
		return boardDao.getReadBoard(boardNo);
	}

	@Override
	public int updateBoard(JdbcBoardVO boardVo) {
		return boardDao.updateBoard(boardVo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return boardDao.deleteBoard(boardNo);
	}

	@Override
	public int getBoardCount(int boardNo) {
		return boardDao.getBoardCount(boardNo);
	}

}
