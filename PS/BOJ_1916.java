import java.util.*;
import java.io.*;

// - 알고리즘: 다익스트라

public class BOJ_1916 {
    public static int n, m;
    public static ArrayList<Node>[] adj = new ArrayList[1002];
    public static int[] distSumArr = new int[1002];

    public static class Node implements Comparable<Node> {
        public int idx;  // nodeIdx
        public int distSum;

        public Node(int idx, int distSum) {
            this.idx = idx;
            this.distSum = distSum;
        }

        public ArrayList<Node> getNextNodeList() {
            return adj[this.idx];
        }

        public int getMinDistSum() {
            return distSumArr[this.idx];
        }

        @Override
        public int compareTo(Node b) {
            return this.distSum - b.distSum;
        }
    }

    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        distSumArr[start] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            // 주의: 현재 기준임에도 < 말고 <= 조건을 적용하면, 답 자체가 틀리게됨.
            if(cur.getMinDistSum() < cur.distSum) continue;

            for(Node next : cur.getNextNodeList()) {
                int allDistSum = cur.distSum + next.distSum;

                // 주의: 다음 기준임에도 <= 말고 < 조건을 적용하면, 답은 맞으나 메모리 초과로 틀리게됨.
                if(next.getMinDistSum() <= allDistSum) continue;

                pq.offer(new Node(next.idx, allDistSum));
                distSumArr[next.idx] = allDistSum;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        for(int i=1; i<=n; i++) {
            adj[i] = new ArrayList<>();
            distSumArr[i] = Integer.MAX_VALUE;
        }

        StringTokenizer stt;
        int start, end, betweenDist;
        while(m-- > 0) {
            stt = new StringTokenizer(br.readLine());
            start = Integer.parseInt(stt.nextToken());
            end = Integer.parseInt(stt.nextToken());
            betweenDist = Integer.parseInt(stt.nextToken());

            adj[start].add(new Node(end, betweenDist));
        }

        stt = new StringTokenizer(br.readLine());
        start = Integer.parseInt(stt.nextToken());
        end = Integer.parseInt(stt.nextToken());
        dijkstra(start);

        System.out.print(distSumArr[end]);
    }
}
