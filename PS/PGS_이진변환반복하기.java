class Solution {
    public int[] solution(String s) {
        int convertCnt = 0;
        int removeCnt = 0;
        
        while(!s.equals("1")) {
            int beforeLen = s.length();
            s = s.replaceAll("0", "");
            int afterLen = s.length();
            if(beforeLen - afterLen > 0) removeCnt += (beforeLen - afterLen);

            int len = afterLen;
            s = Integer.toString(len, 2);
            convertCnt++;
        }
        
        return new int[]{convertCnt, removeCnt};
    }
}

public class PGS_이진변환반복하기 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
