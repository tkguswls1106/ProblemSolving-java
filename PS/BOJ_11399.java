import java.util.*;
import java.io.*;

// - 알고리즘: 그리디

public class BOJ_11399 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(stt.nextToken());
        }
        Arrays.sort(arr);

        int waitTime = 0;
        int sumTime = 0;
        for(int time : arr) {
            waitTime += time;
            sumTime += waitTime;
        }

        System.out.print(sumTime);
    }
}
