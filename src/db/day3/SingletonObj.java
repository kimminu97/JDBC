package db.day3;

public class SingletonObj {
//싱글턴과 static의 차이는 싱글턴 패턴은 일반적인 클래스처럼 인스턴스 메소드로 사용하고 메모리에 저장하는 공간이 다릅니다.
// 프레임웍에서는 직접 코딩안해도 됩니다.
	//1. 자기자신의 객체를 new연산생성하여 private 접근한정자 변수로 참조합니다.
	private static SingletonObj object = new SingletonObj();
	
	//2. 생성자도 private 입니다. -> 다른 클래스에서 new SingletonObj()를 못합니다.
	private SingletonObj() {
		
	}

	//3. getInstance() 메소드정의 : 자기자신 1번 객체를 리턴합니다.
	public static SingletonObj getInstance() {
		return object;
	}
	
	//테스트 메소드
	public void print() {
		System.out.println("이 객체는 싱글턴 패턴 객체입니다.");
	}
}
