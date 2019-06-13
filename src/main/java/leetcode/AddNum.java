package leetcode;

/**
 * Created by adimn on 2019/5/28.
 */
public class AddNum {

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i] + nums[j]) == target) {
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        AddNum addNum = new AddNum();
        int[] input = {7, 11, 2, 15};
        int[] res = addNum.twoSum(input, 17);
        System.out.println(res[0] + "--" + res[1]);
    }


}

class MyListNode {
    String element;
    MyListNode nextNode;

    public MyListNode(String element) {
        this.element = element;
    }
}

class ListNode {
    private MyListNode firstNode = null;

    public void del(String element) {
        if (firstNode == null) {
            return;
        }

        if (element.equals(firstNode.element)) {
            if (firstNode.nextNode == null) {
                firstNode = null;
            } else {
                firstNode = firstNode.nextNode;
            }
            return;
        }
        MyListNode pre = firstNode;
        MyListNode cur = pre.nextNode;
        while (cur != null) {

            if (element.equals(cur.element)) {
                pre.nextNode = cur.nextNode;
                break;
            } else {
                pre = cur;
                cur = cur.nextNode;
            }
        }
    }
}
