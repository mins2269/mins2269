import java.util.Arrays;

public class Ex03 {
    public static void main(String[] args) {
        int n = 6;
        int[] times = new int[]{7, 10};
        System.out.println(solution(n,times));
    }
    public static long solution(int n, int[] times){
        Arrays.sort(times);

        long left = 1;
        long right = (long) times[times.length-1] * n + 1;

        while(left < right){
            long mid = left + (left - right)/2;
            if(canFinish(mid,n,times)){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }
    // time분 동안 n명 이상 처리 가능한가?
    private static boolean canFinish(long time, int n, int[] times) {
        long count = 0;
        for (int t : times) {
            count += time / t;
            if (count >= n) return true;   // 조기 종료 (오버플로 방지 겸 속도)
        }
        return false;
    }
}
