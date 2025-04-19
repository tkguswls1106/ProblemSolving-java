import java.util.*;
import java.io.*;

// - 알고리즘: 매개변수 탐색 (with 이분 탐색)

public class BOJ_2512 {
    public static int n = 0, m = 0;
    public static int[] arr = new int[10002];

    public static boolean isValidPrice(int minPrice) {  // 최소상한액(minPrice) 이상으로 예산을 배정할 수 있는가? => 추가 확장성 확인.
        long sum = 0;
        for(int i=0; i<n; i++) {
            sum += (arr[i] <= minPrice ? arr[i] : minPrice);
            if(sum > m) break;  // 성능 최적화
        }

        return sum <= m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        int right = -1;
        StringTokenizer stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(stt.nextToken());
            right = Math.max(right, arr[i]);
        }
        m = Integer.parseInt(br.readLine());

        int left = 1;
        while(left <= right) {
            int mid = (left + right) / 2;

            if(isValidPrice(mid) == true) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        System.out.print(right);
    }
}
