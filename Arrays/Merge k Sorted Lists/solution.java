class Solution_Heap {
	public ListNode mergeKLists(ListNode[] lists) {

		if (lists == null || lists.length == 0) {
			return null;
		}

		ListNode dummy = new ListNode(0);
		ListNode current = dummy;

		// put 1st of each list to heap
		PriorityQueue<ListNode> heap = new PriorityQueue<>(
			(a,b) -> a.val - b.val
		);

		//
		Arrays.stream(lists).filter(Objects::nonNull).forEach(heap::offer);

		while (heap.size() != 0) {
			ListNode polled = heap.poll();

			current.next = polled;
			current = current.next;

			if (polled.next != null) {
				heap.offer(polled.next); // @note: heap.offer()参数不能是null
			}
		}

		return dummy.next;
	}
}
