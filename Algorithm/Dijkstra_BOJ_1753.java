import java.util.*;
import java.io.*;

// [ 다익스트라 알고리즘 (Dijkstra Algorithm) ]
// - 개념 특징 :
// 그래프의 특정한 한 정점에서 출발하여, 다른 모든 정점까지의 최단 경로를 구하는 알고리즘이다.
// 단, 이는 '가중치가 음수인 간선'이 없다는 전제를 기반으로 동작한다.
// - 시간복잡도 :
// 정점 개수가 V, 간선 개수가 E 일때
// 이중 for문 방식은, 모든 정점 중에서 매 단계마다 미방문 정점의 최소 거리를 선형 탐색 => O(V^2) 느림.
// 우선순위큐 방식은, 큐 삽입/삭제가 O(logV)이고 이 작업이 간선만큼 최대 E번 발생 => O(ElogV) 빠름.
// - BFS vs 다익스트라 :
// BFS 문제는 '단순 최단 이동거리 혹은 이동횟수를 구하는 것'을 목적으로 함.
// 반면, 다익스트라 문제는 '각 이동 간의 비용을 고려해 최소한의 힘으로 가장 빠른 길을 찾는 것'을 목적으로 함.

public class Dijkstra_BOJ_1753 {
    public static int v, e, k;
    public static ArrayList<Node>[] adj = new ArrayList[20002];  // 실상 V+1 길이만큼만 선언하면 됨.
    public static int[] distSumArr = new int[20002];  // minDistSumArr. 실상 V+1 길이만큼만 선언하면 됨.

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
            if(this.distSum != b.distSum) {
                return this.distSum - b.distSum;  // 간선 가중치 기준 오름차순
            }
            else {
                return 0;  // 정렬없이 삽입 순서 그대로 유지함
            }
        }
    }

    // 시작노드(start), 현재노드(cur), 다음노드(next) 일때
    // * distSumArr[next.idx] : 'start → ... → cur → next' 최소 전체 거리합 (차후 갱신 가능)
    // * cur.distSum : 'start → ... → cur' 거리합
    // * next.distSum : 'cur → next' 1개 간선 가중치
    // * cur.distSum + next.distSum : 'start → ... → cur → next' 전체 거리합 (distSumArr 갱신 후보)
    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        distSumArr[start] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();  // 현재 최소 비용을 갖는 노드

            if(cur.getMinDistSum() < cur.distSum) continue;  // 중복노드 방지용이기도 함.

            for(Node next : cur.getNextNodeList()) {
                int allDistSum = cur.distSum + next.distSum;

                if(next.getMinDistSum() < allDistSum) continue;

                pq.offer(new Node(next.idx, allDistSum));  // 주의: 더한 거리합계로 새 노드를 만들어 큐에 넣어줘야함.
                distSumArr[next.idx] = allDistSum;  // distSumArr 갱신 성공.
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        StringTokenizer stt = new StringTokenizer(br.readLine());
        v = Integer.parseInt(stt.nextToken());
        e = Integer.parseInt(stt.nextToken());
        k = Integer.parseInt(br.readLine());
        for(int i=1; i<=v; i++) {
            adj[i] = new ArrayList<>();
            distSumArr[i] = Integer.MAX_VALUE;
        }

        int start, end, betweenDist;
        while(e-- > 0) {
            stt = new StringTokenizer(br.readLine());
            start = Integer.parseInt(stt.nextToken());
            end = Integer.parseInt(stt.nextToken());
            betweenDist = Integer.parseInt(stt.nextToken());  // cost 이면서 distSum을 의미.

            adj[start].add(new Node(end, betweenDist));
        }

        dijkstra(k);

        for(int i=1; i<=v; i++) {
            stb.append(distSumArr[i] != Integer.MAX_VALUE ? distSumArr[i] : "INF").append("\n");
        }
        System.out.print(stb.toString());
    }
}
