public class Challenge10 { 

    public static int counter(int [] nums) {
        return recursiveCount(nums, 0);
    }

    // [A]nalysis of the recursive method
    // 1 - Is the recursive solution suboptimal?
    // Yes it is recursive by nature and only concerned about current step for
    // calculating the total count.
    //
    // 2 - does it contain re-occurring values?
    /*
        nums[1,2,3,4], 0
            nums[1,2,3,4], 1
                nums[1,2,3,4], 2
                    nums[1,2,3,4], 3
                        nums[1,2,3,4], 4
                            return 1
                        nums[1,2,3,4], 4
                            return 1
                        return 2
                    nums[1,2,3,4], 3
                        nums[1,2,3,4], 4
                            return 1
                        nums[1,2,3,4], 4
                            return 1
                        return 2
                    return 4
                nums[1,2,3,4], 2
                    nums[1,2,3,4], 3
                        nums[1,2,3,4], 4
                            return 1
                        nums[1,2,3,4], 4
                            return 1
                        return 2
                    nums[1,2,3,4], 3
                        nums[1,2,3,4], 4
                            return 1
                        nums[1,2,3,4], 4
                            return 1
                        return 2
                    return 4
                return 8 
            nums[1,2,3,4], 1
                nums[1,2,3,4], 2
                    nums[1,2,3,4], 3
                        nums[1,2,3,4], 4
                            return 1
                        nums[1,2,3,4], 4
                            return 1
                        return 2
                    nums[1,2,3,4], 3
                        nums[1,2,3,4], 4
                            return 1
                        nums[1,2,3,4], 4
                            return 1
                        return 2
                    return 4
                nums[1,2,3,4], 2
                    nums[1,2,3,4], 3
                        nums[1,2,3,4], 4
                            return 1
                        nums[1,2,3,4], 4
                            return 1
                        return 2
                    nums[1,2,3,4], 3
                        nums[1,2,3,4], 4
                            return 1
                        nums[1,2,3,4], 4
                            return 1
                        return 2
                    return 4
                return 8 
            return 16
    */
    // Yes - as from above I can see that there are multiple subproblems being
    // re-computed multiple times
    //
    // TIME & SPACE COMPLEXITY
    // Height of the tree = 4, which is N in this case
    // Branching factor is set to (2) since 2 branches are taken at each level
    // So total run time complexity is (2^4) or roughly (2^N) so exponential.
    //
    // The space complexity is that of the depth of the tree which is linear to
    // the length of the array of (4) in this case and generally O(N).
    private static int recursiveCount(int [] nums, int index) {
        if (index == nums.length) {
            return 1;
        }
        else {
            int included = recursiveCount(nums, index + 1);
            int excluded = recursiveCount(nums, index + 1);

            return included + excluded;
        }
    }

    // [S]ubproblem Identification & memoization
    // Build out a wrapper first

    public static int counterDP(int [] nums) {
        int [] dp = new int [nums.length + 1];
        return counter(nums, dp, 0);
    }

    private static int counter(int [] nums, int [] results, int index) {
        if (results[index] == 0) {
            if (index == nums.length) return 1;
            int included = counter(nums, results, index + 1);
            int excluded = counter(nums, results, index + 1);
            results[index] = included + excluded;
        }
        
        return results[index];
    }

    public static void main(String [] args) {
        assert (counter(new int [] {1}) == counterDP(new int [] {1}));
        assert (counter(new int [] {1,2}) == counterDP(new int [] {1,2}));
        assert (counter(new int [] {1,2,3}) == counterDP(new int [] {1,2,3}));
        assert (counter(new int [] {1,2,3,4}) == counterDP(new int [] {1,2,3,4}));
    }
}