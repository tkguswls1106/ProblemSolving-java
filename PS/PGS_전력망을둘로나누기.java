// < 성공 방법 1 >
// 'ArrayList<Integer>[]' 사용 방법
// => 장점 1 : 메모리 사용이 효율적임.
// => 장점 2 : 인접 노드의 탐색이 빠름.
// => 단점 : 'adj[num1].remove(Integer.valueOf(num2))'에서 시간복잡도 O(간선개수)를 가지므로, 간선의 삭제가 느림.
// ==> '노드 수 (n개)'가 작고 '간선 수가 최대 (n-1개)'인 트리 구조에서는, 'ArrayList<Integer>[]' 방식이 약간 더 효율적임.

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

/*
// < 성공 방법 2 >
// 'boolean[][]' 사용 방법
// => 장점 : 'arr[num1][num2] = false'에서 시간복잡도 O(1)을 가지므로, 간선의 삭제가 빠름.
// => 단점 1 : 2차원 배열이므로 메모리 사용이 비효율적임.
// => 단점 2 : 'for(int nx=1; nx<=lastN; nx++)'에서 전체 노드에 매번 for문을 돌아야하므로, 인접 노드의 탐색이 느림.
// ==> '노드 수 (n개)'가 작고 '간선 수가 최대 (n-1개)'인 트리 구조에서는, 'boolean[][]' 방식이 약간 더 비효율적임.

import java.util.*;

class Solution {
    public static boolean[][] arr = new boolean[102][102];
    public static boolean[] visited = new boolean[102];
    public static int lastN = 0;
    
    public static int bfs(int x) {
        if(visited[x] == true) return 0;
        
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(x);
        visited[x] = true;
        int nodeCnt = 1;
        
        while(!qu.isEmpty()) {
            int cur = qu.poll();
            
            for(int nx=1; nx<=lastN; nx++) {
                if(nx == cur) continue;

                if(arr[cur][nx] == false || visited[nx] == true) continue;

                qu.offer(nx);
                visited[nx] = true;
                nodeCnt++;
            }
        }
        
        return nodeCnt;
    }
    
    public int solution(int n, int[][] wires) {
        lastN = n;
        
        for(int[] wire : wires) {
            int num1 = wire[0];
            int num2 = wire[1];
            arr[num1][num2] = true;
            arr[num2][num1] = true;
        }
        
        int result = Integer.MAX_VALUE;
        for(int[] wire : wires) {
            int num1 = wire[0];
            int num2 = wire[1];
            arr[num1][num2] = false;
            arr[num2][num1] = false;
            
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
            
            arr[num1][num2] = true;
            arr[num2][num1] = true;
            Arrays.fill(visited, false);
            cntList.clear();
        }
        
        return result;
    }
}
*/

public class PGS_전력망을둘로나누기 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
