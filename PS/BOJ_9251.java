import java.util.*;
import java.io.*;

// - 알고리즘: LCS with DP

public class BOJ_9251 {
    public static int[][] dp = new int[1002][1002];  // 적어도 dp[str1.length()+1][str2.length()+1] 이상의 크기로 선언해야만함.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();
        int len1 = str1.length();
        int len2 = str2.length();

        // dp[i][j]는 '왼쪽위 대각선 dp[i-1][j-1]' 값을 조회해야하므로,
        // 모두 한줄씩 건너뛰어 0행과 0열은 비워두고 dp 인덱스를 1부터 시작해야만함.
        for(int i=1; i<=len1; i++) {  // dp 배열의 인덱스 (str1의 인덱스가 아니니 주의할것.)
            char ch1 = str1.charAt(i-1);

            for(int j=1; j<=len2; j++) {  // dp 배열의 인덱스 (str2의 인덱스가 아니니 주의할것.)
                char ch2 = str2.charAt(j-1);

                if(ch1 == ch2) {
                    // - 서로 문자가 같을 경우 : '현재칸에 들어갈 값'은 '왼쪽위 대각선'의 값에 +1 한 값을 저장함.
                    // * 만약 그러지않고 해당 위치가 아닌 왼쪽 값에서 +1을 하게되면, 동일한 현재 문자의 LCS길이에 다시 +1을 하는것이기에 초과가 될 수 있음.
                    // * 참고로, 굳이 왼쪽위 대각선과 위쪽의 값을 모두 Math.max()로 비교 고려하지않아도 되는 이유는,
                    //   둘 다 같은 이전 문자 상태를 나타내므로 대각선 값만 고려해도 충분하기 때문임.
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else {
                    // - 서로 문자가 다를 경우 : '현재칸에 들어갈 값'은 '왼쪽'과 '위쪽'의 값 중 더 큰 값을 저장함. 
                    // * 이는 현재의 두 문자가 서로 다르므로, LCS 길이에 +1을 하지 않고 기존 값을 그대로 이어받아야 하는 상황임.
                    //   이때 왼쪽과 위쪽 위치는 각각 서로 다른 문자를 기준으로 한 LCS 길이를 갖고 있기 때문에,
                    //   둘 다 유효한 후보로 간주하여, 이 중 더 큰 값을 선택해 이어받는 것이 최장의 길이를 만드는 올바른 방식임.
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        System.out.print(dp[len1][len2]);
    }
}
