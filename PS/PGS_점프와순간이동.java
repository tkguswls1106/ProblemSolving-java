public class Solution {
    public int solution(int n) {
        int cnt = 0;
        while(n != 0) {
            if(n%2 == 0) {
                n /= 2;
            }
            else {
                n--;
                cnt++;
            }
        }
        
        return cnt;
    }
}

public class PGS_점프와순간이동 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
