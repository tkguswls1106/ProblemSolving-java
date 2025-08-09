import java.util.*;
import java.io.*;

// - 알고리즘: 다익스트라

public class BOJ_1939 {
    public static int n, m;
    public static ArrayList<Node>[] adj = new ArrayList[10002];
    public static int[] distArr = new int[10002];
    public static final int INF = Integer.MAX_VALUE;

    public static class Node implements Comparable<Node> {
        public int idx;  // nodeIdx
        public int dist;

        public Node(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        public ArrayList<Node> getNextNodeList() {
            return adj[this.idx];
        }

        public int getMaxDist() {
            return distArr[this.idx];
        }

        @Override
        public int compareTo(Node b) {
            if(this.dist != b.dist) {
                return b.dist - this.dist;  // 간선 가중치 기준 내림차순
            }
            else {
                return 0;  // 정렬없이 삽입 순서 그대로 유지함
            }
        }
    }

    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, INF));
        distArr[start] = INF;
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            // 주의: 현재 기준임에도 > 말고 >= 조건을 적용하면, 답 자체가 틀리게됨.
            if(cur.getMaxDist() > cur.dist) continue;  // 맨처음에는 '-1 > Integer.MAX_VALUE' 조건임.

            for(Node next : cur.getNextNodeList()) {
                int minDist = Math.min(cur.dist, next.dist);  // 어차피 cur.dist 의미도 현재'까지 중에서' 최소거리값이니 문제없음.

                // 주의: 다음 기준임에도 >= 말고 > 조건을 적용하면, 답은 맞으나 메모리 초과로 틀리게됨.
                //      만약 '>' 부등호 사용 시, 거리가 같은 경우임에도 불필요하게 큐에 넣고 갱신을 시도해 메모리 초과가 발생할 수 있음.
                //      따라서 이 next 조건문에서는 기존값을 새로 갱신할지 확인하는 용도이므로, '>=' 부등호로 필터링해주어야 함.
                if(next.getMaxDist() >= minDist) continue;

                pq.offer(new Node(next.idx, minDist));
                distArr[next.idx] = minDist;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());
        for(int i=1; i<=n; i++) {
            adj[i] = new ArrayList<>();
            distArr[i] = -1;
        }

        int start, end, betweenDist;
        while(m-- > 0) {
            stt = new StringTokenizer(br.readLine());
            start = Integer.parseInt(stt.nextToken());
            end = Integer.parseInt(stt.nextToken());
            betweenDist = Integer.parseInt(stt.nextToken());

            adj[start].add(new Node(end, betweenDist));
            adj[end].add(new Node(start, betweenDist));  // 문제에 양방향이라고 명시되어 있음.
        }

        stt = new StringTokenizer(br.readLine());
        start = Integer.parseInt(stt.nextToken());
        end = Integer.parseInt(stt.nextToken());

        dijkstra(start);
        System.out.print(distArr[end]);
    }
}
