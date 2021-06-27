package kr.or.ddit.assignment;

import java.util.ArrayList;

public class test {
	public static void main(String[] args) {
		ArrayList<MemberVO> memVoData = new ArrayList<>();
		memVoData.add(new MemberVO("memId1", "memPass1", "memName1", "memTel1", "memAddr1"));
		memVoData.add(new MemberVO("memId2", "memPass2", "memName2", "memTel2", "memAddr2"));
		memVoData.add(new MemberVO("memId3", "memPass3", "memName3", "memTel3", "memAddr3"));
		memVoData.add(new MemberVO("memId4", "memPass4", "memName4", "memTel4", "memAddr4"));
		
		System.out.println(memVoData.size());
		System.out.println(memVoData.get(1).getMem_name());
	}
	

}
