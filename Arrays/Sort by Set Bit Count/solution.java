class Solution {
    public ArrayList<Integer> sortBySetBitCount(int[] arr) {
        Integer[] boxed = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            boxed[i] = arr[i];
        }
        
        Arrays.sort(boxed, (a, b) -> {
            int bitA = Integer.bitCount(a);
            int bitB = Integer.bitCount(b);
            return Integer.compare(bitB, bitA);
        });
        
        ArrayList<Integer> result = new ArrayList<>();
        for (int num : boxed) {
            result.add(num);
        }
        return result;
    }
}