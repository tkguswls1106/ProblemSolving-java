import java.util.*;
import java.io.*;

// - 알고리즘: 그리디

public class BOJ_1026 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n1 = Integer.parseInt(br.readLine());
        int n2 = n1;

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        StringTokenizer stt = new StringTokenizer(br.readLine());
        int input;
        while(n1-- > 0) {
            input = Integer.parseInt(stt.nextToken());
            list1.add(input);
        }

        stt = new StringTokenizer(br.readLine());
        n1 = n2;
        while(n2-- > 0) {
            input = Integer.parseInt(stt.nextToken());
            list2.add(input);
        }

        Collections.sort(list1);
        Collections.sort(list2, Collections.reverseOrder());

        int answer = 0;
        for(int i=0; i<n1; i++) {
            answer += (list1.get(i) * list2.get(i));
        }

        System.out.print(answer);
    }
}
