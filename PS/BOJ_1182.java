import java.util.*;
import java.io.*;

// - 알고리즘: 부분집합 

public class BOJ_1182 {
    public static int n, s;
    public static int arr[];
    public static int answer = 0;

    public static void sub(int cnt, int sum) {
        if(cnt == n) {
            if(sum == s) answer++;
            return;
        }

        sub(cnt+1, sum);
        sub(cnt+1, sum+arr[cnt]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stt.nextToken());
        s = Integer.parseInt(stt.nextToken());
        arr = new int[n];

        stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            int num = Integer.parseInt(stt.nextToken());
            arr[i] = num;
        }

        sub(0, 0);
        if(s == 0) answer--;

        System.out.print(answer);
    }
}
