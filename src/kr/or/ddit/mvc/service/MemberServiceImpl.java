package kr.or.ddit.mvc.service;

import java.util.List;

import kr.or.ddit.mvc.dao.IMemberDao;
import kr.or.ddit.mvc.dao.MemberDaoImpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	private IMemberDao memDao;

	public MemberServiceImpl() {
		memDao = new MemberDaoImpl();
	}
	
	@Override
	public int insertMember(MemberVO memVo) {
		return memDao.insertMember(memVo);
	}

	@Override
	public int deletetMember(String memId) {
		return memDao.deletetMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) {
		return memDao.updateMember(memVo);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		return memDao.getAllMemberList();
	}

	@Override
	public int getMemberCount(String memId) {
		return memDao.getMemberCount(memId);
	}

}
