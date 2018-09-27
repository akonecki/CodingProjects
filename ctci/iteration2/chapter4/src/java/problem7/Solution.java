import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;
import java.util.HashSet;

public class Solution {

    public static List<String> buildOrder(List<String> projects, List<List<String>> yDependsOnX) {
        HashMap<String, List<String>> buildDependsOn = new HashMap<String, List<String>>();
        List<String> result = new ArrayList<String>(projects.size());
        HashSet<String> added = new HashSet<>();

        // key project : those that the project depends upon
        for (List<String> dependency : yDependsOnX) {
            String x = dependency.get(0);
            String y = dependency.get(1);

            List<String> dependencies = buildDependsOn.get(y);

            if (dependencies == null) {
                dependencies = new ArrayList<String>();
            }

            dependencies.add(x);
            buildDependsOn.put(y, dependencies);
        }

        for (String project : projects) {
            if (!build(project, buildDependsOn, result, added, new HashSet<String>())) {
                result.clear();
                return result;
            }  
        }

        return result;
    }

    // Worse case recursion will be O(N).  This is a DFS implementation that
    // peels off one layer at a time.
    private static boolean build(
        String project, HashMap<String, List<String>> buildDependsOn,
        List<String> result, HashSet<String> added,
        HashSet<String> currentSet) 
    {
        System.out.println(project);
        if (currentSet.contains(project)) {
            return false;
        }

        if (added.contains(project)) {
            return true;
        }
        else if (!buildDependsOn.containsKey(project)) {
            System.out.println("No depencies " + project);
            result.add(project);
            added.add(project);
            return true;
        }
        else {
            System.out.println("Depencies " + project);
            List<String> dependencies = buildDependsOn.get(project);

            currentSet.add(project);

            for (String dependency : dependencies) {
                if (!build(dependency, buildDependsOn, result, added, currentSet)) {
                    return false;
                }
            }

            result.add(project);
            added.add(project);
            currentSet.remove(project);

            return true;
        }
    }

    public static void main(String [] args) {
        List<String> projects = new ArrayList<String>();
        List<List<String>> dependencies = new ArrayList<>();
        projects.add("a");
        projects.add("b");
        projects.add("c");
        projects.add("d");
        projects.add("e");
        projects.add("f");
        
        String [][] dependsOn = {
            {"a", "b"},
            {"a", "f"},
            {"b", "c"},
            {"b", "e"},
            {"c", "d"},
            {"c", "f"},
            {"d", "e"},
            {"d", "f"},
            {"e", "f"}
        };

        for (String [] strs : dependsOn) {
            List<String> strings = new ArrayList<String>(2);
            strings.add(strs[0]);
            strings.add(strs[1]);
            dependencies.add(strings);
        }

        for (String str : buildOrder(projects, dependencies)) {
            System.out.print(str + " ");
        }        

        System.out.println("");
    }
}