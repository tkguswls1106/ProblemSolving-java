import java.util.*;
import java.io.*;

// - 알고리즘: 슬라이딩 윈도우 (with 투 포인터)

public class BOJ_2559 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stt.nextToken());
        int k = Integer.parseInt(stt.nextToken());
        int[] arr = new int[n+1];  // 투포인터의 구간 내 누적합 문제이므로, n이 아닌 n+1 크기로 설정.

        stt = new StringTokenizer(br.readLine());
        int sum = 0;
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(stt.nextToken());
            if(i < k) sum += arr[i];
        }
        arr[n] = 0;  // 배열길이를 더 늘려서 덧셈시 arr[nextEnd]의 인덱스 초과를 방지. 반드시 맨뒤에 넣을것!!!
        
        int start = 0, nextEnd = k;  // nextEnd = (nowEnd: k - 1) + 1 = k
        int maxSum = Integer.MIN_VALUE;  // 만약 maxSum=-1로 선언한다면, 이와같은 음수 누적합이 가능한 문제에서는 sum값이 -1보다 작아 갱신되지않을수있어 사용하면 안됨.
        while(start<=nextEnd && nextEnd<arr.length) {  // 누적합이므로 웬만해서는 start'<='nextEnd 활용을 추천.
            // 현재 구간부터 확인 및 적용.
            maxSum = Math.max(maxSum, sum);

            // o o o o
            // x o o o o
            // 현재 구간의 앞을 제거. (첫 요소)
            sum -= arr[start++];
            // 다음예정 구간의 뒤를 추가. (새롭게 나타나는 요소)
            sum += arr[nextEnd++];  // 미리 end가 아닌 nextEnd로 시작했으므로, ++end 말고 안전하게 nextEnd++를 사용.
        }

        System.out.print(maxSum);
    }
}
