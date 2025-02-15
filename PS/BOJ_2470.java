import java.util.*;
import java.io.*;

// - 알고리즘: 투 포인터

public class BOJ_2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());  // '2<=N<=10만'이므로 투포인터 활용시, 최대 O(10만) 시간복잡도인 1초 내로 풀이 가능.
        int[] arr = new int[n];

        StringTokenizer stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            int num = Integer.parseInt(stt.nextToken());
            arr[i] = num;
        }
        Arrays.sort(arr);

        int start = 0, end = arr.length-1;
        int minValue = Integer.MAX_VALUE;  // 약 21억. 만약 '(int) 1e9' 사용시, 이는 10억값까지밖에 표현이 안되므로 이 문제에서는 틀리게 처리됨.
        int num1 = 0, num2 = 0;
        while(start<end && end<arr.length) {
            int value = Math.abs(arr[start] + arr[end]);
            if(value < minValue) {
                minValue = value;
                num1 = arr[start];
                num2 = arr[end];
                // if(value == 0) break;
            }

            if(Math.abs(arr[start]) >= Math.abs(arr[end])) {  // arr[start]의 절댓값이 같거나 더 크므로, 이를 더 줄이기위해 start를 앞으로 옮길 필요가있음.
                start++;
            }
            else {
                end--;
            }
        }

        System.out.printf("%d %d", num1, num2);
    }
}
