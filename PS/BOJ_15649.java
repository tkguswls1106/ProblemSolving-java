import java.util.*;
import java.io.*;

// - 알고리즘: 순열

public class BOJ_15649 {
    public static int n, m;
    public static int[] visited = new int[10];
    public static List<Integer> selected = new ArrayList<>();
    public static StringBuilder stb = new StringBuilder();

    public static void perm(int cnt) {
        if(cnt == m) {
            for(int num : selected) stb.append(num + " ");
            stb.append("\n");
            return;
        }

        for(int i=1; i<=n; i++) {
            if(visited[i] == 1) continue;

            selected.add(i);
            visited[i] = 1;
            perm(cnt+1);

            selected.remove(selected.size()-1);
            visited[i] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());

        perm(0);
        System.out.print(stb.toString());
    }
}
