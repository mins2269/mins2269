package part05;

public class Ex01 {
    static void main() {
        int[] nums ={-1,0,3,5,9,12};
        int target = 2;
        System.out.println(binarySearch(nums,target));
    }
    static int binarySearch(int[] nums, int target){
        int left = 0;
        int right = nums.length-1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] < target){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return -1;
    }
}
