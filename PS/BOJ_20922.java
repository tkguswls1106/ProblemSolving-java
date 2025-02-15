import java.util.*;
import java.io.*;

// - 알고리즘: 투 포인터

public class BOJ_20922 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stt.nextToken());
        int k = Integer.parseInt(stt.nextToken());
        int[] arr = new int[n+1];

        stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(stt.nextToken());
        }
        arr[n] = 0;

        int start = 0, nextEnd = 1;
        int len = 1, maxLen = 0;  // maxLen은 아직 미적용.
        int[] cntArr = new int[100001];
        cntArr[arr[start]] = 1;
        while(start<=nextEnd && nextEnd<arr.length) {
            // 현재 구간부터 확인 및 적용.
            maxLen = Math.max(maxLen, len);

            int nextEndNum = arr[nextEnd];
            int nextEndCnt = cntArr[nextEndNum];
            if(nextEndCnt + 1 <= k) {
                len++;
                cntArr[nextEndNum]++;
                nextEnd++;
            }
            else {
                len--;
                cntArr[arr[start]]--;
                start++;
            }
        }

        System.out.print(maxLen);
    }
}
