import java.util.*;

class Solution {    
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        
        int sum = 0;
        for(int i=0; i<A.length; i++) {
            sum += A[i] * B[A.length-1-i];
        }
        
        return sum;
    }
}

public class PGS_최솟값만들기 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
