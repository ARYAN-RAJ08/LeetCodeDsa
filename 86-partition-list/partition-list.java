class Solution {
    public ListNode partition(ListNode head, int x) {
        
        ListNode beforeHead = new ListNode(0);
        ListNode afterHead = new ListNode(0);

        ListNode before = beforeHead;
        ListNode after = afterHead;

        ListNode curr = head;

        while (curr != null) {
            if (curr.val < x) {
                before.next = curr;
                before = before.next;
            } else {
                after.next = curr;
                after = after.next;
            }
            curr = curr.next;
        }
        after.next = null;

        before.next = afterHead.next;

        return beforeHead.next;
    }
}