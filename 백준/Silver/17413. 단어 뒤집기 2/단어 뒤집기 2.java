import java.util.Scanner;

// 17413 단어 뒤집기 2
// 실버3

// 문제
// 문자열 S의 단어를 뒤집어서 출력하라

// 문자열 S의 규칙
// 알파벳 소문자, 숫자, 공백, <> 로만 이루어져 있다
// 문자열의 시작과 끝은 공백이 아니다
// <> 세트안의 내용은 단어가 아닌 태그다
//	=> <>는 무조건 한 쌍으로 나오며, 순서를 지켜서 등장한다
// 단어와 단어 사이에는 공백이 있고, 태그와 단어 사이에는 공백이 없다

// 조건
// 문자열 S의 길이는 100,000 이하

// 풀이
// 문자열을 char배열로 분리해서 입력받는다
// 배열의 0~length까지 순서대로 확인하면서...
// <가 나올 경우 다음 인덱스부터 확인해서 >가 나올 때까지 확인하고,
// 		>가 발견되면 > +1번 인덱스부터 다시 탐색한다
// 알파벳 소문자나 숫자가 나올 경우 다음 인덱스부터 확인해서 공백이나 <가 나올 때 까지 확인하고,
//		둘 중 하나가 발견되면 그 인덱스-1까지가 한 단어이다
// 	단어의 마지막인덱스 - 처음인덱스 +1 까지가 단어의 길이이다
//	idx = 0~단어의 길이 /2 만큼 반복하며 단어의 첫 인덱스 + idx와 단어의 마지막 인덱스 -idx의 자리를 바꿔준다
// 공백이 나올 경우 다음 인덱스를 확인한다


public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		char[] line = sc.nextLine().toCharArray(); // 문자열을 string으로 읽어와서 char배열로 바꿔서 저장
		
		int idx = 0; // 문자열에서 현재 확인할 인덱스를 나타낼 변수
		
		while (idx < line.length) { // 문자열 길이만큼 확인
			
			if (line[idx] == ' ') { // 공백이 나올 경우 다음 문자 확인
				idx++;
				
			} else if (line[idx] == '<') { // 여는 괄호가 나올 경우 닫는 괄호가 나올 때 까지 확인
				int bracketIdx = idx + 1;
				
				while (line[bracketIdx] != '>') {
					bracketIdx++;
				} // while문을 탈출했을 때 line[bracketIdx]는 닫는 괄호다
				
				idx = bracketIdx + 1; // 닫는 괄호 다음 문자부터 다시 확인
				
			} else { // 공백도 태그도 아니라면 여기서부터 단어다
				int wordIdx = idx + 1;
				
				while (true) { 
					// 인덱스가 문자열의 길이를 벗어나거나, 여는 괄호를 만나거나, 공백을 만나면 그 전 까지가 하나의 단어이다
					if (wordIdx == line.length) break;
					if (line[wordIdx] == '<') break;
					if (line[wordIdx] == ' ') break;
					
					wordIdx++;
				} // while문을 탈출했다면 wordIdx - 1까지가 하나의 단어이다
				
				wordIdx--; // idx~wordIdx까지가 하나의 단어
				
				int wordSize = wordIdx - idx + 1;
				
				for (int idxCnt = 0; idxCnt < wordSize / 2; idxCnt++) { // 단어의 길이 / 2 만큼 단어의 앞뒤 바꾸기
					char tmp = line[idx + idxCnt];
					line[idx + idxCnt] = line[wordIdx - idxCnt];
					line[wordIdx - idxCnt] = tmp;
				}
				
				idx = wordIdx + 1; // 단어 바꾸기 끝났으니 다음 문자 확인하기
			}
			
		}
		
		// 문자열 길이만큼 확인 끝났으면 바뀐 문자열 출력하기
		for (idx = 0; idx < line.length; idx++) {
			sb.append(line[idx]);
		}
		
		System.out.println(sb);
	}

}

