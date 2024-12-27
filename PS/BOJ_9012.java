import java.util.*;
import java.io.*;

public class BOJ_9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        while(n-- > 0) {
            Stack<Character> st = new Stack<>();
            String str = br.readLine();

            boolean isVps = true;
            for(char ch : str.toCharArray()) {
                if(ch == '(') st.push(ch);
                else {
                    if(!st.isEmpty()) {
                        st.pop();
                    }
                    else {
                        isVps = false;
                        break;
                    }
                }
            }
            if(!st.isEmpty()) {
                isVps = false;
            }

            stb.append(isVps ? "YES" : "NO").append("\n");
        }

        System.out.print(stb.toString());
    }
}
