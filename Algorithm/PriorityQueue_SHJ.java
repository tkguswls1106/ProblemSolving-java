import java.util.*;
import java.awt.*;  // Point

// [ 우선순위 큐 (Priority Queue) ]
// - 개념 특징:
// * 우선순위큐는 완전이진트리 형태의 힙 자료구조의 알고리즘이다.
// * 우선순위가 가장 높은 원소의 확인, 원소의 추가, 우선순위가 가장 높은 원소의 제거 이 3개의 기능을 제공한다.
// * 반정렬로써, 전체적인 정렬은 안되어있지만, 최댓값 또는 최솟값을 구하기에 매우 적합하다.
// * 중복된 값이 허용된다.
// - 시간 복잡도:
// * 우선순위가 가장 높은 원소 확인: O(1), 원소의 추가: O(logN), 우선순위가 가장 높은 원소 제거: O(logN)
// * PriorityQueue는 Set보다 수행 속도가 빠르고 공간도 적게 차지하기때문에,
//   PriorityQueue에서 제공하는 연산들만 필요할 경우에는, Set을 쓰는것보다 PriorityQueue를 쓰는게 좋다.

public class PriorityQueue_SHJ {
    public static class Pair implements Comparable<Pair> {
        public int num1;
        public int num2;

        public Pair(int num1, int num2) {
            this.num1 = num1;
            this.num2 = num2;
        }

        @Override
        public int compareTo(Pair b) {
            if(this.num1 != b.num1) {
                return this.num1 - b.num1;  // num1 기준 오름차순
            }
            else {
                return b.num2 - this.num2;  // num2 기준 내림차순
            }
        }
    }

    public static void printPQ(PriorityQueue<Integer> pq) {
        StringBuilder stb = new StringBuilder();
        while(!pq.isEmpty()) {
            stb.append(pq.poll()).append(" ");
        }
        System.out.println(stb.toString());
    }

    public static void printPointPQ(PriorityQueue<Point> pq) {
        StringBuilder stb = new StringBuilder();
        while(!pq.isEmpty()) {
            Point p = pq.poll();
            String printStr = String.format("[%d %d] ", p.x, p.y);
            stb.append(printStr);
        }
        System.out.println(stb.toString());
    }

    public static void printPairPQ(PriorityQueue<Pair> pq) {
        StringBuilder stb = new StringBuilder();
        while(!pq.isEmpty()) {
            Pair p = pq.poll();
            String printStr = String.format("[%d %d] ", p.num1, p.num2);
            stb.append(printStr);
        }
        System.out.println(stb.toString());
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();  // 최소힙
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Collections.reverseOrder());  // 최대힙
        PriorityQueue<Integer> pq3 = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                if(Math.abs(a) != Math.abs(b)) {
                    return Math.abs(a) - Math.abs(b);  // 절댓값 기준 오름차순
                }
                else {
                    return a - b;  // 실제값 기준 오름차순
                }
            }
        });
        PriorityQueue<Point> pq4 = new PriorityQueue<>((a, b) -> {
            if(a.x != b.x) {
                return a.x - b.x;  // x 기준 오름차순
            }
            else {
                return b.y - a.y;  // y 기준 내림차순
            }
        });
        PriorityQueue<Pair> pq5 = new PriorityQueue<>();
        
        // pq1
        pq1.offer(5);
        pq1.offer(1);
        pq1.offer(7);
        pq1.offer(1);
        printPQ(pq1);  // => 1 1 5 7

        // pq2
        pq2.offer(5);
        pq2.offer(1);
        pq2.offer(7);
        pq2.offer(1);
        printPQ(pq2);  // => 7 5 1 1

        // pq3
        pq3.offer(1);
        pq3.offer(-1);
        pq3.offer(1);
        pq3.offer(-5);
        pq3.offer(5);
        pq3.offer(8);
        pq3.offer(-12);
        printPQ(pq3);  // => -1 1 1 -5 5 8 -12

        // pq4
        pq4.offer(new Point(1, 3));
        pq4.offer(new Point(1, 4));
        pq4.offer(new Point(2, 2));
        pq4.offer(new Point(2, 2));
        pq4.offer(new Point(2, 7));
        pq4.offer(new Point(3, 1));
        pq4.offer(new Point(3, 8));
        printPointPQ(pq4);  // => [1 4] [1 3] [2 7] [2 2] [2 2] [3 8] [3 1]

        // pq5
        pq5.offer(new Pair(1, 3));
        pq5.offer(new Pair(1, 4));
        pq5.offer(new Pair(2, 2));
        pq5.offer(new Pair(2, 2));
        pq5.offer(new Pair(2, 7));
        pq5.offer(new Pair(3, 1));
        pq5.offer(new Pair(3, 8));
        printPairPQ(pq5);  // => [1 4] [1 3] [2 7] [2 2] [2 2] [3 8] [3 1]
    }
}
