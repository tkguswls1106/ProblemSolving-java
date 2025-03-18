import java.util.*;
import java.io.*;

// - 알고리즘: 조합

public class BOJ_15655 {
    public static int n, m;
    public static int[] arr;
    public static List<Integer> selected = new ArrayList<>();
    public static StringBuilder stb = new StringBuilder();

    public static void comb(int start, int cnt) {
        if(cnt == m) {
            for(int num : selected) {
                stb.append(num).append(" ");
            }
            stb.append("\n");
            return;
        }

        for(int i=start; i<n; i++) {
            selected.add(arr[i]);
            comb(i+1, cnt+1);

            selected.remove(selected.size()-1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());
        arr = new int[n];

        stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(stt.nextToken());
        }
        Arrays.sort(arr);  // 문제에 '수열은 사전 순으로 증가하는 순서로 출력'라고 명시되어있기 때문.

        comb(0, 0);

        System.out.print(stb.toString());
    }
}
