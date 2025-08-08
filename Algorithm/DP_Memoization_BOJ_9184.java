import java.util.*;
import java.io.*;

// [ DP - TopDown 방식 (메모이제이션) 응용 ]

public class DP_Memoization_BOJ_9184 {
    public static int[][][] dp = new int[22][22][22];  // 문제상 최대 w(20, 20, 20)로 변환됨.

    public static boolean checkDpRange(int a, int b, int c) {
        return (0<=a && a<=20) && (0<=b && b<=20) && (0<=c && c<=20);  // DP 배열의 값 조회시, 인덱스 에러를 방지하기위함.
    }

    public static int memoW(int a, int b, int c) {
        if(checkDpRange(a, b, c) && dp[a][b][c] != 0) return dp[a][b][c];

        if(a<=0 || b<=0 || c<=0) {
            return 1;  // 'return dp[a][b][c] = 1;'로 작성하면 틀림.
        }
        if(a>20 || b>20 || c>20) {
            return dp[20][20][20] = memoW(20, 20, 20);  // 'return dp[a][b][c] = memoW(20, 20, 20)'로 작성하면 틀림.
        }
        if(a<b && b<c) {
            return dp[a][b][c] = memoW(a, b, c-1) + memoW(a, b-1, c-1) - memoW(a, b-1, c);
        }
        return dp[a][b][c] = memoW(a-1, b, c) + memoW(a-1, b-1, c) + memoW(a-1, b, c-1) - memoW(a-1, b-1, c-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        StringTokenizer stt;
        int a, b, c;
        while(true) {
            String inputStr = br.readLine();
            if(inputStr.equals("-1 -1 -1")) break;

            stt = new StringTokenizer(inputStr);
            a = Integer.parseInt(stt.nextToken());
            b = Integer.parseInt(stt.nextToken());
            c = Integer.parseInt(stt.nextToken());
            int result = memoW(a, b, c);

            stb.append("w(")
                .append(a).append(", ").append(b).append(", ").append(c)
                .append(") = ").append(result).append("\n");
        }

        System.out.print(stb.toString());
    }
}
