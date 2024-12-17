import java.util.*;
import java.io.*;

public class BOJ_10845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        StringTokenizer stt;

        Queue<String> qu = new LinkedList<>();
        int n = Integer.parseInt(br.readLine());
        String last = "-1";
        while(n-- > 0) {
            stt = new StringTokenizer(br.readLine());
            String str = stt.nextToken();

            if(str.equals("push")) {
                str = stt.nextToken();
                qu.offer(str);
                last = str;
            }
            else if(str.equals("pop")) {
                str = qu.poll();
                stb.append(str != null ? str : -1).append("\n");
            }
            else if(str.equals("size")) {
                stb.append(qu.size()).append("\n");
            }
            else if(str.equals("empty")) {
                stb.append(qu.isEmpty() ? 1 : 0).append("\n");
            }
            else if(str.equals("front")) {
                str = qu.peek();
                stb.append(str != null ? str : -1).append("\n");
            }
            else {
                if(qu.isEmpty()) stb.append(-1).append("\n");
                else stb.append(last).append("\n");
            }
        }

        System.out.print(stb.toString());
    }
}
