import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;

public class Solution {
    
    // Could implement a bi-directional search from both nodes as long  both 
    // nodes exist within the matrix.  However since it is directed would 
    // actually need to traverse the entire path from node1 and node2 to each
    // other since just meeting in the middle along the shortest route is not
    // sufficient.
    public static boolean routeExists(int [][] matrix, int node1, int node2) {
        if (node1 < 0 || node2 < 0 || 
            node1 >= matrix.length || 
            node2 >= matrix.length) 
        {
            return false;
        }

        // Since it is directed, it could be the case that node1 can go to node2
        // but node2 can not go to node1.  Would need to discuss if route is 
        // purely one way or can exist from node2 to node1 as well in the 
        // definition of the problem.

        // lets assume it is directed only from node1 to node2.
        HashSet<Integer> visited = new HashSet<Integer>();
        Queue<Integer> toVisit = new LinkedList<Integer>();
        int node = node1;

        
            int col = 0;
            for (int num : matrix[node]) {
                if (num == 1) {
                    // Direct path
                    if (col == node2) {
                        return true;
                    }
                    else {
                        toVisit.add(col);
                    }
                }
                col++;
            }
        

        // BFS search
        while (!toVisit.isEmpty()) {
            // remove the front
            node = toVisit.remove().intValue();

            if (node == node2) {
                return true;
            }
            else if (!visited.contains(node)) {
                visited.add(node);

                col = 0;
                for (int num : matrix[node]) {
                    if (num == 1 && !visited.contains(col)) {
                        toVisit.add(col);
                    }
                    col++;
                }
            }
        }

        return false;
    }

    public static void main(String [] args) {
        int [][] routes = {
            {0, 0, 1, 0}, // 0
            {1, 1, 0, 0}, // 1
            {1, 1, 0, 1}, // 2
            {0, 0, 1, 0} // 3
        };

        assert (routeExists(routes, 0, 0));
        assert (routeExists(routes, 0, 1));
        assert (routeExists(routes, 0, 2));
        assert (routeExists(routes, 0, 3));
        assert (routeExists(routes, 1, 0));
        assert (routeExists(routes, 1, 1));
        assert (routeExists(routes, 1, 2));
        assert (routeExists(routes, 1, 3));
        assert (routeExists(routes, 2, 0));
        assert (routeExists(routes, 2, 1));
        assert (routeExists(routes, 2, 2));
        assert (routeExists(routes, 2, 3));
        assert (routeExists(routes, 3, 0));
        assert (routeExists(routes, 3, 1));
        assert (routeExists(routes, 3, 2));
        assert (routeExists(routes, 3, 3));
    }
}