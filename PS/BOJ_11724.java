import java.util.*;
import java.io.*;

// - 알고리즘: BFS
// - 문제 Tip: 참고로 문제에 명시된 '연결 요소의 개수'란 '몇 개의 그룹으로 나뉘어졌는지'를 의미한다.

public class BOJ_11724 {
    public static int n;
    public static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    public static ArrayList<Integer>[] arr;
    public static int[] visited;
    public static int answer = 0;

    public static void bfs(int x) {
        if(visited[x] == 1) return;

        Queue<Integer> qu = new LinkedList<>();
        qu.offer(x);
        visited[x] = 1;

        while(!qu.isEmpty()) {
            int cur = qu.poll();

            for(int next : arr[cur]) {
                if(visited[next] == 1) continue;
                
                qu.offer(next);
                visited[next] = 1;
            }
        }

        answer++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        int m = Integer.parseInt(stt.nextToken());
        arr = new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
            arr[i] = new ArrayList<>();
        }
        visited = new int[n+1];

        while(m-- > 0) {
            stt = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stt.nextToken());
            int end = Integer.parseInt(stt.nextToken());
            arr[start].add(end);
            arr[end].add(start);
        }
        
        for(int i=1; i<=n; i++) {
            bfs(i);
        }

        System.out.print(answer);
    }
}
