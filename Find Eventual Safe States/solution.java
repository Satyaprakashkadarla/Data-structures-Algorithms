class Solution {
    private static final int UNKNOWN = 0;
    private static final int VISIT = 1;
    private static final int SAFE = 2;
    private static final int UNSAFE = 3;
    private int[] state;
    private int len;
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> result = new ArrayList<>();
        if(graph == null || graph.length == 0) return result;
        len = graph.length;
        state = new int[len];
        for(int i = 0; i < len; i++){
            if(dfs(graph, i) == SAFE)
                result.add(i);
        }
        return result;
    }
    private int dfs(int[][] graph, int cur){
        if(state[cur] == VISIT){
            return state[cur] = UNSAFE;
        }else if(state[cur] != UNKNOWN){
            return state[cur];
        }
        state[cur] = VISIT;
        for(int i = 0; i < graph[cur].length; i++){
            if(dfs(graph, graph[cur][i]) == UNSAFE){
                return state[cur] = UNSAFE;
            }
        }
        return state[cur] = SAFE;
    }
}