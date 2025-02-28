import java.util.*;
import java.io.*;

// - 알고리즘: DFS

public class BOJ_9466 {
    public static int[] arr;
    public static int[] visited;  // 방문했는가?
    public static int[] finished;  // 사이클 체크를 완료했는가?
    public static int matchCount;

    public static void dfs(int cur) {
        if(visited[cur] == 1) return;

        visited[cur] = 1;  // 현재학생을 방문.

        int next = arr[cur];  // next: picked, cur: pick (cur이 뽑은 학생이 next임.)
        if(visited[next] == 0) {  // 아직 다음학생을 방문하지 않았으므로, 계속해서 탐색 이어나감.
            dfs(next);
        }
        else if(finished[next] == 0) {  // 방문했던 다음학생인데, 사이클 체크는 아직 안된 상황 (신규 사이클 발견)
            int cycleStart = next;  // 사이클이 시작된 학생을 기억.
            // 여기부분까진 아직 실제로 이동하진 않았음.

            matchCount++;
            next = arr[next];  // 다음으로 실제 이동.
            while(next != cycleStart) {
                matchCount++;
                next = arr[next];  // 다음으로 실제 이동.
            }
        }

        finished[cur] = 1;  // 현재학생의 사이클 체크 완료 표시.
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        while(tc-- > 0) {
            int n = Integer.parseInt(br.readLine());
            arr = new int[n+1];
            visited = new int[n+1];
            finished = new int[n+1];
            matchCount = 0;

            StringTokenizer stt = new StringTokenizer(br.readLine());
            for(int i=1; i<=n; i++) {
                int num = Integer.parseInt(stt.nextToken());
                arr[i] = num;
            }

            for(int i=1; i<=n; i++) {
                dfs(i);
            }

            stb.append(n - matchCount).append("\n");
        }

        System.out.print(stb.toString());
    }
}
