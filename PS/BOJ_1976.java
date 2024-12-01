import java.util.*;
import java.io.*;

// - 알고리즘: 유니온 파인드

public class BOJ_1976 {
    public static int[] parent = new int[202];

    public static int findParent(int x) {
        if(x == parent[x]) return x;
        else return parent[x] = findParent(parent[x]);
    }

    public static void merge(int x, int y) {
        x = findParent(x);
        y = findParent(y);

        if(x == y) return;
        else parent[y] = x;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++) {
            parent[i] = i;
        }

        StringTokenizer stt;
        int num;
        for(int i=0; i<n; i++) {
            stt = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                num = Integer.parseInt(stt.nextToken());
                if(i!=j && num==1) {  // i!=j : 본인이 본인 도시로 가는경우는 제외
                    merge(i, j);
                }
            }
        }

        Set<Integer> s = new HashSet<>();
        stt = new StringTokenizer(br.readLine());
        while(m-- > 0) {
            num = Integer.parseInt(stt.nextToken());
            num--;
            s.add(findParent(num));
            if(s.size() >= 2) {
                System.out.print("NO");
                return;
            }
        }
        System.out.print("YES");
    }
}
