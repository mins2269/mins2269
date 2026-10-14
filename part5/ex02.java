import java.util.Arrays;

public class Ex02 {
    public static void main(String[] args) {
        int[] nums = {7,7,7,7,8,8,10};
        int k = 7;
        int[] answer = searchRange(nums,k);
        System.out.print(Arrays.toString(answer));
    }
    public static int[] searchRange(int[] nums, int target) {
        int[] output = {-1,-1};
        int left = 0;
        int right = nums.length - 1;
        // find first
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target){
                output[0] = mid;
                right = mid - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }
            else if(nums[mid] > target){
                right = mid - 1;
            }
        }
        // find last
        left = 0;
        right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target){
                output[1] = mid;
                left = mid + 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }
            else if(nums[mid] > target){
                right = mid - 1;
            }
        }
        return output;
    }
    public static int[] searchRange2(int[] nums, int target) {
        int[] output = {-1, -1};

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                output[0] = mid;
                right = mid - 1;      // 더 왼쪽 탐색
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                output[1] = mid;
                left = mid + 1;       // 더 오른쪽 탐색
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return output;
    }
    public static int[] searchRange3(int[] nums, int target) {
        int[] output = {-1, -1};
        int result = -1;
        int left = 0;
        int right = nums.length;
        // 첫 요소
        while (left < right) {
            int mid = left + (right - left)/2;
            if (nums[mid] >= target){
                // mid가 target일 수 있으니 일단 저장하고 봄
                result = mid;
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        if(result == -1 || nums[result] != target){
            return output;
        }
        output[0] = result;
        int result2 = -1;
        left = 0;
        right = nums.length ;
        // 마지막 요소
        while (left < right) {
            int mid = left + (right - left)/2;
            if (nums[mid] <= target){
                result2 = mid;
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        output[1] = result2;
        return output;
    }
}
