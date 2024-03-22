import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 30619 내 집 마련하기 실버2
 * 시간 제한 1초 | 메모리 제한 1024 MB
 *
 * 문제
 * N명의 사람에게 N채의 집을 배정해주자.
 * x번 집에 y번 사람이 입주해서 살게 되면 xy만큼의 세금을 감면해준다.
 * 수열 A : i번 집에 Ai번 사람이 배정됨을 의미한다.
 * L R : 초기 집 배정 상태에서 L번부터 R번까지 사람들의 감면되는 세금 합이 최대가 되도록 재배정했을 때의 수열 A를 출력한다.
 * 쿼리가 실행된 이후 수열 A는 초기 상태로 복구된다.
 *
 * 조건
 * 수열의 길이를 나타내는 정수 N (1 <= N <= 300)
 * 정수로 이루어진 수열 A (1 <= Ai <= N)
 * 쿼리의 개수를 나타내는 정수 M (1 <= M <= 300)
 * 쿼리를 나타내는 두 정수 L, R (1 <= L <= R <= N)
 *
 * 풀이
 * 1. L R 연산을 하려면 사람 번호 순으로 정렬되어야 하고, 쿼리를 출력하기 위해서는 집 번호 순으로 정렬되어야 함.
 * 2. 집 번호와 사람번호를 갖는 클래스의 배열을 만들어서 조건에 따라 정렬해서 사용하자.
 */

public class Main {
	
	static class Home {
		int index, person;
		
		public Home(int index, int person) {
			this.index = index;
			this.person = person;
		}
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	
    	int N = Integer.parseInt(st.nextToken()); // 수열의 길이 (집과 사람의 개수)
    	Home[] homeList = new Home[N]; // N개의 집에 배정된 사람 정보를 저장할 배열
    	
    	// 처음 배정된 사람 정보 저장하기
    	st = new StringTokenizer(br.readLine());
    	for (int idx = 0; idx < N; idx++) {
    		homeList[idx] = new Home(idx + 1, Integer.parseInt(st.nextToken()));
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	int M = Integer.parseInt(st.nextToken()); // 쿼리의 개수
    	
    	// 쿼리마다 집 배열을 복제해서 사용해야하니 배열 하나 선언해놓기
    	Home[] copyHomeList = new Home[N];
    	
    	// 쿼리 실행
    	for (int cnt = 0; cnt < M; cnt++) {
    		st = new StringTokenizer(br.readLine());
    		int L = Integer.parseInt(st.nextToken());
    		int R = Integer.parseInt(st.nextToken());
    		
    		// 초기 집 배열 복제
    		for (int idx = 0; idx < N; idx++) {
    			copyHomeList[idx] = new Home(homeList[idx].index, homeList[idx].person);
    		}
    		
    		// 사람 번호 순으로 정렬
    		Arrays.sort(copyHomeList, (o1, o2) -> {
    			return o1.person - o2.person;
    		});
    		
    		List<Integer> homeNumber = new ArrayList<>();
    		
    		for (int idx = L - 1; idx < R; idx++) {
    			homeNumber.add(copyHomeList[idx].index);
    		}
    		
    		Collections.sort(homeNumber);
    		
    		for (int idx = L - 1; idx < R; idx++) {
    			copyHomeList[idx].index = homeNumber.get(0);
    			homeNumber.remove(0);
    		}
    		
    		Arrays.sort(copyHomeList, (o1, o2) -> {
    			return o1.index - o2.index;
    		});
    		
    		for (Home h : copyHomeList) {
    			sb.append(h.person).append(" ");
    		}
    		sb.append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
}
