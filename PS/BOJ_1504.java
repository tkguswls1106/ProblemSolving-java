import java.util.*;
import java.io.*;

// - 알고리즘 : 다익스트라
// - 문제 Tip :
// 플로이드 워셜 알고리즘을 적용하기에는, O(정점개수^3) = O(800^3) = 512000000 연산 횟수로
// 문제의 시간 제한인 1초 안에 풀이가 불가능함. 따라서 다익스트라를 여러번 활용하는 방안이 옳음.
// 'start -> via1 -> via2 -> end'와 'start -> via2 -> via1 -> end' 경로를 확인하면됨.

public class BOJ_1504 {
    public static int v, e;  // 참고로 v는 문제상으로 N에 해당함.
    public static int via1, via2;
    public static ArrayList<Node>[] adj = new ArrayList[802];
    public static int[] distSumArr = new int[802];
    public static final int INF = 1000 * 200000 + 2;  // Integer.MAX_VALUE 오버플로우 방지

    public static class Node implements Comparable<Node> {
        public int idx;
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

    public static int dijkstra(int start, int end) {
        for(int i=1; i<=v; i++) {
            distSumArr[i] = INF;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        distSumArr[start] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(cur.getMinDistSum() < cur.distSum) continue;

            for(Node next : cur.getNextNodeList()) {
                int allDistSum = cur.distSum + next.distSum;

                if(next.getMinDistSum() <= allDistSum) continue;

                pq.offer(new Node(next.idx, allDistSum));
                distSumArr[next.idx] = allDistSum;
                // 주의: 여기서 end에 도착했다고 조기종료하면 틀림. 더 빠른 경로가 있을 수 있기 때문.
            }
        }

        return distSumArr[end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        v = Integer.parseInt(stt.nextToken());
        e = Integer.parseInt(stt.nextToken());
        for(int i=1; i<=v; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i=0; i<e; i++) {
            stt = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(stt.nextToken());
            int e = Integer.parseInt(stt.nextToken());
            int dist = Integer.parseInt(stt.nextToken());

            adj[s].add(new Node(e, dist));
            adj[e].add(new Node(s, dist));
        }

        stt = new StringTokenizer(br.readLine());
        int start = 1, end = v;
        via1 = Integer.parseInt(stt.nextToken());
        via2 = Integer.parseInt(stt.nextToken());
        
        // 'via1 -> via2' == 'via2 -> via1'
        int viaToViaDist = dijkstra(via1, via2);

        // 'start -> via1'
        int resultDist1 = dijkstra(start, via1);
        // 'via2 -> end'
        resultDist1 += dijkstra(via2, end);

        // 'start -> via2'
        int resultDist2 = dijkstra(start, via2);
        // 'via1 -> end'
        resultDist2 += dijkstra(via1, end);

        int resultDist = Math.min(resultDist1, resultDist2) + viaToViaDist;
        System.out.print(resultDist >= INF ? -1 : resultDist);
    }
}
