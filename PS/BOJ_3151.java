import java.util.*;
import java.io.*;

// - 알고리즘: 투 포인터 + 조합 수학

public class BOJ_3151 {
    public static int getCombCnt(int allCnt) {  // 조합 nC2 개수 구하는 함수
        int permCnt = allCnt * (allCnt - 1);  // 순열 nPr = 'allCnt P 2'
        int combCnt = permCnt / 2;  // 조합 nCr = nPr / r! = permCnt / 2!
        return combCnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        StringTokenizer stt = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            int num = Integer.parseInt(stt.nextToken());
            arr[i] = num;
        }
        Arrays.sort(arr);

        long cnt = 0;  // 주의: int 사용시, 틀림.
        for(int i=0; i<n-2; i++) {
            int restSum = 0 - arr[i];

            int start = i+1, end = arr.length-1;
            while(start<end && end<arr.length) {
                int startEndSum = arr[start] + arr[end];
                if(startEndSum == restSum) {
                    if(arr[start] == arr[end]) {
                        cnt += getCombCnt(end - start + 1);
                        break;
                    }
                    else {
                        int startCnt = 1;
                        for(int j=start+1; j<=end-1; j++) {
                            if(arr[start] == arr[j]) startCnt++;
                            else break;
                        }
                        start += (startCnt - 1);

                        int endCnt = 1;
                        for(int j=end-1; j>=start+1; j--) {
                            if(arr[end] == arr[j]) endCnt++;
                            else break;
                        }
                        end -= (endCnt - 1);

                        cnt += (startCnt * endCnt);
                        start++;
                    }
                }
                else if(startEndSum > restSum) {
                    end--;
                }
                else {
                    start++;
                }
            }
        }

        System.out.print(cnt);
    }
}
