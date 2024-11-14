// - 알고리즘: 진법(진수) 변환

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder stb = new StringBuilder();
        int num = 0;
        String str;
        while(true) {
            str = Integer.toString(num, n);  // 반환될 영어는 모두 소문자이므로, 차후 대문자 변환 필수.
            stb.append(str);
            num++;
            if(stb.length() > m*t) break;
        }
        
        StringBuilder stb2 = new StringBuilder();
        int idx = m*(t-1) + p - 1;
        for(int i=p-1; i<=idx; i+=m) {
            stb2.append(stb.charAt(i));
        }
        
        return stb2.toString().toUpperCase();  // 대문자 변환.
    }
}

public class PGS_n진수게임 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
