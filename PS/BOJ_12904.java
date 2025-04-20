import java.util.*;
import java.io.*;

// - 알고리즘 : 그리디
// - 문제 Tip :
// 문자를 추가하며 확인하는 방식으로는 A,B 라는 가짓수가 2개씩 늘어나므로, 시간 효율이 좋지않음.
// 따라서 역순으로 끝자리의 문자를 제거하는 방식을 사용하면, 가짓수를 2개가 아닌 1개씩 사용하며 빠른 풀이가 가능해짐.

public class BOJ_12904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder goal = new StringBuilder(br.readLine());  // stbS (역순 입력)
        StringBuilder cur = new StringBuilder(br.readLine());  // stbT (역순 입력)

        while(cur.length() != goal.length()) {
            char lastCurCh = cur.charAt(cur.length()-1);
            cur.deleteCharAt(cur.length()-1);
            if(lastCurCh == 'B') {
                cur.reverse();
            }
        }

        int isAble = 0;
        if(cur.toString().equals(goal.toString()) == true) {
            isAble = 1;
        }

        System.out.print(isAble);
    }
}
