import java.util.*;
import java.io.*;

// - 알고리즘: DP 누적합
// - 문제 Tip: 최대 시간복잡도 = O(문자열길이20만 x 알파벳종류26 + 질문20만) = O(5400000) = 1초 이내로 연산 가능.

public class BOJ_16139 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        String str = br.readLine();
        int[][] dp = new int[str.length()][26];  // [구간이자인덱스][a~z번호]

        char ch = str.charAt(0);
        int chIdx = ch - 'a';
        dp[0][chIdx] = 1;  // dp[0][chIdx]++;
        for(int i=1; i<str.length(); i++) {
            ch = str.charAt(i);
            chIdx = ch - 'a';
            dp[i][chIdx] = dp[i-1][chIdx] + 1;

            for(int j=0; j<26; j++) {
                if(j==chIdx) continue;
                dp[i][j] = dp[i-1][j];
            }
        }

        int tc = Integer.parseInt(br.readLine());
        StringTokenizer stt;
        while(tc-- > 0) {
            stt = new StringTokenizer(br.readLine());
            ch = stt.nextToken().charAt(0);
            chIdx = ch - 'a';

            int l = Integer.parseInt(stt.nextToken());
            int r = Integer.parseInt(stt.nextToken());
            int cnt = dp[r][chIdx] - (l==0 ? 0 : dp[l-1][chIdx]);
            stb.append(cnt).append("\n");
        }

        System.out.print(stb.toString());
    }
}
