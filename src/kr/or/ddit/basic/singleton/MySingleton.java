package kr.or.ddit.basic.singleton;
/* 
	Singleton 패턴 : 객체가 1개만 만들어지게 하는 디자인 패턴
					 (외부에서 new 명령을 사용하지 못하게 한다.)
	
	- Singleton 클래스를 만드는 방법(필수 구성 요소)
		1. 자기 자신 class의 참조값이 저장될 변수를 private static으로 선언한다.
		
		2. 생성자의 접근제한자를 private으로 한다. (외부에서 new명령을 못쓰는 이유) 
						 
		3. 자기 자신 class의 인스턴스를 생성하고 그 참조값을 반환하는 메서드를 public static으로 만든다.
		   (보통 메서드명은 'getInstance()' 로 한다.)
		   
		4. 기타 이 클래스가 처리할 내용들을 기술
	
	- 싱글톤을 사용하는 이유
		1. 메모리 낭비 방지
		2. 데이터의 공유가 필요할 때 
*/
public class MySingleton {
	// 1번 : 자기 자신 class의 참조값이 저장될 변수
	private static MySingleton single;

	// 2번 : 생성자
	private MySingleton() {
		System.out.println("싱글톤 클래스의 생성자");
	}

	// 3번 : 자기 자신 class의 인스턴스
	public static MySingleton getInstance() {
		// 자기 자신 class의 인스턴스 생성 및 반환 작업을 수행한다.
		if (single == null) { // 객체가 생성이 되었는지 확인
			single = new MySingleton();
		}
		return single;
	}
	
	// 기타 이 클래스가 처리할 내용들을 기술한다.
	public void displayTest() {
		System.out.println("싱글톤 클래스의 메서드 호출");
	}
}
