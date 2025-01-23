import java.util.*;
import java.io.*;

// - 알고리즘: 투 포인터

public class BOJ_3273 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(stt.nextToken());
        }
        Arrays.sort(arr);
        int x = Integer.parseInt(br.readLine());

        int start = 0, end = n-1;  // end를 맨오른쪽 끝에서부터 시작.
        int cnt = 0;
        while(start<end && end<arr.length) {
            int sum = arr[start] + arr[end];
            if(sum == x) {
                cnt++;
                start++;
            }
            else if(sum > x) {
                end--;  // 각 포인터가 양끝에서 시작하므로, 방향이 안쪽으로가야함.
            }
            else {  // (sum < x)
                start++;  // 각 포인터가 양끝에서 시작하므로, 방향이 안쪽으로가야함.
            }
        }

        System.out.print(cnt);
    }
}
