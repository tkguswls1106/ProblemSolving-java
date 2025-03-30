import java.util.*;
import java.io.*;

// - 알고리즘: 트리 + DFS
// - 문제 Tip: DFS를 두번 호출하여, 트리의 가장 긴 지름을 구할 수 있음.

public class BOJ_1167 {
    public static int v = 0;
    public static ArrayList<Pair>[] arr;
    public static boolean[] visited;
    public static int maxEnd = 0, maxDistSum = 0;

    public static class Pair {
        public int end;
        public int dist;

        public Pair(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }
    }

    public static void dfs(int start, int distSum) {
        if(maxDistSum < distSum) {
            maxEnd = start;
            maxDistSum = distSum;
        }

        for(Pair p : arr[start]) {
            int end = p.end;
            int dist = p.dist;

            if(visited[end] == true) continue;

            visited[end] = true;
            dfs(end, distSum + dist);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        v = Integer.parseInt(br.readLine());

        arr = new ArrayList[v+1];
        visited = new boolean[v+1];
        for(int i=1; i<=v; i++) {
            arr[i] = new ArrayList<>();
        }

        StringTokenizer stt;
        for(int i=0; i<v; i++) {
            stt = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stt.nextToken());

            int end, dist;
            while(true) {
                end = Integer.parseInt(stt.nextToken());
                if(end == -1) break;
                dist = Integer.parseInt(stt.nextToken());

                Pair p1 = new Pair(end, dist);
                Pair p2 = new Pair(start, dist);
                arr[start].add(p1);
                arr[end].add(p2);
            }
        }

        visited[1] = true;
        dfs(1, 0);
        visited = new boolean[v+1];  // 재초기화
        maxDistSum = 0;  // 재초기화

        visited[maxEnd] = true;
        dfs(maxEnd, 0);

        System.out.print(maxDistSum);
    }
}
