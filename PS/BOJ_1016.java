import java.util.*;

// - 알고리즘: 에라토스테네스의 체

public class BOJ_1016 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long minNum = sc.nextLong();
        long maxNum = sc.nextLong();
        int len = (int) (maxNum - minNum) + 1;

        // '에라토스테네스의 체'를 응용.
        Boolean[] isAnswerArr = new Boolean[len];  // isAnswerArr[idx] = 'minNum + idx' ~ 'maxNum'
        Arrays.fill(isAnswerArr, true);
        for(long i=2; i*i<=maxNum; i++) {
            long powNum = i * i;

            // 제곱수의 '곱' 값으로 시작할 j의 최소값 구하기 (j를 매번 1로 시작하면 비효율적이므로)
            long startJ;
            if(minNum%powNum == 0) startJ = minNum / powNum;
            else startJ = minNum / powNum + 1;
            
            for(long j=startJ; powNum*j<=maxNum; j++) {
                int idx = (int) (powNum*j - minNum);
                isAnswerArr[idx] = false;
            }
        }
        
        int answerCnt = (int) Arrays.stream(isAnswerArr)
                                .filter(isAnswer -> isAnswer==true)
                                .count();
        System.out.print(answerCnt);
    }
}
