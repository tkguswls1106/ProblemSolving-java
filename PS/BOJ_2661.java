import java.util.*;

// - 알고리즘: 백트래킹

public class BOJ_2661 {
    public static int n;
    public static StringBuilder selectedStb = new StringBuilder();
    public static String answer;
    public static boolean isEnd = false;

    public static boolean isNotRepeat() {
        int len = selectedStb.length();
        int halfLen = len / 2;  // 뒤에서부터 사이즈세서 비교.

        for(int cmpLen=1; cmpLen<=halfLen; cmpLen++) {
            String frontStr = selectedStb.substring(len - (cmpLen * 2), len - cmpLen);
            String backStr = selectedStb.substring(len - cmpLen, len);
            if(frontStr.equals(backStr)) return false;
        }
        return true;
    }

    public static void bt(int cnt) {
        if(isEnd) return;
        if(cnt == n) {
            // 어차피 ch를 1,2,3 순으로 넣어보기에 조건이 충족되어 사이즈가 가득 차면, 그게 가장 작은수임.
            answer = selectedStb.toString();
            isEnd = true;
            return;
        }

        for(char ch='1'; ch<='3'; ch++) {
            if(selectedStb.length() == 0) {
                selectedStb.append(ch);
                bt(cnt+1);

                selectedStb.deleteCharAt(selectedStb.length()-1);  // len-1 사용하면 안됨.
            }
            else {
                selectedStb.append(ch);
                if(isNotRepeat() == false) {
                    selectedStb.deleteCharAt(selectedStb.length()-1);
                    continue;
                }

                // else if(isNotRepeat() == true)
                bt(cnt+1);

                selectedStb.deleteCharAt(selectedStb.length()-1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        bt(0);
        System.out.print(answer);
    }
}
