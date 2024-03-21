class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int time1 = timeToSecond(h1, m1, s1);
        int time2 = timeToSecond(h2, m2, s2);
        
        return countAlarm(time2) - countAlarm(time1) + (isAlarmNow(time1) ? 1 : 0);
    }
    
    private int timeToSecond(int h, int m, int s) {
        return (h * 60 * 60) + (m * 60) + s;
    }
    
    private int countAlarm(int time) {
        // 시침이 한 바퀴 도는 데에는 12 * 60 * 60 초가 걸림
        // 분침이 한 바퀴 도는 데에는 60 * 60 초가 걸림
        // 초침이 한 바퀴 도는 데에는 60 초가 걸림
        
        // 시침-초침은 얼마만에 한 번 만나냐 : 
        // 시침이 한 바퀴 도는 데에 걸리는 시간 / 시침이 한 바퀴 돌 동안 초침이 시침을 만나고 가는 횟수
        // (12 * 60 * 60) / (12 * 60 - 1)
        // int hourAlarmCount = time / ((12 * 60 * 60) / (12 * 60 - 1));
        int hourAlarmCount = time * (12 * 60 - 1) / (12 * 60 * 60);
        // 분침-초침은 얼마만에 한 번 만나냐 : 
        // 분침이 한 바퀴 도는 데에 걸리는 시간 / 분침이 한 바퀴 돌 동안 초침이 분침을 만나고 가는 횟수
        // (60 * 60) / (60 - 1)
        int minuteAlarmCount = time * (60 - 1) / (60 * 60);
        
        // 시-분-초침이 모두 만나는 시간은 count 한 번씩 빼줘야 함
        // 0시0분0초, 12시0분0초 
        return hourAlarmCount + minuteAlarmCount - ((time >= 12 * 60 * 60) ? 2 : 1);
    }
    
    private boolean isAlarmNow(int time) {
        // 지금 시-초침이 만났거나 분-초침이 만나면 알람 울려야함
        return time * (12 * 60 - 1) % (12 * 60 * 60) == 0
            || time * (60 - 1) % (60 * 60) == 0;
    }
}