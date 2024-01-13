import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 200006 랭킹전 대기열 실버2
 *
 * 문제
 * 방 만든 플레이어 기준 +-10렙까지 입장 가능
 * 입장 가능한 방이 여러개라면 먼저 생성된 방에 입장
 * 방의 정원이 모두 차면 게임 시작
 *
 * 조건
 * 플레이어의 수 p(1 ≤ p ≤ 300)
 * 방의 정원 m(1 ≤ m ≤ 300)
 * 플레이어의 레벨 l (1 ≤ l ≤ 500)
 * 플레이어의 닉네임 n : 중복X, 공백X, 알파벳 소문자, 길이 16 이하
 * 입력된 순서대로 게임 시작
 *
 * 풀이
 * 1. ArrayList만들어서 조건 맞춰 플레이어들을 담고, 플레이어의 닉네임 사전순으로 출력
 */

public class Main {
	
	static class Player{
		int level;
		String name;
		public Player(int level, String name) {
			this.level = level;
			this.name = name;
		}
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	
    	int p = Integer.parseInt(st.nextToken()); // 플레이어의 수 입력
    	int m = Integer.parseInt(st.nextToken()); // 방의 정원 입력
    	
    	ArrayList<ArrayList<Player>> room = new ArrayList<>(); // 게임 룸 만들기
    	
    	// 플레이어 차례대로 입장
    	while (p-- > 0) {
    		st = new StringTokenizer(br.readLine());
    		
    		// 새로 입장한 플레이어 정보
    		Player newbie = new Player(Integer.parseInt(st.nextToken()), st.nextToken());
    		
    		// 새로운 플레이어가 기존에 있던 방에 들어갔는지 확인하기 위한 flag
    		boolean flag = false;
    		
    		// 이미 만들어진 방에 들어갈 수 있는지 확인
    		for (int idx = 0; idx < room.size(); idx++) {
    			// m명이 이미 입장해있으면 입장 불가
    			if (room.get(idx).size() == m) continue;
    			
    			// 방장렙 +-10범위 넘으면 입장 불가
    			if (room.get(idx).get(0).level - 10 > newbie.level 
    					|| room.get(idx).get(0).level + 10 < newbie.level) continue;
    			
    			// 두 조건에 다 안걸리면 해당 방에 입장
    			room.get(idx).add(newbie);
    			flag = true;
    			break;
    		}
    		
    		// 기존 방에 입장했으면 다음 플레이어 입장
    		if (flag) continue;
    		
    		// 입장할 수 있는 방이 없으면 새로운 방 생성
    		room.add(new ArrayList<>());
    		room.get(room.size() - 1).add(newbie);
    	}
    	
    	// 방에 있는 플레이어들 정보 출력
    	// 한 방에 m명이 있으면 시작한 방
    	// 플레이어 이름 사전순
    	for (ArrayList<Player> list : room) {
    		Collections.sort(list, (o1, o2) -> {
    			return o1.name.compareTo(o2.name);
    		});
    		
    		if (list.size() == m)
    			sb.append("Started!").append("\n");
    		else
    			sb.append("Waiting!").append("\n");
    		
    		for (Player player : list) {
    			sb.append(player.level).append(" ").append(player.name).append("\n");
    		}
    	}
    	
    	System.out.println(sb.toString());
    }
}
