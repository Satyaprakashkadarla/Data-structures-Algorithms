class Solution {
    public int sumOfModes(int[] arr, int k) {
        
        int n = arr.length;
        int sum = 0;

        Map<Integer, Integer> mp = new HashMap<>();

        TreeSet<int[]> st = new TreeSet<>(
            (a, b) -> (a[0] != b[0]) ? Integer.compare(a[0], b[0]) 
                                     : Integer.compare(a[1], b[1])
        );


        for (int i = 0; i < k; i++) {
            mp.put(arr[i], mp.getOrDefault(arr[i], 0) + 1);
        }

        for (Map.Entry<Integer, Integer> x : mp.entrySet()) {
            st.add(new int[]{x.getValue(), -x.getKey()});
        }

       
        int mode = -st.last()[1];
        sum += mode;

        
        for (int i = k; i < n; i++) {
            int out = arr[i - k];
            int in = arr[i];
            
            int outFreq = mp.get(out);
            st.remove(new int[]{outFreq, -out});

            mp.put(out, outFreq - 1);

            if (mp.get(out) > 0) st.add(new int[]{mp.get(out), -out});
            else mp.remove(out);

            mp.put(in, mp.getOrDefault(in, 0) + 1);

            st.add(new int[]{mp.get(in), -in});

            mode = -st.last()[1];
            sum += mode;
        }

        return sum;
    
    }
}

