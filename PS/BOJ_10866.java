import java.util.*;
import java.io.*;

public class BOJ_10866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        StringTokenizer stt;

        Deque<String> dq = new ArrayDeque<>();
        int n = Integer.parseInt(br.readLine());
        while(n-- > 0) {
            stt = new StringTokenizer(br.readLine());
            String str = stt.nextToken();

            if(str.equals("push_front")) {
                str = stt.nextToken();
                dq.offerFirst(str);
            }
            else if(str.equals("push_back")) {
                str = stt.nextToken();
                dq.offerLast(str);
            }
            else if(str.equals("pop_front")) {
                str = dq.pollFirst();
                stb.append(str != null ? str : -1).append("\n");
            }
            else if(str.equals("pop_back")) {
                str = dq.pollLast();
                stb.append(str != null ? str : -1).append("\n");
            }
            else if(str.equals("size")) {
                stb.append(dq.size()).append("\n");
            }
            else if(str.equals("empty")) {
                stb.append(dq.isEmpty() ? 1 : 0).append("\n");
            }
            else if(str.equals("front")) {
                str = dq.peekFirst();
                stb.append(str != null ? str : -1).append("\n");
            }
            else {
                str = dq.peekLast();
                stb.append(str != null ? str : -1).append("\n");
            }
        }

        System.out.print(stb.toString());
    }
}
