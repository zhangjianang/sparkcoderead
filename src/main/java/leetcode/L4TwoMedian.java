package leetcode;

/**
 * Created by adimn on 2019/6/4.
 */
public class L4TwoMedian {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int all = nums1.length+nums2.length;
        if( all%2 == 0){
            return (getNum(nums1,nums2,all/2)+getNum(nums1,nums2,all /2+1)+0.0)/2;
        }else{
            return getNum(nums1,nums2,all /2+1)+0.0;
        }
    }

    private int getNum(int[] nums1,int[] nums2,int index){
        int x1= 0;
        int x2= 0;
        int res =  0;
        for(int i = 0; i < index; i++){
            if(nums1.length>0 && nums2.length>0 && x1 < nums1.length && x2<nums2.length) {
                if ( nums1[x1] <= nums2[x2]) {
                    res = nums1[x1];
                    x1++;
                } else {
                    res = nums2[x2];
                    x2++;
                }
            }else if(nums1.length ==0 || x1 == nums1.length){
                res = nums2[x2];
                x2++;
            }else if(nums2.length ==0 || x2==nums2.length){
                res = nums1[x1];
                x1++;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] nums1 = {1};
        int[] nums2 = {};
        L4TwoMedian tm = new L4TwoMedian();
        System.out.println(tm.findMedianSortedArrays(nums1, nums2));
    }
}
