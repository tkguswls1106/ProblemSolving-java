class Solution {
    public static int getOrder(int num) {
        if(num%2 == 0) return num/2;
        else return num/2+1;
    }
    
    public int solution(int n, int a, int b) {
        if(a > b) {  // swap
            int temp = a;
            a = b;
            b = temp;
        }

        int cnt = 1;
        while(true) {
            if(b-a == 1 && a%2 != 0) {
                return cnt;
            }
            
            b = getOrder(b);
            a = getOrder(a);
            n /= 2;
            cnt++;
        }
    }
}

public class PGS_예상대진표 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
