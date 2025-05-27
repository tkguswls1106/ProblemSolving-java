import java.util.*;
import java.io.*;

// - 알고리즘: 투 포인터

public class BOJ_17609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        while(n-- > 0) {
            String str = br.readLine();

            int start = 0, end = str.length()-1;
            int answer = 0;
            while(start < end) {  // && end < str.length()
                char ch1 = str.charAt(start);
                char ch2 = str.charAt(end);

                if(ch1 != ch2) {  // 주의: 반례 'abbab -> (x)bbab or abba(x)'처럼 한쪽을 삭제해도 문자가 같은 경우도 있으니, 둘 모두를 깊게 확인해봐야함.
                    // case 1.
                    boolean isSame1 = true;
                    int idx1 = start+1;
                    int idx2 = end;
                    while(idx1 < idx2) {
                        if(str.charAt(idx1) != str.charAt(idx2)) {
                            isSame1 = false;
                            break;
                        }
                        else {
                            idx1++;
                            idx2--;
                        }
                    }

                    // case 2.
                    boolean isSame2 = true;
                    idx1 = start;
                    idx2 = end-1;
                    while(idx1 < idx2) {
                        if(str.charAt(idx1) != str.charAt(idx2)) {
                            isSame2 = false;
                            break;
                        }
                        else {
                            idx1++;
                            idx2--;
                        }
                    }

                    // result
                    if(isSame1 == false && isSame2 == false) answer = 2;
                    else answer = 1;
                    break;
                }
                else {
                    start++;
                    end--;
                }
            }

            stb.append(answer).append("\n");
        }

        System.out.print(stb.toString());
    }
}
