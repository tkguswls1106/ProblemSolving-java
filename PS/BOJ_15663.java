import java.util.*;
import java.util.stream.*;
import java.io.*;

// - 알고리즘: 순열

public class BOJ_15663 {
    public static int n, m;
    public static int[] arr, visited;
    public static List<Integer> selected = new ArrayList<>();
    public static List<String> answerList = new ArrayList<>();

    public static void perm(int cnt) {
        if(cnt == m) {
            StringBuilder stb = new StringBuilder();
            for(int num : selected) {
                stb.append(num).append(" ");
            }
            answerList.add(stb.toString());
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
        answerList = answerList.stream()
                            .distinct()
                            .collect(Collectors.toList());
        StringBuilder stb = new StringBuilder();
        for(String answer : answerList) stb.append(answer).append("\n");

        System.out.print(stb.toString());
    }
}
