import java.util.*;
import java.io.*;

// - 알고리즘: 시뮬레이션 (with Swap)

public class BOJ_10811 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        StringTokenizer stt = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stt.nextToken());
        int m = Integer.parseInt(stt.nextToken());
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = i+1;
        }

        while(m-- > 0) {
            stt = new StringTokenizer(br.readLine());
            int idx1 = Integer.parseInt(stt.nextToken()) - 1;
            int idx2 = Integer.parseInt(stt.nextToken()) - 1;

            // Swap
            while(idx1 < idx2) {
                int temp = arr[idx1];
                arr[idx1] = arr[idx2];
                arr[idx2] = temp;
                idx1++;
                idx2--;
            }
        }
        
        for(int num : arr) {
            stb.append(num).append(" ");
        }
        System.out.print(stb.toString());
    }
}
