import java.util.*;

// [ 최대공약수(GCD) & 최소공배수(LCM) ]

class Solution {
    // 최대공약수(GCD)
    public static int gcd(int a, int b) {
        if(b == 0) return a;  // 헷갈릴때 Tip: b비 a아
        else return gcd(b, a%b);  // 헷갈릴때 Tip: b비 a%b압
    }
    
    // 최소공배수(LCM)
    public static int lcm(int a, int b) {
        return a*b / gcd(a,b);
    }
    
    public int[] solution(int n, int m) {
        int numGcd = gcd(n, m);
        int numLcm = lcm(n, m);
        return new int[]{numGcd, numLcm};
    }
}

public class GcdLcm_PGS_최대공약수와최소공배수 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] answer1 = solution.solution(3, 12);
        int[] answer2 = solution.solution(2, 5);

        System.out.println(Arrays.toString(answer1));  // => [3, 12] 출력
        System.out.println(Arrays.toString(answer2));  // => [1, 10] 출력
    }
}
