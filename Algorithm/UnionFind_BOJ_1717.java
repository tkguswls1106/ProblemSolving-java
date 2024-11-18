import java.util.*;
import java.io.*;

// [ 유니온 파인드 (Union-Find) - 기초 ]

// - 개념 특징 :
// 유니온 파인드란, 여러 값 노드들이 존재하고 있을 때, 이들이 서로 같은 집합에 있는가를 판별하는 알고리즘이다.
// 이는 서로소 집합으로도 불리는 '분리 집합 (Disjoint-Set)' 자료구조를 사용하며, 이미 존재하는 집합에 대해 겹치는 부분이 발생하지 않도록 모든 원소들을 분리한 서로 다른 부분집합을 의미한다.
// 이처럼 유니온 파인드는 '분리 집합 (= 서로 공통원소가 없는 집합)'을 구현하는 알고리즘으로, 각 그룹을 트리의 형태로 표현한다.

// - 코드 구조:
// * 초기화: 메인함수에서 맨 처음과정으로 시행해야한다. 트리의 개념을 사용하기 때문에, 'parent[i] = i;' 처럼 각 노드들의 부모값을 자기 자신값으로 초기화 해줘야 한다.
// * 합치기(union) 연산 함수: 두 원소 a,b가 주어질 때, 이들이 속한 두 집합을 하나로 합친다. => 합집합
// * 찾기(find) 연산 함수: 어떤 원소 a가 주어질 때 이 원소가 속한 집합을 반환한다. 정확히는, 루트노드값을 반환해준다.
// 주로 union(합집합 함수)과 find(루트노드값을 찾는 함수) 함수로 코드를 표현한다. 두 원소의 find()함수값이 같다면, 둘은 같은 집합에 속해있다는 의미이다.
// 참고로 parent[x]는 x의 부모노드값을 의미한다. 만약 findParent()함수에 경로 압축 최적화를 적용한다면, 이는 나중에 x의 루트노드값으로 갱신될 예정이다.

// - find 최적화 (경로 압축 최적화):
// findParent() 함수에서 'return findParent(parent[x]);' 말고 'return parent[x] = findParent(parent[x]);' 로 사용하는 이유는, 경로 압축 최적화 때문이다.
// 이는 유니온파인드의 시간복잡도가 트리의 높이에 비례해서 증가하기때문인데, 즉슨 선형적인 트리가 되어버릴경우 시간복잡도가 O(N)으로 최악이 될 수 있어 최적화를 해주어야 한다.
// 만약 전자의 방법을 사용한다면 선형적인 트리일때, findParent() 함수를 매번 실행할때마다 재귀를 해주어야해서 시간복잡도가 O(N)이 되어버리기때문에, 시간이 길어져 의미가 없어진다.
// 반면 후자의 방법을 사용한다면 선형적인 트리일때, findParent(parent[x]) 부분이 루트노트에 도착하여 재귀가 모두 종료되고 루트노드값을 반환받아와서 하나하나 다시 돌아가면서
// 루트노드의 경로상에 존재했던 모든 자식노드들의 '부모값을 나타내는 parent 배열값'에 루트노드값을 전부 적용해줌으로써 갱신시켜,
// 차후에 다시 이 함수를 사용한다면 이번에는 거의 상수의 시간복잡도로 findParent()함수를 사용할 수 있게되어 매우 빠르게 실행시킬 수 있다.
// ==> 예시 및 정리
// 예시로 다음을 들어보겠다. x와 y가 같은 집합에 속해있는지 확인하려면, 그 둘의 루트노드값이 같은지 확인하면 된다.
// 이를 위해 먼저 main함수 등등의 다른함수에서 x의 루트노드값을 구하고자, 외부함수인 findParent(x)를 호출한다면,
// 해당 함수 내의 'return parent[x] = findParent(parent[x]);'에서
// findParent(parent[x])를 통해 x가 속한 집합의 루트노드값을 반환받아 가져오고, 이 루트노드값을 'x의 부모노드값을 의미하는 parent[x]'에다가 할당시켜 갱신시킴으로써,
// 차후에 처음처럼 findParent(x)를 재호출하였을때, 경로를 또다시 타고타고 찾아갈 필요가 없어 찾기 연산이 중복된 계산을 여러번 하지 않은채로 빠르게 루트값을 찾을 수 있다.
// 그리고 참고로 findParent(x) 함수 실행의 최종 return값으로, 루트노드값으로 갱신된 parent[x] 값을 반환해주며 함수가 최종 종료된다.
// 즉, 경로 압축 최적화 코드를 적용한다면, 나중에 또 x값의 루트값을 찾고자 하더라도, 부모노드를 타고타고 올라가 확인할 필요없이, 바로 갱신된 루트노트값을 전달해줄수 있다는 것이다.
// 이를 통해 혹시나 최악의 선형적인 트리일 경우에도, findParent()함수 호출시 O(N)의 시간복잡도가 아닌, O(1)처럼 상수의 시간복잡도가 나온다.

public class UnionFind_BOJ_1717 {
    public static int[] parent = new int[1000002];  // 인덱스값은 parent[0 ~ n+1] 이지만, 어차피 문제에서 1<=n<=1000000 라고 명시되어있음. 원소의 개수만큼 공간이 존재하면됨.

    public static int findParent(int x) {  // 루트노드값을 찾아 반환해주는 함수 (find)
        if(x == parent[x]) {
            // 루트노드값의 parent값은 처음에 초기화했던 본인의 값을 그대로 지니고 있다. 즉, 루트노드의 부모값은 루트본인값이다. 이는 본인이 최상위기때문에 더이상 부모가 존재하지 않기 때문임.
            // 그러므로, 이처럼 if(x == parent[x])의 의미는, 루트노드에 도달하였느냐는 의미인 것이다.
            return x;  // 루트노드에 도달하였다면, 해당 루트노드값을 반환하며 재귀 종료해서 점차 돌아가기.
        }

        return parent[x] = findParent(parent[x]);  // 경로 압축 최적화임. findParent(parent[x])를 할당받은 parent[x]를 반환함.
    }

    public static void merge(int x, int y) {  // 합집합 함수 (union)
        x = findParent(x);  // x의 루트노드값 가져옴.
        y = findParent(y);  // y의 루트노드값 가져옴.

        if(x == y) {  // 루트노드값이 같으므로 만약 x와 y가 이미 같은 집합에 속해있다면?
            return;  // 합집합을 해줄 이유가 없으므로 함수를 바로 종료시킴.
        }
        else {
            parent[y] = x;  // y 루트노드의 부모를 x의 루트노드로 지정함으로써, 연결시켜 하나의 집합으로 합쳐서 합집합으로 만듦.
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        StringBuilder stb = new StringBuilder();

        int n = Integer.parseInt(stt.nextToken());
        int m = Integer.parseInt(stt.nextToken());

        for(int i=0; i<=n; i++) {
            parent[i] = i;  // 알고리즘 과정에서 우선적으로 먼저, 각 노드들의 부모값을 자기 자신값으로 초기화 해줘야함.
        }

        int checkSameSet, a, b;
        while(m-- > 0) {
            stt = new StringTokenizer(br.readLine());
            checkSameSet = Integer.parseInt(stt.nextToken());
            a = Integer.parseInt(stt.nextToken());
            b = Integer.parseInt(stt.nextToken());

            if(checkSameSet == 0) {  // 합집합 연산 실행.
                merge(a, b);
            }
            else {  // 같은 집합에 소속되어있는지 확인하기. (checkSameSet == 1 일때)
                if(findParent(a) == findParent(b)) {  // 만약 같은 집합 소속이 맞다면?
                    stb.append("YES\n");
                }
                else {
                    stb.append("NO\n");
                }
            }
        }

        System.out.print(stb.toString());
    }
}
