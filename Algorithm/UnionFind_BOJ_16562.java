import java.util.*;
import java.io.*;

// [ 유니온 파인드 cost 문제 ]

public class UnionFind_BOJ_16562 {
    public static int[] parent = new int[10002];
    public static int[] cost = new int[10002];
    
    public static int findParent(int x) {
        if(x == parent[x])
            return x;
        else
            return parent[x] = findParent(parent[x]);
    }

    public static void merge(int x, int y) {
        x = findParent(x);
        y = findParent(y);

        if(x == y) {
            return;
        }
        else {
            // 비용이 더 낮은것을 부모로 설정해서 합집합을 실시해야함.
            if(cost[x] < cost[y]) {
                parent[y] = x;  // x를 y의 부모로 지정.
            }
            else {
                parent[x] = y;  // y를 x의 부모로 지정.
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stt.nextToken());
        int m = Integer.parseInt(stt.nextToken());
        int k = Integer.parseInt(stt.nextToken());

        stt = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {  // 문제의 명시상으로, 친구 번호가 1부터 시작함.
            int money = Integer.parseInt(stt.nextToken());

            parent[i] = i;
            cost[i] = money;
        }

        int v, w;
        while(m-- > 0) {
            stt = new StringTokenizer(br.readLine());
            v = Integer.parseInt(stt.nextToken());
            w = Integer.parseInt(stt.nextToken());
            
            if(v == w) continue;  // 자기 자신과 친구일때
            merge(v,w);
        }

        int sum = 0;
        Set<Integer> rootNodeSet = new HashSet<>();  // 최상위 노드 Set (with 중복제거)
        for(int i=1; i<=n; i++) {
            rootNodeSet.add(findParent(i));
        }
        for(int rootIdx : rootNodeSet) {
            sum += cost[rootIdx];
        }
        
        System.out.print((k >= sum) ? sum : "Oh no");
    }
}
