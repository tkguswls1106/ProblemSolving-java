import java.util.*;

// - 알고리즘: BFS + 브루트포스
// - 문제 Tip:
// 1. 모든 간선을 한번씩 끊어보기.
// 2. 전력망이 두 그룹으로 나뉘는지 확인.
// 3. 브루트포스로 BFS 실시.

class Solution {
    public static ArrayList<Integer>[] adj = new ArrayList[102];
    public static boolean[] visited = new boolean[102];
    
    public static int bfs(int x) {
        if(visited[x] == true) return 0;
        
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(x);
        visited[x] = true;
        int nodeCnt = 1;
        
        while(!qu.isEmpty()) {
            int cur = qu.poll();
            
            for(int nx : adj[cur]) {
                if(visited[nx] == true) continue;
                
                qu.offer(nx);
                visited[nx] = true;
                nodeCnt++;
            }
        }
        
        return nodeCnt;
    }
    
    public int solution(int n, int[][] wires) {
        for(int i=1; i<=n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for(int[] wire : wires) {
            int num1 = wire[0];
            int num2 = wire[1];
            adj[num1].add(num2);
            adj[num2].add(num1);
        }
        
        int result = Integer.MAX_VALUE;
        for(int[] wire : wires) {
            int num1 = wire[0];
            int num2 = wire[1];
            adj[num1].remove(Integer.valueOf(num2));
            adj[num2].remove(Integer.valueOf(num1));
            
            List<Integer> cntList = new ArrayList<>();
            for(int i=1; i<=n; i++) {
                int cnt = bfs(i);
                if(cnt != 0) {
                    cntList.add(cnt);
                    if(cntList.size() > 2) break;
                }
            }
            
            if(cntList.size() == 2) {
                int diff = Math.abs(cntList.get(0) - cntList.get(1));
                result = Math.min(result, diff);
            }
            
            adj[num1].add(num2);
            adj[num2].add(num1);
            Arrays.fill(visited, false);
            cntList.clear();
        }
        
        return result;
    }
}

public class PGS_전력망을둘로나누기 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
