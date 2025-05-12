import java.util.*;
import java.io.*;

// - 알고리즘: 우선순위큐 + 그리디

public class BOJ_1826 {
    public static class GasStation implements Comparable<GasStation> {
        public int dist;
        public int fuel;

        public GasStation(int dist, int fuel) {
            this.dist = dist;
            this.fuel = fuel;
        }

        @Override
        public int compareTo(GasStation b) {
            return this.dist - b.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<GasStation> list = new ArrayList<>();
        StringTokenizer stt;
        int a, b;
        while(n-- > 0) {
            stt = new StringTokenizer(br.readLine());
            a = Integer.parseInt(stt.nextToken());
            b = Integer.parseInt(stt.nextToken());
            list.add(new GasStation(a, b));
        }

        stt = new StringTokenizer(br.readLine());
        int townDist = Integer.parseInt(stt.nextToken());
        int restFuel = Integer.parseInt(stt.nextToken());
        if(townDist <= restFuel) {
            System.out.print(0);
            return;
        }
        list.add(new GasStation(townDist, 0));  // 최종도착지(마을) 추가
        Collections.sort(list);

        PriorityQueue<Integer> fuelPq = new PriorityQueue<>(Collections.reverseOrder());
        int prevDist = 0;
        int cnt = 0;
        for(GasStation gs : list) {  // 0 .. 주유소 .. 마을 (주의: 도착'할' 위치 기준임. 도착'한' 위치가 아님.)
            int curDist = gs.dist;
            int curFuel = gs.fuel;

            int needFuel = curDist - prevDist;
            if(restFuel - needFuel < 0) {  // 연료를 채워야만 현재위치에 도달할 수 있는 경우
                while(!fuelPq.isEmpty()) {  // 도착이 가능해질때까지 연료 채움
                    restFuel += fuelPq.poll();
                    cnt++;
                    if(restFuel - needFuel >= 0) break;  // 불필요하게 더 채우는것을 방지.
                }

                if(restFuel - needFuel < 0) {  // 연료를 가능한 한 최대한 채웠음에도, 불가능한 경우.
                    System.out.print(-1);
                    return;
                }
            }

            fuelPq.offer(curFuel);
            restFuel -= needFuel;
            prevDist = curDist;
        }

        System.out.print(cnt);
    }
}
