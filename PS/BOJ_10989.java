import java.util.*;
import java.io.*;

// - 알고리즘: Counting Sort
// - 문제 Tip: 
// 리스트는 공간복잡도가 좋지않아, Timsort로 시간 초과는 통과해도 대신 애매하게라도 메모리 초과가 날 수 있음.
// 사실 Counting Sort 외에, Arrays.sort() 방식으로도 이 문제를 통과할 수 있음.

public class BOJ_10989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        Map<Integer, Integer> m = new HashMap<>();
        int n = Integer.parseInt(br.readLine());
        int input;
        while(n-- > 0) {
            input = Integer.parseInt(br.readLine());
            m.put(input, m.getOrDefault(input, 0) + 1);
        }
        
        List<Integer> list = new ArrayList<>(m.keySet());
        Collections.sort(list);

        int cnt;
        for(int num : list) {
            cnt = m.get(num);
            while(cnt-- > 0) {
                stb.append(num).append("\n");
            }
        }

        System.out.print(stb.toString());
    }
}
