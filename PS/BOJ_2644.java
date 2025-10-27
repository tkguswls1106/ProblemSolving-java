import java.util.*;
import java.io.*;

// - 알고리즘 : BFS
// - 문제 Tip :
// 부모나 자식은 +1촌이지만, 형제는 +2촌임.
// 형제는 부모의 다른 자식이므로, 본인에서 형제로 이동하려면 반드시 부모를 거쳐야 함.
// 따라서 단순히 이동 거리 계산만으로도 위의 +2촌 계산이 가능함.
// 즉, 본인 -> +1: 부모 -> +1: 부모의 자식(형제)
// ==> BFS 풀이 사용할것.

public class BOJ_2644 {
    public static int n = 0;
    public static int targetX = 0, targetY = 0;
    public static ArrayList<Integer>[] adj = new ArrayList[102];  // board
    public static int[] dist = new int[102];

    public static void bfs() {
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(targetX);
        dist[targetX] = 1;

        while(!qu.isEmpty()) {
            int cur = qu.poll();

            for(int next : adj[cur]) {
                if(dist[next] > 0) continue;
                
                qu.offer(next);
                dist[next] = dist[cur] + 1;

                if(next == targetY) return;  // 조기 종료 (성능 최적화)
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for(int i=1; i<=n; i++) {
            adj[i] = new ArrayList<>();
        }

        StringTokenizer stt = new StringTokenizer(br.readLine());
        targetX = Integer.parseInt(stt.nextToken());
        targetY = Integer.parseInt(stt.nextToken());

        int m = Integer.parseInt(br.readLine());
        int parent, child;
        while(m-- > 0) {
            stt = new StringTokenizer(br.readLine());
            parent = Integer.parseInt(stt.nextToken());
            child = Integer.parseInt(stt.nextToken());  // 자식 번호
            adj[parent].add(child);
            adj[child].add(parent);
        }
        
        bfs();

        System.out.print(dist[targetY] - 1);  // 참고로 이는 '친척 관계가 아닌 -1'도 처리 가능.
    }
}
