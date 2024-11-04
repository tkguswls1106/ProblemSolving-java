class Solution {
    public int[] solution(int n, long left, long right) {
        int len = (int) (right - left) + 1;
        int answer[] = new int[len];

        int idx = 0;
        for (long i=left; i<=right; i++) {
            long row = i / n;
            long col = i % n;
            
            answer[idx++] = (int) Math.max(row+1, col+1);
        }

        return answer;
    }
}

public class PGS_n2배열자르기 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
