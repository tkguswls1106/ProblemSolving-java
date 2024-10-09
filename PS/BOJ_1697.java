import java.util.*;

public class BOJ_1697 {
    public static int n, k;
    public static int dist[] = new int[100002];

    public static void bfs(int x) {
        // if(dist[x] > 0) return;

        Queue<Integer> qu = new LinkedList<>();
        qu.offer(x);
        // 어차피 dist[x]=0 이므로, 생략.

        while(!qu.isEmpty()) {
            int cur = qu.poll();

            for(int nx : Arrays.asList(cur-1, cur+1, cur*2)) {
                if(nx<0 || nx>100000) continue;
                if(dist[nx] > 0) continue;

                qu.offer(nx);
                dist[nx] = dist[cur] + 1;

                if(nx == k) {
                    System.out.print(dist[nx]);
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        if(n == k) System.out.print(0);
        else bfs(n);
    }
}
