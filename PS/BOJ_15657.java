import java.util.*;
import java.util.stream.*;
import java.io.*;

// - 알고리즘: 중복순열 

public class BOJ_15657 {
    public static int n, m;
    public static List<Integer> allList = new ArrayList<>();
    public static List<Integer> selected = new ArrayList<>();
    public static StringBuilder stb = new StringBuilder();

    public static void perm(int cnt) {
        if(cnt == m) {
            for(int num : selected) {
                stb.append(num).append(" ");
            }
            stb.append("\n");
            return;
        }
        
        for(int i=0; i<n; i++) {
            int num = allList.get(i);
            if(!selected.isEmpty() && selected.get(selected.size()-1) > num) continue;

            selected.add(num);
            perm(cnt+1);

            selected.remove(selected.size()-1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());

        stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            allList.add(Integer.parseInt(stt.nextToken()));
        }
        allList = allList.stream()
            .distinct()
            .sorted()
            .collect(Collectors.toList());
        n = allList.size();  // 리스트 길이값 갱신

        perm(0);

        System.out.print(stb.toString());
    }
}
