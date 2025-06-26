import java.util.*;
import java.io.*;

// [ 최장 공통 부분 수열 (LCS, Longest Common Subsequence) with DP ]
// - 개념 특징 :
// 주어진 두 수열(or 문자열)의 앞에서부터 순차적으로(연속적일 필요 X) 요소를 선택하여, 공통된 가장 긴 부분 수열을 찾는 알고리즘이다.
// - 번외 지식 :
// * 최장 공통 문자열 (Longest Common Substring) :
//   A[BCDE]FG, H[BCDE]GF -> BCDE
//   ==> 답은 4길이인 BCDE
// * 최장 공통 부분수열 (Longest Common Subsequence) :
//   A[BCDEF]G, H[BCDE]G[F] -> BCDEF
//   A[BCDE]F[G], H[BCDEG]F -> BCDEG
//   ==> 답은 5길이인 BCDEF 또는 BCDEG

public class LCS_BOJ_9252 {
    public static int[][] dp = new int[1002][1002];  // 적어도 dp[str1.length()+1][str2.length()+1] 이상의 크기로 선언해야만함.
    public static String str1, str2;
    public static int len1, len2;
    public static StringBuilder stb = new StringBuilder();

    public static void traceLCS() {  // LCS 역추적 메소드
        String str = str1;  // '공통' 부분수열을 탐색할것이므로 str1 또는 str2 중 어느 문자열을 사용해도 무관함.
        int i = len1;
        int j = len2;

        // dp[len1][len2]부터 거꾸로 추적하므로,
        // 차후 LCS 문자열을 올바른 순서로 출력하려면 거꾸로 추적했던 문자를 다시 뒤집어야 하므로 Stack을 활용함.
        // ==> 즉, 역추적으로 먼저 찾은 문자를 나중에 출력하기 위해 Stack을 활용함.
        Stack<Character> st = new Stack<>();
        while(i>=1 && j>=1) {
            if(dp[i][j] == dp[i-1][j]) {
                // - 위쪽(dp[i-1][j])에서 값이 그대로 내려왔었던 경우 :
                // 'str1.charAt(i-1)' 문자는 LCS에 포함되지 않았음을 의미하므로, 'i--'로 위쪽으로 올라가서 건너뜀.
                i--;  // LCS는 공통으로 등장한 문자만 모은 것이므로, 공통이 아닌 문자는 무시(건너뛰기) 해야함.
            }
            else if(dp[i][j] == dp[i][j-1]) {
                // - 왼쪽(dp[i][j-1])에서 값이 그대로 넘어왔었던 경우 :
                // 'str2.charAt(j-1)' 문자는 LCS에 포함되지 않았음을 의미하므로, 'j--'로 왼쪽으로 이동해서 건너뜀.
                j--;  // LCS는 공통으로 등장한 문자만 모은 것이므로, 공통이 아닌 문자는 무시(건너뛰기) 해야함.
            }
            else {
                // - 공통 문자를 찾는 데 성공하여 대각선(dp[i-1][j-1])에서 +1 되며 넘어왔었던 경우 :
                // 'str1.charAt(i-1)'이자 'str2.charAt(j-1)' 문자는 LCS에 포함되었음을 의미이므로, 'i--; j--;'로 왼쪽위 대각선으로 이동해서 추적을 이어감.
                char ch1 = str1.charAt(i-1);  // str1을 사용했으므로, 인덱스도 j가 아닌 i를 사용해 조회해야함. (또는 str2.charAt(j-1)을 써도 무방함.)
                st.push(ch1);
                i--;  // 다음 공통 문자를 추적하기위해 대각선으로 이동.
                j--;
            }
        }

        while(!st.isEmpty()) {
            stb.append(st.pop());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine();
        str2 = br.readLine();
        len1 = str1.length();
        len2 = str2.length();

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

        stb.append(dp[len1][len2]).append("\n");  // LCS의 길이
        traceLCS();  // LCS (문자열)
        System.out.print(stb.toString());
    }
}
