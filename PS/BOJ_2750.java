import java.util.*;
import java.io.*;

public class BOJ_2750 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        List<Integer> list = new ArrayList<>();
        int n = Integer.parseInt(br.readLine());
        while(n-- > 0) {
            int input = Integer.parseInt(br.readLine());
            list.add(input);
        }
        
        Collections.sort(list);
        for(int num : list) {
            stb.append(num).append("\n");
        }

        System.out.print(stb.toString());
    }
}
