class Solution {
    public int[] solution(int n, int s) {
        if(n > s) return new int[]{-1};
        if(n==1) return new int[]{s};
        
        int[] answer = new int[n];  // 만약 배열이 아닌 ArrayList로 구현시, 효율성에서 시간초과로 틀리게됨.
        int div = s / n;
        int rest = s % n;
        for(int i=0; i<n; i++) {
            answer[i] = div;
        }
        
        for(int i=n-1; rest>0; i--, rest--) {
            answer[i] += 1;
        }
        
        return answer;
    }
}

public class PGS_최고의집합 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
