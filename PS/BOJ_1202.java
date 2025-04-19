import java.util.*;
import java.io.*;

// - 알고리즘 : 우선순위큐
// - 문제 Tip :
// 참고로 우선순위큐 말고 이분탐색 방식으로도 시도해봤지만, 시간초과로 인해 적절하지 않았음.
// => 무게가 작음에도 오히려 가격이 비싼 보석이 있기에, 이분탐색 방식이 크게 메리트가 없음.
// => 혹여 예외 처리를 했다한들, 처음부터 검색 인덱스까지 다시 앞부분을 for문 순회해야하므로, 시간초과가 발생함.

public class BOJ_1202 {
    public static int n = 0, k = 0;
    public static List<Gem> nList = new ArrayList<>();  // 최악의 경우, 배열 정렬보다 빠름. => O(nlog(n))
    public static List<Integer> kList = new ArrayList<>();  // 최악의 경우, 배열 정렬보다 빠름. => O(nlog(n))

    public static class Gem implements Comparable<Gem> {  // 보석
        public int weight;
        public int price;
        
        public Gem(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(Gem b) {  // 커스텀 정렬 (리스트 전용)
            return this.weight - b.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        k = Integer.parseInt(stt.nextToken());

        for(int i=0; i<n; i++) {
            stt = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(stt.nextToken());
            int price = Integer.parseInt(stt.nextToken());
            nList.add(new Gem(weight, price));
        }
        Collections.sort(nList);
        
        for(int i=0; i<k; i++) {
            stt = new StringTokenizer(br.readLine());
            kList.add(Integer.parseInt(stt.nextToken()));
        }
        Collections.sort(kList);  // 가방무게 오름차순 정렬

        PriorityQueue<Gem> pq = new PriorityQueue<>(new Comparator<Gem>() {
            @Override
            public int compare(Gem a, Gem b) {  // 커스텀 정렬 (우선순위큐 전용)
                if(a.price != b.price) {
                    return b.price - a.price;
                }
                else {
                    return b.weight - a.weight;
                }
            }
        });

        int nIdx = 0;  // 우선순위큐가 외부에 있으므로, nList.get(nIdx)를 또다시 중복 offer하지않도록 nIdx도 외부에 선언해야함.
        long sum = 0;  // 주의: int로 선언하면 틀림.
        for(int kWeight : kList) {
            while(nIdx < n && nList.get(nIdx).weight <= kWeight) {
                Gem gem = nList.get(nIdx);
                pq.offer(gem);
                nIdx++;
            }

            if(!pq.isEmpty()) {
                sum += pq.poll().price;
            }
        }

        System.out.print(sum);
    }
}
