import java.util.*;

/*
queue1의 합과 queue2의 합을 구한다.
둘의 합 / 2 = 맞춰야하는 한 큐의 합
두 포인터로 각 큐의 첫 인덱스를 가르키고, 두 큐 중 합이 작은 큐에 원소를 옮겨준다.
두 포인터가 모두 각 큐의 크기를 넘어가면 더 이상 방법이 없으므로 탐색을 중단하고 -1을 출력한다.
=> 2번 예시 통과X

두 배열을 큐에 옮기고 두 포인터로 구현하려던 로직을 수행하자.
탈출 조건 (최악의 경우 이동 횟수)는 (두 큐의 길이의 합) * 2 이다.
이유는 최악의 상황에서(두 큐가 완전히 바뀐 상황) 다시 원래 상태로 돌리는 경우를 생각했을 때의 이동횟수이다.
*/

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int size = queue1.length; // 배열의 크기
        long sum1 = 0; // queue1의 합
        long sum2 = 0; // queue2의 합
        
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        
        for (int idx = 0; idx < size; idx++) {
            sum1 += queue1[idx];
            sum2 += queue2[idx];
            q1.add(queue1[idx]);
            q2.add(queue2[idx]);
        }
        
        long mid = (sum1 + sum2) / 2; // 만들어야 하는 평균값
        
        // 두 포인터
        int count = 0; // 움직인 횟수를 세어줄 카운트 변수
        
        while (count <= size * 4) {
            if (sum1 == mid) {
                return count;
            }
            if (sum1 > sum2) {
                sum1 -= q1.peek();
                sum2 += q1.peek();
                q2.add(q1.poll());
                count++;
            }
            if (sum1 < sum2) {
                sum2 -= q2.peek();
                sum1 += q2.peek();
                q1.add(q2.poll());
                count++;
            }
        }
        
        // 두 포인터 안에서 해결되지 않으면 불가능하므로 -1출력
        return -1;
    }
}