import java.util.*;
import java.io.*;

public class BOJ_1422 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        StringTokenizer stt = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(stt.nextToken());
        int n = Integer.parseInt(stt.nextToken());
        
        List<String> list = new ArrayList<>();
        int maxNum = -1;
        for(int i=0; i<k; i++) {
            String str = br.readLine();
            list.add(str);
            maxNum = Math.max(maxNum, Integer.parseInt(str));
        }
        for (int i=0; i <n-k; i++) {
            list.add(String.valueOf(maxNum));
        }
        
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                String ab = a + b;
                String ba = b + a;
                return ba.compareTo(ab);
            }
        });

        for(int i=0; i<list.size(); i++) {
            stb.append(list.get(i));
        }

        System.out.print(stb.toString());
    }
}
