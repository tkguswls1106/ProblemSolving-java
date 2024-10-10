import java.util.*;
import java.io.*;

// - 알고리즘: BFS + 이분 그래프
// - 이분 그래프란?: 간선의 정점을 서로 이었을때, 반대방향에 모인 정점끼리 집합으로 묶이며, 동시에 같은 집합 내에서는 서로 이어진 간선이 없는 그래프이다.

public class BOJ_1707 {
    public static int k, v, e;
    public static ArrayList<Integer>[] arr;  // arr[start][end,end,..]
    public static int[] visited;  // 0:방문X(미방문), 1:왼쪽정점집합, 2:오른쪽정점집합
    public static boolean isOtherGraph = true;

    public static void bfs(int x) {
        if(visited[x] != 0) return;  // 미방문 정점 아니라면, bfs 호출거부.

        Queue<Integer> qu = new LinkedList<>();
        qu.offer(x);
        visited[x] = 1;  // 왼쪽정점집합으로 지정.

        while(!qu.isEmpty()) {
            int cur = qu.poll();

            for(int next : arr[cur]) {
                if(visited[next] == 0) {  // 미방문인 경우, 반대집합으로 지정.
                    qu.offer(next);
                    int nextVisited = (visited[cur] == 1) ? 2 : 1;
                    visited[next] = nextVisited;
                }
                else {  // 방문인 경우
                    if(visited[cur] == visited[next]) {  // 같은 집합에 도달한경우
                        isOtherGraph = false;
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());

        while(k-- > 0) {
            StringTokenizer stt = new StringTokenizer(br.readLine());
            v = Integer.parseInt(stt.nextToken());
            e = Integer.parseInt(stt.nextToken());

            arr = new ArrayList[v+1];
            for(int i=1; i<=v; i++) {
                arr[i] = new ArrayList<>();
            }
            visited = new int[v+1];

            for(int i=0; i<e; i++) {
                stt = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(stt.nextToken());
                int end = Integer.parseInt(stt.nextToken());
                arr[start].add(end);
                arr[end].add(start);
            }

            for(int i=1; i<=v; i++) {
                bfs(i);
            }

            System.out.println(isOtherGraph ? "YES" : "NO");
            isOtherGraph = true;
        }
    }
}
