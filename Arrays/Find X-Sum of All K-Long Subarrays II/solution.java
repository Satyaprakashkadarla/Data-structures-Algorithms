class Solution {
    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap();
        long[] res = new long[n-k+1];
        TreeSet<Cell> xset = new TreeSet<Cell>((a,b)->a.f==b.f ? (int)(b.v-a.v) : (int)(b.f-a.f));
        TreeSet<Cell> rem = new TreeSet<Cell>((a,b)->a.f==b.f ? (int)(b.v-a.v) : (int)(b.f-a.f));
        long xsum = 0;
        for(int i=0;i<k;i++){
            if(map.containsKey(nums[i])){
                Cell temp = new Cell(nums[i], map.get(nums[i]));
                map.put(nums[i], map.get(nums[i]) + 1);
                if(xset.contains(temp)){
                    xset.remove(temp);
                    temp.f++;
                    xset.add(temp);
                    xsum += temp.v;
                }else{
                    rem.remove(temp);
                    temp.f++;
                    xset.add(temp);
                    xsum += temp.v * temp.f;
                    temp = xset.pollLast();
                    xsum -= temp.v * temp.f;
                    rem.add(temp);
                }
            }else{
                Cell temp = new Cell(nums[i], 1);
                map.put(nums[i], 1);
                xset.add(temp);
                xsum += nums[i];
                if(xset.size() > x){
                    temp = xset.pollLast();
                    xsum -= temp.v;
                    rem.add(temp);
                }
            }
        }

        for(int i=0; i<n-k;i++){
            res[i] = xsum;
            // System.out.println(xset);
            if(nums[i]==nums[k+i])
                continue;

            // remove nums[i]
            Cell temp = new Cell(nums[i], map.get(nums[i]));
            map.put(nums[i], map.get(nums[i]) - 1);
            if(xset.contains(temp)){
                xset.remove(temp);
                temp.f--;
                xset.add(temp);
                xsum -= temp.v;
                if(!rem.isEmpty()){
                    temp = rem.pollFirst();
                    xset.add(temp);
                    xsum += temp.v * temp.f;
                    temp = xset.pollLast();
                    xsum -= temp.v*temp.f;
                    rem.add(temp);
                }
            }else{
                rem.remove(temp);
                temp.f--;
                rem.add(temp);
            }
            // System.out.println(xsum+"---"+xset);
            //insert nums[k+i]
            if(map.containsKey(nums[k+i])){
                temp = new Cell(nums[k+i], map.get(nums[k+i]));
                map.put(nums[k+i], map.get(nums[k+i]) + 1);
                if(xset.contains(temp)){
                    xset.remove(temp);
                    temp.f++;
                    xset.add(temp);
                    xsum += temp.v;
                }else{
                    rem.remove(temp);
                    temp.f++;
                    xset.add(temp);
                    xsum += temp.v * temp.f;
                    temp = xset.pollLast();
                    xsum -= temp.v * temp.f;
                    rem.add(temp);
                }
            }else{
                temp = new Cell(nums[k+i], 1);
                map.put(nums[k+i], 1);
                xset.add(temp);
                xsum += nums[k+i];
                if(xset.size() > x){
                    temp = xset.pollLast();
                    xsum -= temp.v * temp.f;
                    rem.add(temp);
                }
            }
            
        }
        // System.out.println(xset);
        res[n-k] = xsum;
        
        return res;
    }
}

class Cell{
    long v, f;
    Cell(long v, long f){
        this.v = v;
        this.f = f;
    }
    public String toString(){
        return "["+v+","+f+"]";
    }
}