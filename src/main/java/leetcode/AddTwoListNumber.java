package leetcode;

/**
 * Created by adimn on 2019/6/4.
 */
public class AddTwoListNumber {

    public ListNode2 addTwoNumbers(ListNode2 l1, ListNode2 l2) {
        ListNode2 res = new ListNode2(0);
        ListNode2 x = l1,y = l2 ,curr = res;
        int carry = 0;
        while(x != null || y != null){
            int first = (x == null) ? 0: x.val;
            int second = (y == null) ? 0: y.val;
            int sum = first + second + carry;
            curr.next = new ListNode2(sum %10);
            carry = sum /10;
            if(x != null) x = x.next;
            if(y != null) y = y.next;
            curr = curr.next;
        }
        if(carry >0){
            curr.next = new ListNode2(carry);
        }
        return res.next;
    }

    public ListNode2 addtwo(ListNode2 l1,ListNode2 l2){
        ListNode2 dummyHead = new ListNode2(0);
        ListNode2 p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode2(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode2(carry);
        }
        return dummyHead.next;
    }



    public static void main(String[] args) {
        AddTwoListNumber ad = new AddTwoListNumber();
        ListNode2 l11 = new ListNode2(2);
        ListNode2 l12 = new ListNode2(4);
        ListNode2 l13 = new ListNode2(5);
        l11.next = l12;
        l12.next = l13;

        ListNode2 l21 = new ListNode2(5);
        ListNode2 l22 = new ListNode2(6);
        ListNode2 l23 = new ListNode2(4);
        ListNode2 l24 = new ListNode2(9);
        l21.next = l22;
        l22.next = l23;
        l23.next = l24;

        ListNode2 res = ad.addTwoNumbers(l11, l21);

        System.out.println(res.val+ " - " + res.next.val+ " - " + res.next.next.val+" - "+res.next.next.next.val+"-"+res.next.next.next.next.val);

        ListNode2 r1 = new ListNode2(5);
        ListNode2 r2 = new ListNode2(5);
        ListNode2 res2 = ad.addTwoNumbers(r1, r2);
        System.out.println(res2.val+"-"+res2.next.val);
    }
}

class ListNode2 {
     int val;
     ListNode2 next;
     ListNode2(int x) { val = x; }
}
