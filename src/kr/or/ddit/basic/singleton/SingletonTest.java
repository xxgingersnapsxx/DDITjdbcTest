package kr.or.ddit.basic.singleton;

public class SingletonTest {

	public static void main(String[] args) {
		// MySingleton test1 = new MySingleton(); // 외부에서 new 명령으로 객체 생성 불가
		
		MySingleton test2 = MySingleton.getInstance();	// MySingleton()객체 생성
		MySingleton test3 = MySingleton.getInstance();	
		
		// test2와 test3는 같은 객체이다.
		System.out.println("test2 : " + test2.toString());
		System.out.println("test3 : " + test3.toString());
		System.out.println(test2 == test3);
		
		// 객체 생성 후 Method 호출
		test2.displayTest();
	}

}
