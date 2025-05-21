import java.util.*;

// - 알고리즘 : 그리디
// - 문제 Tip :
// 10단위로 만드는것이 풀이의 핵심임.
// 특히 1~4 일때는 -1 연산이 더 빠르고, 6~9 일때는 +1 연산이 더 빠름.

class Solution {    
    public int solution(int storey) {
        int answer = 0;
        
        while(storey != 0) {
            int rest = storey % 10;  // 현재 숫자에서 일의 자리
            
            if(1<=rest && rest<=4) {
                answer += rest;
                storey -= rest;
            }
            else if(6<=rest && rest<=9) {
                answer += (10-rest);
                storey += (10-rest);
            }
            else if(rest == 5) {
                answer += 5;

                int next = (storey / 10) % 10;  // 현재 숫자에서 십의 자리
                if(next >= 5) storey += 5;  // 번외로, 이 경우 앞자리가 5이상일때 올림처리하는게 더 유리함.
                else storey -= 5;
            }
            // else인 0일 경우에는 나눠주고 다시 확인하면되므로 코드 필요 X.
            
            storey /= 10;
        }
        
        return answer;
    }
}

public class PGS_마법의엘리베이터 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
