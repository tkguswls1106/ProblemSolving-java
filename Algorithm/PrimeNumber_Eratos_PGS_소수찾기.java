import java.util.*;

// [ 에라토스테네스의 체 ]
// - 개념 특징 :
// 특정 범위내 소수 판별 알고리즘을 의미함.
// - 풀이 방식 :
// i=2부터 시작하여 범위내에서 i의 배수를 모두 제거해주고, i++을 해가며 남은것들에 반복해서 i의 배수를 제거함.
// 그렇게하면 최종적으로 남은 숫자범위내에는 소수만 남게 됨.

// - 추가 Tip :
/*
// 특정 숫자의 소수 확인 메소드
public static boolean isPrimeNumber(int num) {
    if(num < 2) return false;

    for(int i=2; i<=Math.sqrt(num); i++) {  // 또는 'i*i<=num' 사용. 하지만 sqrt 방식이 더 빠른 경우가 있음.
        // 참고로 위의 'i<=Math.sqrt(num)'로 인해 2,3 등의 범위는 무시처리됨.
        if(num%i == 0){
            return false;
        }
    }
    
    return true;
}
*/

class Solution {
    public int solution(int n) {
        // boolean[] 말고 Boolean[]으로 해야지, 차후 stream 활용이 효과적임. (다만, stream 활용 안할거면 boolean[]이 더 효율적임.)
        Boolean[] primeArr = new Boolean[n + 1];  // 인덱스로는 '0'~'n+1-1'
        Arrays.fill(primeArr, true);
        primeArr[0] = primeArr[1] = false;  // 어차피 0,1은 소수가 아님.
        
        for(int i=2; i*i<=n; i++) {
            if(primeArr[i] == true) {
                // - 과거 버전 -
                // for(int j=2; i*j<=n; j++) {
                //     primeArr[i*j] = false;
                // }
                // - 최적화 버전 -
                for(int j=i*i; j<=n; j+=i) {
                    primeArr[j] = false;
                }
            }
        }
        
        int primeCnt = (int) Arrays.stream(primeArr)
                                // .skip(1)  // skip(n)은 처음 n개의 요소를 건너뛰는 메소드임.
                                .filter(isPrime -> isPrime==true)  // 만약 소수가 아닌수라면 false로 조회할것.
                                .count();  // 인덱스 0을 제외한, 1부터 끝까지 카운트.
        return primeCnt;
    }
}

public class PrimeNumber_Eratos_PGS_소수찾기 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int answer1 = solution.solution(10);
        int answer2 = solution.solution(5);

        System.out.println(answer1);  // => 4 출력
        System.out.println(answer2);  // => 3 출력
    }
}
