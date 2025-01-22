import java.util.*;
import java.io.*;

// - 알고리즘: 투 포인터

public class BOJ_2003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stt.nextToken());
        int m = Integer.parseInt(stt.nextToken());
        int[] arr = new int[n+1];

        stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(stt.nextToken());
        }
        arr[n] = 0;  // 끝 인덱스 확인을 위함. 반드시 맨뒤에 넣을것!!!

        int start = 0, end = 0;
        int sum = 0, cnt = 0;
        while(start<=end && end<arr.length) {
            if(sum < m) {
                sum += arr[end];
                end++;
            }
            else if(sum > m) {
                sum -= arr[start];
                start++;
            }
            else {  // (sum == m)
                cnt++;
                sum -= arr[start];
                start++;
            }
        }

        System.out.print(cnt);
    }
}
