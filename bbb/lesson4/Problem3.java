import java.util.*;

public class Problem3 {

    private static class Item {
        private String itemName;
        private int itemValue;
        private int itemWeight;
        private int itemQuantity;

        public Item(String name, int value, int weight, int quantity) {
            this.itemName = name;
            this.itemValue = value;
            this.itemWeight = weight;
            this.itemQuantity = quantity;
        }

        public String toString() {
            return this.itemName;
        }
    }

    public static List<List<Item>> knapsack(Item [] items, int maxWeight) {
        List<List<Item>> result = new ArrayList<List<Item>>();

        knapsack(items, 0, 0, 0, new ArrayList<Integer>(), 0, maxWeight, new ArrayList<Item>(), result);
        return result;
    }

    private static int knapsack(
        Item [] items, 
        int itemIndex, 
        int currentWeight, 
        int currentValue,
        List<Integer> maxWeight, 
        int maxValue, 
        int abosulteMaxWeight,
        List<Item>itemList, 
        List<List<Item>> result) 
    {
        if (itemIndex >= items.length) {
            if (currentWeight > abosulteMaxWeight) {
                // over the weight limit return current maxValue.
                return maxValue;
            }
            else if (currentWeight > 0 && currentValue > 0 && maxWeight.isEmpty() || result.isEmpty()) {
                result.add(new ArrayList<Item>(itemList));
                maxWeight.add(currentWeight);
                return currentValue;
            }
            else if (currentValue > maxValue ||
                (currentValue == maxValue && currentWeight < maxWeight.get(0).intValue())) 
            {
                result.clear();
                result.add(new ArrayList<Item>(itemList));
                maxWeight.set(0, currentWeight);
                return currentValue;
            }
            else if (currentValue == maxValue && currentWeight == maxWeight.get(0).intValue()) {
                result.add(new ArrayList<Item>(itemList));
                return maxValue;
            }
            else {
                return maxValue;
            }
        }

        Item item = items[itemIndex];
        int value = maxValue;

        // perform the exclusion first
        value = knapsack(items, 
            itemIndex + 1, 
            currentWeight,
            currentValue,
            maxWeight,
            value,
            abosulteMaxWeight,
            itemList,
            result);

        // will need to iterate through the quantity.
        // inclusion of the item iterator
        int itemCount = 0;
        for (; itemCount < item.itemQuantity; itemCount++) {
            if (currentWeight + (itemCount + 1) * item.itemWeight <= abosulteMaxWeight) {
                itemList.add(item);
                value = knapsack(items, 
                    itemIndex + 1, 
                    currentWeight + (itemCount + 1) * item.itemWeight,
                    currentValue + (itemCount + 1) * item.itemValue,
                    maxWeight,
                    value,
                    abosulteMaxWeight,
                    itemList,
                    result);
            }
            else {
                break;
            }
        }

        // Remove all the items added to the list at this level.
        while (!itemList.isEmpty() && itemCount > 0) {
            itemList.remove(itemList.size() - 1);
            itemCount--;
        }

        return value;
    }

    public static void main(String [] args) {
        Item [] items = new Item [5];
        items[0] = new Item("flour", 1, 3, 1);
        items[1] = new Item("baking soda", 1, 1, 1);
        items[2] = new Item("seame seeds", 1, 1, 1);
        items[3] = new Item("corn stratch", 1, 1, 2);
        items[4] = new Item("brown sugar", 1, 2, 1);

        List<List<Item>> result = knapsack(items, 8);
        
        for (List<Item> itemList : result) {
            for (Item item : itemList) {
                System.out.print(item + ", ");
            }
            System.out.println("");
        }
        
        //assert (result.size() == 1);


    }
}