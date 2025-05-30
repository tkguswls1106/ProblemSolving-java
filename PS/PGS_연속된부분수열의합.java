import java.util.*;

// - 알고리즘: 투 포인터

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] arr = new int[sequence.length + 1];  // 누적합 판단을 위해 끝에 0을 추가.
        for(int i=0; i<sequence.length; i++) {
            arr[i] = sequence[i];
        }
        
        int start = 0, end = 0;
        int sum = 0;
        int idx1 = 0, idx2 = 0;
        int minLen = Integer.MAX_VALUE;
        while(start<=end && end<arr.length) {
            if(sum == k) {
                int realEnd = end - 1;  // 이때 end는 'sum += arr[end++]'로 인해 +1된 상태이므로, end-1 값을 사용해야함.
                int len = realEnd - start + 1;
                if(len < minLen) {
                    idx1 = start;
                    idx2 = realEnd;
                    minLen = len;
                }
                sum -= arr[start];
                start++;
            }
            else if(sum > k) {
                sum -= arr[start];
                start++;
            }
            else {  // (sum < k)
                sum += arr[end];
                end++;
            }
        }
        
        return new int[]{ idx1, idx2 };
    }
}

public class PGS_연속된부분수열의합 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
