import java.util.*;
import java.io.*;

// - 알고리즘: 덱

public class BOJ_5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        String p;
        int n; 
        while(tc-- > 0) {
            p = br.readLine();
            n = Integer.parseInt(br.readLine());
            
            String[] strArr = br.readLine().split("[\\[\\],]");
            Deque<Integer> dq = new ArrayDeque<>();
            for(String str : strArr) {
                if(!str.isEmpty()) dq.offerLast(Integer.parseInt(str));
            }

            boolean isError = false;
            boolean isNotReverse = true;
            for(char ch : p.toCharArray()) {
                if(ch == 'R') {
                    isNotReverse = isNotReverse ? false : true;  // 토글시킴
                }
                else {
                    if(dq.isEmpty()) {
                        isError = true;
                        break;
                    }
                    else {
                        if(isNotReverse) dq.pollFirst();
                        else dq.pollLast();
                    }
                }
            }

            if(isError) stb.append("error\n");
            else {
                if(!isNotReverse) {
                    List<Integer> list = new ArrayList<>(dq);
                    Collections.reverse(list);
                    dq = new ArrayDeque<>(list);
                }
                stb.append(dq.toString().replace(" ", "")).append("\n");
            }
        }

        System.out.print(stb.toString());
    }
}
