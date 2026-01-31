class kQueues {
    int[] arr;
    int[] front;
    int[] rear;
    int[] next;
    int free;
    int n, k;

    kQueues(int n, int k) {
        this.n = n;
        this.k = k;
        arr = new int[n];
        front = new int[k];
        rear = new int[k];
        next = new int[n];
        
        // Initialize all queues as empty
        for (int i = 0; i < k; i++) {
            front[i] = -1;
            rear[i] = -1;
        }
        
        // Initialize all slots as free
        free = 0;
        for (int i = 0; i < n - 1; i++) {
            next[i] = i + 1;
        }
        next[n - 1] = -1; // last slot
    }

    void enqueue(int x, int i) {
        if (isFull()) return;
        
        int idx = free; // get free slot
        free = next[idx]; // update free list
        
        if (isEmpty(i)) {
            front[i] = idx;
        } else {
            next[rear[i]] = idx; // link previous rear to new
        }
        
        next[idx] = -1; // end of queue
        rear[i] = idx;
        arr[idx] = x;
    }

    int dequeue(int i) {
        if (isEmpty(i)) return -1;
        
        int idx = front[i]; // store front index
        front[i] = next[idx]; // move front
        
        // If queue becomes empty
        if (front[i] == -1) {
            rear[i] = -1;
        }
        
        // Add idx to free list
        next[idx] = free;
        free = idx;
        
        return arr[idx];
    }

    boolean isEmpty(int i) {
        return front[i] == -1;
    }

    boolean isFull() {
        return free == -1;
    }
}