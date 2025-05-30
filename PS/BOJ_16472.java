import java.util.*;
import java.io.*;

// - 알고리즘: 투 포인터

public class BOJ_16472 {
    public static StringBuilder inputStb = new StringBuilder();
    public static Map<Character, Integer> m = new HashMap<>();

    public static void mPut(int end) {
        char endCh = inputStb.charAt(end);
        m.put(endCh, m.getOrDefault(endCh, 0) + 1);  // 참고로 마지막의 '0' 값은 범위 체크용이므로, 따로 if(endCh != '0')처럼 조건 확인할 필요없음.
    }

    public static void mRemove(int start) {
        char startCh = inputStb.charAt(start);
        m.put(startCh, m.get(startCh) - 1);
        if(m.get(startCh) == 0) {
            m.remove(startCh);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        inputStb.append(br.readLine()).append("0");  // 단지 while 범위 체크용임.

        int start = 0, end = 0;
        int maxLen = -1;
        while(start<=end && end<inputStb.length()) {
            if(m.size() <= n) {
                int realEnd = end - 1;  // 이때 end는 이전의 'end++'로 인해 +1된 상태이므로, end-1 값을 사용해야함.
                int len = realEnd - start + 1;
                maxLen = Math.max(maxLen, len);
                mPut(end);  // 주의: 이때는 realEnd 말고 end 사용할것!
                end++;
            }
            else {
                mRemove(start);
                start++;
            }
        }

        System.out.print(maxLen);
    }
}
