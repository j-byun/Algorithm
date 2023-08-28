import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 1822 차집합 실버4
 *
 * 문제
 * 몇 개의 자연수로 이루어진 두 집합 A와 B가 있다.
 * A에는 속하면서 집합 B에는 속하지 않는 모든 원소를 구하는 프로그램을 작성하시오.
 *
 * 조건
 * 집합 A의 원소의 개수 n(A), 집합 B의 원소의 개수 n(B)
 * 1 ≤ n(A), n(B) ≤ 500,000
 * 하나의 집합의 원소는 2,147,483,647 이하의 자연수이며, 하나의 집합에 속하는 모든 원소의 값은 다르다.
 *
 * 풀이
 * 1. 투포인터
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int sizeA = sc.nextInt(); // A 배열 원소 개수
    	int sizeB = sc.nextInt(); // B 배열 원소 개수
    	
    	int[] arrA = new int[sizeA]; // A 배열
    	int[] arrB = new int[sizeB]; // B 배열	
    	
    	// A의 원소들 입력받기
    	for (int idx = 0; idx < sizeA; idx++) {
    		arrA[idx] = sc.nextInt();
    	}
    	
    	// B의 원소들 입력받기
    	for (int idx = 0; idx < sizeB; idx++) {
    		arrB[idx] = sc.nextInt();
    	}
    	
    	// 배열 오름차순으로 정렬
    	Arrays.sort(arrA);
    	Arrays.sort(arrB);
    	
    	List<Integer> list = new ArrayList<>(); // 차집합 원소들을 저장할 리스트
    	
    	// 두 개의 포인터를 사용하자
    	int pointerA = 0;
    	int pointerB = 0;
    	
    	while (pointerA < sizeA) {
    		if (pointerB >= sizeB || arrA[pointerA] < arrB[pointerB]) {
    			list.add(arrA[pointerA]);
    			pointerA++;
    		} else if (arrA[pointerA] > arrB[pointerB]) {
    			pointerB++;
    		} else if (arrA[pointerA] == arrB[pointerB]) {
    			pointerA++;
    		}
    	}
    	
    	// 결과 출력
    	System.out.println(list.size());
    	if (list.size() > 0) {
    		for (int idx = 0; idx < list.size(); idx++) {
    			System.out.print(list.get(idx) + " ");
    		}
    	}
    }
}
