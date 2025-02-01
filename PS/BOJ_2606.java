// < 성공 방법 1 >
// - 알고리즘: BFS

import java.util.*;
import java.io.*;

public class BOJ_2606 {
    public static int n;
    public static ArrayList<Integer>[] arr;
    public static int[] visited;
    public static int area = 0;

    public static void bfs(int x) {
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(x);
        visited[x] = 1;

        while(!qu.isEmpty()) {
            int cur = qu.poll();

            for(int next : arr[cur]) {
                if(visited[next] == 1) continue;

                qu.offer(next);
                visited[next] = 1;
                area++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int tc = Integer.parseInt(br.readLine());

        arr = new ArrayList[n+1];
        visited = new int[n+1];
        for(int i=1; i<=n; i++) {  // 어차피 0인덱스는 사용하지않으므로, 굳이 초기화 X.
            arr[i] = new ArrayList<>();
        }

        StringTokenizer stt;
        while(tc-- > 0) {
            stt = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stt.nextToken());
            int end = Integer.parseInt(stt.nextToken());
            arr[start].add(end);
            arr[end].add(start);
        }

        bfs(1);
        
        System.out.print(area);
    }
}

/*
// < 성공 방법 2 >
// - 알고리즘: DFS

import java.util.*;
import java.io.*;

public class BOJ_2606 {
    public static int n;
    public static ArrayList<Integer>[] arr;
    public static int[] visited;
    public static int area = -1;  // 시작지점을 더해도 0부터 시작하도록.

    public static void dfs(int cur) {
        if(visited[cur] == 1) return;

        visited[cur] = 1;
        area++;

        for(int next : arr[cur]) {
            dfs(next);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int tc = Integer.parseInt(br.readLine());

        arr = new ArrayList[n+1];
        visited = new int[n+1];
        for(int i=1; i<=n; i++) {  // 어차피 0인덱스는 사용하지않으므로, 굳이 초기화 X.
            arr[i] = new ArrayList<>();
        }

        StringTokenizer stt;
        while(tc-- > 0) {
            stt = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stt.nextToken());
            int end = Integer.parseInt(stt.nextToken());
            arr[start].add(end);
            arr[end].add(start);
        }

        dfs(1);
        
        System.out.print(area);
    }
}
*/