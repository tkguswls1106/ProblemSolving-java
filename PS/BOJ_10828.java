import java.util.*;
import java.io.*;

public class BOJ_10828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        StringTokenizer stt;

        Stack<String> st = new Stack<>();
        int n = Integer.parseInt(br.readLine());
        while(n-- > 0) {
            stt = new StringTokenizer(br.readLine());
            String str = stt.nextToken();

            if(str.equals("push")) {
                st.push(stt.nextToken());
            }
            else if(str.equals("pop")) {
                if(st.isEmpty()) stb.append(-1).append("\n");
                else stb.append(st.pop()).append("\n");
            }
            else if(str.equals("size")) {
                stb.append(st.size()).append("\n");
            }
            else if(str.equals("empty")) {
                stb.append(st.isEmpty() ? 1 : 0).append("\n");
            }
            else {
                if(st.isEmpty()) stb.append(-1).append("\n");
                else stb.append(st.peek()).append("\n");
            }
        }

        System.out.print(stb.toString());
    }
}
