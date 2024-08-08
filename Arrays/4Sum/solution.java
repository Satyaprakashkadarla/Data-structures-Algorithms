public class Four_Sum {

    public static void main(String[] args) {
        Four_Sum out = new Four_Sum();
        Solution s = out.new Solution();
//		SolutionForLoop s= out.new SolutionForLoop();

        List<List<Integer>> result = s.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);

        for (List<Integer> each : result) {
            String one = "";

            for (int e : each) {
                one = one + " " + e;
            }

            System.out.println(one);
        }
    }