/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		
		// 테스트 케이스 개수 T 입력받기
		int t = sc.nextInt();
		
		// T만큼 반복하기
		for (int testCase = 0; testCase < t; testCase++) {
			// 크가 10인 배열 arr 만들기
			int[] arr = new int[10];
			
			// 배열 요소 입력받기
			for (int i = 0; i < arr.length; i++) {
				arr[i] = sc.nextInt();
			}
			
			// 버블정렬
			// 어차피 제일 큰 값 하나만 꺼내면 되니까 pass 딱 한 번만 실행
			for (int i = 0; i < arr.length - 1; i++) {
				// 오름차순 정렬
				// 왼쪽 값이 오른쪽 값보다 클 경우 swap
				if (arr[i] > arr[i + 1]) {
					int tmp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = tmp;
				}
			}
			
			// 테스트케이스 번호 + 최대수 출력
			System.out.println("#" + (testCase + 1) + " " + arr[arr.length - 1]);
			
		}
        
        
        
        
       
	}
}