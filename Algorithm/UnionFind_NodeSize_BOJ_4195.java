import java.util.*;
import java.io.*;

// [ 유니온 파인드 집합 내 요소의 개수 문제 (nodeSize) ]

public class UnionFind_NodeSize_BOJ_4195 {
    public static int[] parent = new int[200002];
    public static int[] nodeSize = new int[200002];
    
    public static int findParent(int x) {
        if(x == parent[x])
            return x;
        else
            return parent[x] = findParent(parent[x]);
    }

    public static void merge(int x, int y) {  // nodeSize도 함께 관리.
        x = findParent(x);
        y = findParent(y);

        if(x == y) {
            return;
        }   
        else {
            parent[y] = x;  // x를 y의 부모로 지정.
            nodeSize[x] += nodeSize[y];  // nodeSize를 부모가 된 x에게 추가해줌.
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt;
        StringBuilder stb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        while(tc-- > 0) {
            int f = Integer.parseInt(br.readLine());
            Map<String, Integer> m = new HashMap<>();  // <사용자, 사용자를 대신 의미할 인덱스 지정>
            int idx = 0;

            // 초기화
            for(int i=0; i<f*2; i++) {
                parent[i] = i;
                nodeSize[i] = 1;  // nodeSize도 초기화시켜야하며, 1로 설정한다.
            }

            while(f-- > 0) {
                stt = new StringTokenizer(br.readLine());
                String str1 = stt.nextToken();
                String str2 = stt.nextToken();

                // 두 사용자를 의미할 인덱스 부여.
                m.putIfAbsent(str1, idx++);  // Map에 str1 사용자가 없다면, 새로 추가.
                m.putIfAbsent(str2, idx++);  // Map에 str2 사용자가 없다면, 새로 추가.

                merge(m.get(str1), m.get(str2));  // 두 사용자를 친구로 이음.
                int rootIdx = findParent(m.get(str1));
                int size = nodeSize[rootIdx];

                stb.append(size).append("\n");
            }
        }

        System.out.print(stb.toString());
    }
}
