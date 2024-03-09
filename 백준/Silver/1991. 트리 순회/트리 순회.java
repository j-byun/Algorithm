import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1991 트리순회 실버1
 * 시간 제한 2초 | 메모리 제한 128 MB
 *
 * 문제
 * 이진 트리를 입력받아 전위 순회, 중위 순회, 후위 순회한 결과를 출력하자
 *
 * 조건
 * 이진 트리의 노드의 개수 N(1 ≤ N ≤ 26)
 * 항상 A가 루트 노드가 된다.
 * 자식 노드가 없는 경우에는 .으로 표현한다.
 *
 * 풀이
 */

public class Main {
	
	static class Node {
		char index, left, right;
		
		public Node(char index, char left, char right) {
			this.index = index;
			this.left = left;
			this.right = right;
		}
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken()); // 노드의 개수 N 입력받기
    	
    	Node[] tree = new Node[N]; // N개의 노드 정보를 저장할 배열 생성
    	
    	// N개의 노드 정보 입력받기
    	for (int idx = 0; idx < N; idx++) {
    		st = new StringTokenizer(br.readLine());
    		char index = st.nextToken().charAt(0);
    		char left = st.nextToken().charAt(0);
    		char right = st.nextToken().charAt(0);
    		
    		tree[index - 'A'] = new Node(index, left, right);
    	}
    	
    	System.out.println(preorder(tree, 0));
    	System.out.println(inorder(tree, 0));
    	System.out.println(postorder(tree, 0));
    }
    
    static String preorder(Node[] tree, int index) {
    	return tree[index].index
    			+ ((tree[index].left == '.') ? "" : preorder(tree, tree[index].left - 'A'))
    			+ ((tree[index].right == '.') ? "" : preorder(tree, tree[index].right - 'A'));
    }
    
    static String inorder(Node[] tree, int index) {
    	return ((tree[index].left == '.') ? "" : inorder(tree, tree[index].left - 'A'))
    			+ tree[index].index
    			+ ((tree[index].right == '.') ? "" : inorder(tree, tree[index].right - 'A'));
    }
    
    static String postorder(Node[] tree, int index) {
    	return ((tree[index].left == '.') ? "" : postorder(tree, tree[index].left - 'A'))
    			+ ((tree[index].right == '.') ? "" : postorder(tree, tree[index].right - 'A'))
    			+ tree[index].index;
    }
}
