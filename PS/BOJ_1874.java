import java.util.*;
import java.io.*;

public class BOJ_1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        Stack<Integer> st = new Stack<>();
        int n = Integer.parseInt(br.readLine());
        int maxNum = 0;
        while(n-- > 0) {
            int num = Integer.parseInt(br.readLine());
            
            if(maxNum < num) {
                for(int i=maxNum+1; i<=num; i++) {
                    st.push(i);
                    stb.append("+").append("\n");
                }
                maxNum = Math.max(maxNum, num);
                if(!st.isEmpty()) {
                    st.pop();
                    stb.append("-").append("\n");
                }
            }
            else {  // maxNum >= num 이므로 pop해야하는상황
                if(st.isEmpty()) {
                    System.out.print("NO");
                    return;
                }

                boolean isPop = false;  // 최종 pop에 성공했는가
                while(!st.isEmpty()) {
                    int popNum = st.pop();
                    stb.append("-").append("\n");
                    if(popNum == num) {
                        isPop = true;
                        break;
                    }
                }

                if(!isPop) {
                    System.out.print("NO");
                    return;
                }
            }
        }

        System.out.print(stb.toString());
    }
}
