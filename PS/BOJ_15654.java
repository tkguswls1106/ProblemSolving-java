import java.util.*;
import java.io.*;

// - 알고리즘: 순열
// - 문제 Tip: 문제에 '주어지는 N개의 자연수는 모두 다른 수이다'라고 명시되어있으므로, 입력 요소들의 중복제거는 하지않아도됨.

public class BOJ_15654 {
    public static int n, m;
    public static int[] arr, visited;
    public static List<Integer> selected = new ArrayList<>();
    public static StringBuilder stb = new StringBuilder();

    public static void perm(int cnt) {
        if(cnt == m) {
            for(int num : selected) {
                stb.append(num).append(" ");
            }
            stb.append("\n");
            return;
        }

        for(int i=0; i<n; i++) {
            if(visited[i] == 1) continue;

            selected.add(arr[i]);
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
        arr = new int[n];
        visited = new int[n];

        stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(stt.nextToken());
        }
        Arrays.sort(arr);  // 문제에 '수열은 사전 순으로 증가하는 순서로 출력'라고 명시되어있기 때문.

        perm(0);

        System.out.print(stb.toString());
    }
}
