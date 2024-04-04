import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 12018 Yonsei TOTO 실버3
 * 시간 제한 1초 | 메모리 제한 128 MB
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int n = Integer.parseInt(st.nextToken()); // 과목 수
    	int m = Integer.parseInt(st.nextToken()); // 마일리지
    	
    	int[] bet = new int[n]; // 각 과목을 수강하기 위해 베팅해야 하는 마일리지를 저장할 배열
    	List<Integer> list; // 각 과목에 신청한 학생들이 건 마일리지 점수를 내림차순 정렬할 리스트
    	
    	for (int idx = 0; idx < n; idx++) {
    		st = new StringTokenizer(br.readLine());
    		int P = Integer.parseInt(st.nextToken()); // 신청한 사람 수
    		int L = Integer.parseInt(st.nextToken()); // 수강인원
    		
    		// 수강신청한 학생들 정보 입력받기
    		list = new ArrayList<>();
    		
    		st = new StringTokenizer(br.readLine());
    		
    		// 수강인원만큼 신청 안했으면 1점만 투자하고 탈출
    		if (P < L) {
    			bet[idx] = 1;
    			continue;
    		}
    		
    		while (P-- > 0) {
    			list.add(Integer.parseInt(st.nextToken()));
    		}
    		
    		Collections.sort(list, Collections.reverseOrder()); // 마일리지 내림차순 정렬해서
    		bet[idx] = list.get(L - 1); // 이 과목 수강하기 위해 필요한 최소 마일리지 저장
    	}
    	
    	Arrays.sort(bet); // 많은 과목을 들어야 하니 마일리지 적은 순으로 오름차순 정렬
    	
    	// 마일리지 낮은 과목 순서대로 수강신청하자
    	int count = 0;
    	
    	for (int mileage : bet) {
    		if (m < mileage) break;
    		
    		count++;
    		m -= mileage;
    	}
    	
    	System.out.println(count);
    }
}
