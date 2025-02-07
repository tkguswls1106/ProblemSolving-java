import java.util.*;
import java.io.*;

// - 알고리즘: 투 포인터

public class BOJ_2559 {
    public static int[] arr = new int[100002];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stt.nextToken());
        int k = Integer.parseInt(stt.nextToken());

        stt = new StringTokenizer(br.readLine());
        int k2 = k;
        int sum = 0;
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(stt.nextToken());
            if(k2-- > 0) {
                sum += arr[i];
            }
        }
        
        int start = 0, end = k-1;
        int maxSum = Integer.MIN_VALUE;  // 만약 -1로 선언하면, 음수의 합보다 큰값으로 작용할수있기에 쓰면 안됨.
        while(start<=end && end<n) {
            maxSum = Math.max(maxSum, sum);

            // o o o o
            // x o o o o
            sum -= arr[start++];
            sum += arr[++end];
        }

        System.out.print(maxSum);
    }
}
