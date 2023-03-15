import java.util.Arrays;
import java.util.Scanner;

// 순열과 조합
// 1759 암호만들기
// 골드5

// 문제
// 서로 다른 L개의 알파벳 소문자로 구성된 암호
// 암호는 최소 한 개의 모음 (a, e, i, o, u)과 최소 두 개의 자음으로 구성
// 암호는 알파벳이 증가하는 순서대로 배열됨
// 암호에 포함되었을 법한 C종류의 문자로 만들 수 있는 암호를 모두 구하라

// 조건
// (3 ≤ L ≤ C ≤ 15)

// 풀이
// 알파벳이 증가하는 순서대로 배열되니 순서가 없는 조합
// => [a, b]만 확인하고 [b, a]는 확인하지 않음
// : 백트래킹으로 조합을 만들자

// 암호의 길이 L만큼의 조합이 완성되면
// 현재의 조합이 암호의 조건을 만족하는지 확인하고
// 조건을 만족할 때만 출력 문자열에 더해주자

public class Main {

	static StringBuilder sb = new StringBuilder();

	static int pwLength, charCnt;
	static char[] alph, pw;
	static boolean[] isUsed;

	public static void dfs(int start, int depth) { // 깊이 우선 탐색 (백트래킹)
		// 암호의 길이만큼 조합을 만들어보자

		// 기저조건
		if (depth == pwLength) {
			// 암호의 길이만큼 암호 배열에 문자들을 조합해 넣었다면...
			// 현재 암호에 모음이 1개 이상, 자음이 1개 이상 들어갔는지 확인하자
			boolean isRight = isPassword();
			
			// 암호의 조건을 만족한다면 현재의 암호 조합을 출력하자
			if (isRight) {
				for (int pwIdx = 0; pwIdx < pwLength; pwIdx++) {
					sb.append(pw[pwIdx]);
				}
				sb.append("\n"); // 암호 하나 출력 후 개행
			}
			
			// 암호의 조건을 만족하든 못하든 일단 암호 길이만큼 조합이 끝났으니
			// 더 이상 재귀 호출의 깊이를 증가시키지 못하게 반환하자
			return;
		}
		
		// 암호의 길이만큼 조합할 수 없다면 그냥 반환하기
		// 암호가 오름차순으로 배열되어야 하기 때문에 추가되는 조건
		// 예제 1을 예로 들면, 암호가 t, s, w로 시작되면 무조건 이 조건에 걸리게 된다
		if (start == charCnt)
			return;
		
		for (int idx = start; idx < charCnt; idx++) {
			if (isUsed[idx]) continue; // 확인한 문자가 이미 사용됐으면 다음 문자 확인
			// 확인한 문자가 사용되지 않았다면...
			isUsed[idx] = true; // 사용했다고 바꿔주고
			pw[depth] = alph[idx]; // 암호 조합 배열에 저장하기
			dfs(idx + 1, depth + 1); // 암호 조합 배열의 다음 문자 찾으러 가기
			isUsed[idx] = false; // 재귀 호출이 끝났으면 다시 현재 문자를 미사용 상태로 돌려주기
		}

	}
	
	public static boolean isPassword() {
		// 현재 만든 암호 조합이 암호의 조건을 만족하는지 확인하는 메서드
		// 암호의 조건 : 모음 1개 이상, 자음 2개 이상
		
		int vowelCnt = 0; // 현재 만든 암호의 조합에 포함된 모음의 개수
		int consonantCnt = 0; // 현재 만든 암호의 조합에 포함된 자음의 개수
		
		for (int idx = 0; idx < pwLength; idx++) {
			char cur = pw[idx]; // 암호 조합에서 현재 확인할 문자 뽑아오기
			
			// 현재 확인한 문자가 모음이면 모음 개수+1
			if (cur == 'a' || cur == 'e' || cur == 'i' || cur == 'o' || cur == 'u')
				vowelCnt++;
			// 현재 확인한 문자가 모음이 아니면 자음 개수+1
			else
				consonantCnt++;
		}
		
		// 현재 암호 조합에 모음이 1개 이상, 자음이 2개 이상이라면 true를 반환하고
		if (vowelCnt >= 1 && consonantCnt >= 2)
			return true;
		// 해당 조건을 만족하지 못한다면 false를 반환하자
		else
			return false;
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		pwLength = sc.nextInt(); // 암호의 길이 L 입력받기
		charCnt = sc.nextInt(); // 암호에 사용했을 법한 문자의 종류 C 입력받기

		alph = new char[charCnt]; // C개의 알파벳을 저장할 배열 공간 만들기
		isUsed = new boolean[charCnt]; // 현재 확인할 문자의 사용 여부를 저장할 boolean 배열
		pw = new char[pwLength]; // 현재 조합한 암호를 저장할 배열 공간
		
		// C개의 문자 입력받기
		for (int cnt = 0; cnt < charCnt; cnt++) {
			alph[cnt] = sc.next().charAt(0);
		}
		sc.close(); // 입력이 끝났으니 스캐너 닫기

		// 입력되는 문자는 순서대로 들어온다는 조건이 없기 때문에
		// 입력이 끝난 후 alph 배열을 오름차순으로 정렬해주자
		Arrays.sort(alph);

		dfs(0, 0); // 백트래킹 실행

		System.out.println(sb); // 출력
	}

}
