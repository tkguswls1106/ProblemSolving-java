import java.util.*;
import java.io.*;

// - 알고리즘: HashSet

public class BOJ_25192 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int answer = 0;
        Set<String> s = new HashSet<>();
        while(n-- > 0) {
            String inputStr = br.readLine();
            if(inputStr.equals("ENTER")) {
                answer += s.size();
                s.clear();
                continue;
            }
            s.add(inputStr);

            if(n == 0) {
                answer += s.size();
            }
        }

        System.out.print(answer);
    }
}
