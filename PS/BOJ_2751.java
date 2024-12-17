import java.util.*;
import java.io.*;

public class BOJ_2751 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        List<Integer> list = new ArrayList<>();
        int n = Integer.parseInt(br.readLine());
        while(n-- > 0) {
            int input = Integer.parseInt(br.readLine());
            list.add(input);
        }
        
        Collections.sort(list);  // Arrays.sort와는 다르게, Timsort를 사용하므로 시간복잡도 nlogn으로 통과 가능.
        for(int num : list) {
            stb.append(num).append("\n");
        }

        System.out.print(stb.toString());
    }
}
