import java.util.*;

class Solution {
    public static class Car {
        public int inTime;
        public int sumTime;
        public boolean isOut;
        
        public Car(String inTimeStr) {  // 첫 IN 경우
            this.inTime = getTimeInt(inTimeStr);
            this.sumTime = 0;
            this.isOut = false;
        }
        
        public void in(String inTimeStr) {  // 이후 IN 경우
            this.inTime = getTimeInt(inTimeStr);
            this.isOut = false;
        }
        
        public void out(String outTimeStr) {  // OUT 경우
            int outTime = getTimeInt(outTimeStr);
            this.sumTime += (outTime - this.inTime);
            this.isOut = true;
        }
    }
    
    public static int getTimeInt(String timeStr) {
        StringTokenizer stt = new StringTokenizer(timeStr, ":");
        int h = Integer.parseInt(stt.nextToken());
        int m = Integer.parseInt(stt.nextToken());
        return (h*60 + m);
    }
    
    public int[] solution(int[] fees, String[] records) {
        Map<String, Car> m = new TreeMap<>();  // key가 문자열이므로, 추가적인 Car 오버라이딩 메소드 필요 X.
        
        // IN & OUT 기록 관리
        for(String record : records) {
            StringTokenizer stt = new StringTokenizer(record);
            String timeStr = stt.nextToken();
            String carInfo = stt.nextToken();
            if(stt.nextToken().equals("IN")) {
                if(m.containsKey(carInfo) == false) {  // 첫 IN 경우
                    m.put(carInfo, new Car(timeStr));
                }
                else {  // 이후 IN 경우
                    m.get(carInfo).in(timeStr);
                }
            }
            else {  // OUT 경우
                m.get(carInfo).out(timeStr);
            }
        }
        
        // 요금 정산
        int[] answer = new int[m.size()];
        int idx = 0;
        for(Car car : m.values()) {  // 차량번호는 불필요하므로 map.values() 활용.
            if(car.isOut == false) {  // 최종까지도 출차되지 않았다면
                car.out("23:59");
            }
            
            int price = fees[1];
            int sumTime = car.sumTime;
            if(sumTime <= fees[0]) {
                answer[idx++] = price;
                continue;
            }
            
            sumTime -= fees[0];  // 기본시간 차감
            if(sumTime % fees[2] != 0) {  // 시간 올림 처리
                sumTime = (sumTime / fees[2] + 1) * fees[2];
            }
            price += (sumTime / fees[2]) * fees[3];
            answer[idx++] = price;
        }
        
        return answer;
    }
}

public class PGS_주차요금계산 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
