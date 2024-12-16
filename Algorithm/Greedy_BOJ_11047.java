import java.util.*;

// [ 그리디(Greedy) 동전 문제 ]
// - 이 문제가 Greedy 적용 가능한 이유 :
// 문제의 조건을 보면, '오름차순 1 ≤ Ai ≤ 1,000,000'에서 'A1 = 1, i ≥ 2'인 경우에 'Ai는 Ai-1의 배수'라고 나와있다.
// 이러한 경우에는 무조건 Ai를 안쓰고 Ai-1,Ai-2... 들을 이용해 Ai를 쓴 경우보다 적게 만들 수 없다.
// 예시로, 동전의 종류가 '500,100,50,10원'인 문제라면, '500=100*5, 100=50*2, 50=10*5'로 위의 조건에 부합하기에, 그리디를 적용해도 최적해를 보장받을 수 있다.
// ==> 항상 큰동전이 그보다 한단계 작은 동전의 배수이므로, 그리디를 적용하여 큰 동전을 우선적으로 최대한 많이씩 선택하면 된다.
// - 반례 예시:
// 반례의 예시로는, 어떠한 문제에서 동전의 종류가 '500,400,100원' 이라면, 500은 400의 배수가 아니기에 그리디 적용은 적절치 못하다.
// 예를들어 총 800원이 나와야할때, 여기에 그리디 알고리즘을 적용하면 '500*1 + 100*3'으로 동전의 최소 개수가 4개로 결과가 나오지만,
// 실은 답이 4개가 아닌 '400*2'로써 2개로 최솟값 답을 구할수 있기에, 틀린것을 알 수 있다.

public class Greedy_BOJ_11047 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // N이 최대 10개밖에 안되므로, BufferedReader 사용하지 않아도 괜찮음.
        int n = sc.nextInt(), k = sc.nextInt();

        List<Integer> list = new ArrayList<>();
        while(n-- > 0) {
            list.add(sc.nextInt());
        }
        Collections.reverse(list);

        int coinCnt = 0;
        for(int coin : list) {
            coinCnt += (k / coin);
            k %= coin;
            if(k == 0) break;
        }

        System.out.print(coinCnt);
    }
}
