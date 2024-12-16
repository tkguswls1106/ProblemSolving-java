import java.util.*;
import java.io.*;

// [ 그리디(Greedy) 로프 문제 ]

public class Greedy_BOJ_2217 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Integer[] arr = new Integer[n];  // 만약 int[]로 선언했다면, 'Arrays.sort(arr, Collections.reverseOrder())' 사용 불가능.
        int idx = 0;
        while(n-- > 0) {
            arr[idx++] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr, Collections.reverseOrder());

        int maxWeight = -1;
        for(int i=0; i<arr.length; i++) {
            int k = i + 1;  // 로프 개수
            int w = arr[i] * k;
            maxWeight = Math.max(maxWeight, w);
            // 참고로 maxWeight값이 갱신에 성공했다고해서 for문을 중지시키거나 max함수없이 갱신하는 방식을 사용하면 안된다. 그 예시로 그 반례의 경우를 밑에 적어두었다.
            // 반례: input값들이 (15,10,6,5) 일때, 나올수있는 maxWeight값들이 (15,20,18,20)
        }

        System.out.print(maxWeight);
    }
}
