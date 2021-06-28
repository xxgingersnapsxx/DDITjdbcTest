package kr.or.ddit.mvc.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.dao.IMemberDao;
import kr.or.ddit.mvc.dao.MemberDaoImpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {

	private IMemberDao memDao;

	// 1번
	private static MemberServiceImpl service;

	// 2번
	private MemberServiceImpl() {
		// singleton 사용
		memDao = MemberDaoImpl.getInstance();

		// singleton 사용하지 않음
		// memDao = new MemberDaoImpl();

	}

	// 3번
	public static MemberServiceImpl getInstance() {
		if (service == null)
			service = new MemberServiceImpl();
		return service;
	}
	
	
	
	
	@Override
	public int insertMember(MemberVO memVo) {
		return memDao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) {
		return memDao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) {
		return memDao.updateMember(memVo);
	}

	@Override
	public int updateMember2(Map<String, String> paraMap) {
		return memDao.updateMember2(paraMap);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		return memDao.getAllMemberList();
	}

	@Override
	public int getMemberCount(String memId) {
		return memDao.getMemberCount(memId);
	}

	@Override
	public void createExcelFile(List memList, String filePath) {
		memDao.createExcelFile(memList, filePath);
	}

	
}
