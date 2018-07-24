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
    // couter(arr, i+1) is computing the number of combinations of elements i
    // through n
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

    // [T]urn Around the Solution
    // Want to expand the subproblems and its base case to now be implemented in
    // an iterative approach instead of recursive.
    public static int counterIterativeDP(int [] nums) {
        int [] dp = new int [nums.length + 1];

        // Subproblems are the number of combinations from i to n.
        // Since the subproblem approach fills in the data from the back to the front
        // the subproblems can be inverted to state the number of combinations from
        // 0 to i.    
        for (int index = 0; index <= nums.length; index++) {
            /*
                Doubling of the previous step.
            */
            
            if (index == 0) {
                // empty set
                dp[index] = 1;
            }
            else {
                dp[index] = dp[index - 1] << 1;
            }
        }    

        return dp[nums.length];
    }

    // Hard to flip the subproblem definition.
    // Easy code but hard to understand.

    public static void main(String [] args) {
        assert (counterIterativeDP(new int [] {1}) == counterDP(new int [] {1}));
        assert (counterIterativeDP(new int [] {1,2}) == counterDP(new int [] {1,2}));
        assert (counterIterativeDP(new int [] {1,2,3}) == counterDP(new int [] {1,2,3}));
        assert (counterIterativeDP(new int [] {1,2,3,4}) == counterDP(new int [] {1,2,3,4}));
    }
}