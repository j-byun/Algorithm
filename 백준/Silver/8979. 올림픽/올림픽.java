import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 8979 올림픽 실버5
 *
 * 문제
 * 올림픽 국가간 순위를 정하자.
 * 순위 결정 방식은 다음과 같다.
 * 1. 금메달 수가 더 많은 나라
 * 2. 금메달 수가 같으면, 은메달 수가 더 많은 나라
 * 3. 금, 은메달 수가 모두 같으면, 동메달 수가 더 많은 나라
 * 두 나라가 금, 은, 동메달 수가 모두 같다면 두 나라의 등수는 같다.
 *
 * 조건
 * 국가의 수 N(1 ≤ N ≤ 1,000)
 * 등수를 알고 싶은 국가 K(1 ≤ K ≤ N)
 * 전체 메달 수의 총합은 1,000,000 이하
 *
 * 풀이
 */

public class Main {
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int N = sc.nextInt(); // 국가의 수
    	int K = sc.nextInt(); // 등수를 알고싶은 국가
    	
    	int[][] medals = new int[N][4];
    	
    	for (int row = 0; row < N; row++) {
    		for (int col = 0; col < 4; col++) {
    			medals[row][col] = sc.nextInt();
    		}
    	}
    	
    	Arrays.sort(medals, (o1, o2) -> {
    		if (o2[1] == o1[1]) {
    			if (o2[2] == o1[2]) {
    				if (o2[3] == o1[3] && o1[0] == K)
    					return -1;
    				return o2[3] - o1[3];
    			}
    			return o2[2] - o1[2];
    		}
    		return o2[1] - o1[1];
    	});
    	
    	for (int row = 0; row < N; row++) {
//    		System.out.println(medals[row][0] + " " + medals[row][1] + " " + medals[row][2] + " " + medals[row][3]);
    		
    		if (medals[row][0] == K) {
    			System.out.println(row + 1);
    			return;
    		}
    	}
    }
    
}
