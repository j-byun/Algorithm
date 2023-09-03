import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 2632 피자판매 골드2
 *
 * 문제
 * 두 종류의 피자 A와 B
 * 각 종류의 피자는 다양한 크기의 여러 피자조각으로 나누어져 있다.
 * 각 조각에 쓰여진 숫자는 피자조각의 크기를 나타낸다.
 * 고객이 원하는 피자의 크기만큼 판매하는데,
 * 한 종류의 피자를 2조각 이상 이상 판매할 때는 반드시 연속된 조각들을 잘라서 판매한다.
 * 이때 판매한 피자조각의 크기 합이 주문한 크기가 되어야 한다.
 * 판매한 피자조각은 모두 A종류이거나, 모두 B종류이거나, 또는 A와 B종류가 혼합될 수 있다.
 * 피자가게에서 손님이 원하는 크기의 피자를 판매하는 모든 방법의 가지 수를 계산하는 프로그램을 작성하시오.
 *
 * 조건
 * 손님이 구매하고자 하는 피자크기 2,000,000이하의 자연수
 * A, B 피자의 피자조각의 개수를 나타내는 정수 m, n (3 ≤ m, n ≤ 1000)
 * 각 종류의 피자 조각의 크기는 시계방향으로 주어진다.
 * 각 피자 조각의 크기는 1000이하의 자연수이다.
 * 피자를 판매하는 방법이 없는 경우에는 0을 출력한다.
 *
 * 풀이
 * 1. 피자 A와 B에 대해 손님이 원하는 크기만큼을 판매할 수 있는 경우의 수를 카운트하자. (배열)
 * 1-1. 이 때, 각 피자를 0만큼 판매하는 경우와 피자 전체를 판매하는 경우의 수는 1이다.
 * 2. countA[0] * countB[구매하려는 크기] + countA[1] * countB[구매하려는 크기 -1] + ... 를 계산하자.
 */

public class Main {
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int orderSize = sc.nextInt(); // 손님이 구매하고자 하는 피자크기
    	int m = sc.nextInt(); // 피자 A의 조각 개수
    	int n = sc.nextInt(); // 피자 B의 조각 개수
    	
    	int[] pizzaA = new int[m]; // 피자 A의 조각 크기를 저장할 배열
    	int[] pizzaB = new int[n]; // 피자 B의 조각 크기를 저장할 배열
    	
    	// 두 피자의 조각 크기 입력받기
    	int sumA = 0;
    	for (int idx = 0; idx < m; idx++) {
    		pizzaA[idx] = sc.nextInt();
    		sumA += pizzaA[idx];
    	}
    	
    	int sumB = 0;
    	for (int idx = 0; idx < n; idx++) {
    		pizzaB[idx] = sc.nextInt();
    		sumB += pizzaB[idx];
    	}
    	
    	// 각 피자에 대해 손님이 원하는 크기만큼 만들 수 있는 경우의 수 구하기
    	// 1. 경우의 수를 저장할 배열 만들기
    	int[] countA = new int[orderSize + 1];
    	int[] countB = new int[orderSize + 1];
    	
    	// 2. 배열의 0번과 각 피자의 최대 크기 인덱스 값을 1로 초기화
    	countA[0] = 1;
    	countB[0] = 1;
    	if (sumA <= orderSize)
    		countA[sumA] = 1;
    	if (sumB <= orderSize)
    		countB[sumB] = 1;
    	
    	// 3. 경우의 수 구하기
    	calcCases(pizzaA, m, countA, orderSize);
    	calcCases(pizzaB, n, countB, orderSize);
    	
    	// 피자A의 경우의수 * 피자B의 경우의 수
    	int count = 0;
    	for (int idx = 0; idx <= orderSize; idx++) {
    		count += (countA[idx] * countB[orderSize - idx]);
    	}
    	
    	// 결과 출력
    	System.out.println(count);
    }
    
    private static void calcCases(int[] pizza, int pizzaSize, int[] count, int countSize) {
    	
    	for (int idx = 0; idx < pizzaSize; idx++) { // 시작 인덱스
    		int sum = 0; // 피자 크기의 합
    		
    		for (int piece = 1; piece < pizzaSize; piece++) { // 계산에 포함할 피자 조각의 개수
    			sum += pizza[(idx + piece - 1) % pizzaSize];
    			
    			if (sum > countSize)
    				break; // 손님이 주문하려는 피자보다 크면 탐색 중지
    			
    			// 손님이 주문하려는 피자보다 작으면 경우의 수 +1
    			count[sum]++;
    		}
    	}
    }
    
}
