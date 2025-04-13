import java.util.*;

// - 알고리즘: 비트마스킹
// - 문제 Tip:
// 1. 변수 x의 비트 오른쪽부터 0을 1로 한번 변환한 숫자를 활용하면 됨.
// 2. 0->1 변환한 비트의 다음 비트 하나를 1->0으로 바꿔주어야함. (크기를 최소로 만들기위함.)

class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
        for(int i=0; i<numbers.length; i++) {
            long originNum10 = numbers[i];
            long num10 = originNum10;
            String str2 = Long.toString(num10, 2);

            boolean isExist0 = false;
            int idx = str2.length() - 1;
            while(num10 > 0) {
                if((num10 & 1) == 0) {
                    isExist0 = true;
                    break;
                }
                
                num10 >>= 1;
                idx--;
            }
            
            StringBuilder stb;
            if(isExist0 == false) {
                stb = new StringBuilder("1");
                if(idx+1 <= str2.length()-1) {
                    stb.append("0").append(str2.substring(1));
                }
            }
            else {
                stb = new StringBuilder(str2);
                stb.setCharAt(idx, '1');
                if(idx+1 <= str2.length()-1) {
                    stb.setCharAt(idx+1, '0');
                }
            }
            answer[i] = Long.parseLong(stb.toString(), 2);
        }
        
        return answer;
    }
}

public class PGS_2개이하로다른비트 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
