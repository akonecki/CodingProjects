import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;

public class Solution {

    // (a, b) - reads a preceeds b in occurrence.
    public static List<String> topologicalSort(String [] nodes, String [][] dependencies) {
        List<String> result = new ArrayList<String>(nodes.length);
        HashMap<String, HashSet<String>> inboundMap = new HashMap<>();
        HashMap<String, HashSet<String>> outboundMap = new HashMap<>();
        Queue<String> toProcess = new LinkedList<String>();
        HashSet<String> nodesLeft = new HashSet<String>();

        // add all the nodes to a set that is left to process
        for (String node : nodes) {
            nodesLeft.add(node);
        }

        // work out the depency array
        for (String [] depency : dependencies) {
            HashSet<String> inset = inboundMap.get(depency[1]);
            HashSet<String> outset = outboundMap.get(depency[0]);

            if (inset == null) {
                inset = new HashSet<String>();
            }
            if (outset == null) {
                outset = new HashSet<String>();
            }

            inset.add(depency[0]);
            inboundMap.put(depency[1], inset);
            outset.add(depency[1]);
            outboundMap.put(depency[0], outset);
        }

        // iterate through the list of nodes left and queue up the ones with 
        // zero / dont exist within the map.
        boolean flag = true;
        while (flag) {
            // flag represents to perform some level of work, needs to get reset 
            flag = false;

            // 1. look to see what nodes to perform work
            if (toProcess.isEmpty()) {
                // should really only happen the first time.
                for (String node : nodesLeft) {
                    if (!inboundMap.containsKey(node)) {
                        flag = true;
                        toProcess.add(node);
                        System.out.println("Added " + node);
                    }
                }
            }
            else {
                flag = true;

                // 2. remove node from the queue
                String node = toProcess.remove();

                // 3. remove the node's outbound / target inbound.
                if (outboundMap.get(node) != null) {
                    for (String outbound : outboundMap.get(node)) {
                        System.out.println("Outbound node " + outbound);

                        if (inboundMap.get(outbound) != null) {
                            System.out.println("Inbound for outbound node " + outbound + " size is " + inboundMap.get(outbound).size());
                            inboundMap.get(outbound).remove(node);
                            
                            if (inboundMap.get(outbound).size() == 0) {
                                toProcess.add(outbound);
                                inboundMap.remove(outbound);
                            }
                        }
                    }
                }

                // 4. add the node to the result
                result.add(node);

                // 5. remove from the list of nodesLeft
                nodesLeft.remove(node);
            }
        }

        System.out.println(result);

        if (nodesLeft.isEmpty()) {
            return result;
        }
        else {
            return new ArrayList<String>();
        }
    }

    public static void main(String [] args) {
        assert (!topologicalSort(new String [] {"a", "b", "c", "d", "e", "f"}, new String [][] {
            {"f", "e"},
            {"e", "d"},
            {"d", "c"},
            {"c", "b"},
            {"b", "a"}
        }).isEmpty());

        assert (topologicalSort(new String [] {"a", "b", "c", "d", "e", "f"}, new String [][] {
            {"f", "e"},
            {"e", "d"},
            {"d", "c"},
            {"c", "b"},
            {"b", "a"},
            {"a", "f"}
        }).isEmpty());

        assert (topologicalSort(new String [] {"a", "b", "c", "d", "e", "f"}, new String [][] {
            {"f", "e"},
            {"e", "d"},
            {"d", "c"},
            {"c", "b"},
            {"b", "a"},
            {"a", "e"}
        }).isEmpty());

        assert (topologicalSort(new String [] {"a", "b", "c", "d", "e", "f"}, new String [][] {
            {"f", "e"},
            {"e", "d"},
            {"d", "c"},
            {"c", "b"},
            {"b", "a"},
            {"a", "d"}
        }).isEmpty());

        assert (topologicalSort(new String [] {"a", "b", "c", "d", "e", "f"}, new String [][] {
            {"f", "e"},
            {"e", "d"},
            {"d", "c"},
            {"c", "b"},
            {"b", "a"},
            {"a", "c"}
        }).isEmpty());

        assert (topologicalSort(new String [] {"a", "b", "c", "d", "e", "f"}, new String [][] {
            {"f", "e"},
            {"e", "d"},
            {"d", "c"},
            {"c", "b"},
            {"b", "a"},
            {"a", "b"}
        }).isEmpty());

        assert (topologicalSort(new String [] {"a", "b", "c", "d", "e", "f"}, new String [][] {
            {"f", "e"},
            {"e", "d"},
            {"d", "c"},
            {"c", "b"},
            {"b", "a"},
            {"a", "a"}
        }).isEmpty());

        assert (!topologicalSort(new String [] {"a", "b", "c", "d", "e", "f"}, new String [][] {
            {"f", "e"},
            {"f", "d"},
            {"e", "c"},
            {"c", "b"},
            {"b", "a"}
        }).isEmpty());
    }

}