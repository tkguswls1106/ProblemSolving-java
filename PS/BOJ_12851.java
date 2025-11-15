import java.util.*;

// - 알고리즘 : BFS
// - 유사 문제 : 'BFS_BOJ_4179' 문제

public class BOJ_12851 {
    public static int n = 0, k = 0;
    public static int[] dist = new int[100002];
    public static int[] dx = {-1, 1, 2};  // +, +, x
    public static int cnt = 0;  // 최종 도착 카운트

    public static void bfs(int x) {
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(x);
        dist[x] = 1;

        while(!qu.isEmpty()) {
            int cur = qu.poll();

            for(int i=0; i<3; i++) {  // for(int nx : Arrays.asList(cur-1, cur+1, cur*2))
                int nx = (i != 2) ? cur + dx[i] : cur * dx[i];

                if(nx<0 || nx>=100001) continue;  // nx>100000
                if(dist[nx] > 0 && dist[cur]+1 > dist[nx]) continue;  // 이전 기록보다 늦게 도착한 경우

                qu.offer(nx);
                dist[nx] = dist[cur] + 1;
                if(nx == k) cnt++;  // 주의: 동생에게 최종 도착해도 중단하지 말것.
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();  // 수빈 (첫 출발지)
        k = sc.nextInt();  // 동생 (최종 도착지)
        if(n == k) {
            System.out.print("0\n1");
            return;
        }

        bfs(n);
        StringBuilder stb = new StringBuilder();
        stb.append(dist[k]-1).append("\n").append(cnt);

        System.out.print(stb.toString());
    }
}
