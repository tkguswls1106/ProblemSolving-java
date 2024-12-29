import java.util.*;
import java.io.*;

// - 알고리즘: 조합

public class BOJ_6603 {
    public static StringBuilder stb = new StringBuilder();
    public static int[] arr;
    public static List<Integer> selected = new ArrayList<>();
    public static int k = 0;  // 이 문제에선 전체개수인 n과 같은 의미로 쓰임.

    public static void comb(int start, int cnt) {
        if(cnt == 6) {
            for(int num : selected) {
                stb.append(num).append(" ");
            }
            stb.append("\n");
            return;
        }

        for(int i=start; i<k; i++) {
            selected.add(arr[i]);
            comb(i+1, cnt+1);

            selected.remove(selected.size()-1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputStr;
        StringTokenizer stt;
        while(true) {
            inputStr = br.readLine();
            if(inputStr.equals("0")) {
                System.out.print(stb.toString());
                return;
            }

            stt = new StringTokenizer(inputStr);
            k = Integer.parseInt(stt.nextToken());
            arr = new int[k];
            for(int i=0; i<k; i++) {
                arr[i] = Integer.parseInt(stt.nextToken());
            }

            comb(0, 0);
            stb.append("\n");
            selected.clear();
        }
    }
}
