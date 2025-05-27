import java.util.*;
import java.io.*;

// - 알고리즘: 슬라이딩 윈도우 (with 투 포인터)

public class BOJ_15565 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stt.nextToken());
        int k = Integer.parseInt(stt.nextToken());

        List<Integer> lionList = new ArrayList<>();
        stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            int num = Integer.parseInt(stt.nextToken());
            if(num == 1) lionList.add(i);
        }
        // 이 경우, 마지막 요소에 0을 추가해주지 않아도 됨.

        if(lionList.size() < k) {
            System.out.print(-1);
            return;
        }

        int start = 0, nextEnd = k-1;
        int minLen = Integer.MAX_VALUE;
        while(start<=nextEnd && nextEnd<lionList.size()) {
            int startIdx = lionList.get(start);
            int nextEndIdx = lionList.get(nextEnd);
            int len = nextEndIdx - startIdx + 1;
            minLen = Math.min(minLen, len);

            start++;
            nextEnd++;
        }

        System.out.print(minLen);
    }
}
