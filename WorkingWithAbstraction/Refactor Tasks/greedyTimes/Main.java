package greedyTimes;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long bagCapacity = Long.parseLong(scanner.nextLine());
        String[] safe = scanner.nextLine().split("\\s+");

        Map<String, LinkedHashMap<String, Long>> bag = new LinkedHashMap<>();
        long gold = 0;
        long gems = 0;
        long cash = 0;
        long currentBagCapacity = 0;

        for (int i = 0; i < safe.length; i += 2) {
            String name = safe[i];
            long itemValue = Long.parseLong(safe[i + 1]);

            String item = "";

            if (name.length() == 3) {
                item = "Cash";
            } else if (name.toLowerCase().endsWith("gem")) {
                item = "Gem";
            } else if (name.toLowerCase().equals("gold")) {
                item = "Gold";
            }

            if (item.equals("")) {
                continue;
            } else if (bagCapacity < currentBagCapacity + itemValue) {
                continue;
            }

            switch (item) {
                case "Gem":
                    if (gems + itemValue > gold || !bag.containsKey("Gold") || itemValue > gold) {
                        continue;
                    }
//                    if (!bag.containsKey(item)) {
//                        if (bag.containsKey("Gold")) {
//                            if (itemValue > gold) {
//                                continue;
//                            }
//                        } else {
//                            continue;
//                        }
//                    } else if (gems + itemValue > gold) {
//                        continue;
//                    }
                    break;
                case "Cash":
                    if (cash + itemValue > gems || !bag.containsKey("Gem") || itemValue > gold) {
                        continue;
                    }
//                    if (!bag.containsKey(item)) {
//                        if (bag.containsKey("Gem")) {
//                            if (itemValue > gold) {
//                                continue;
//                            }
//                        } else {
//                            continue;
//                        }
//                    }
                    break;
            }

            if (!bag.containsKey(item)) {
                bag.put((item), new LinkedHashMap<>());
            }

            if (!bag.get(item).containsKey(name)) {
                bag.get(item).put(name, 0L);
            }
            currentBagCapacity += itemValue;


            bag.get(item).put(name, bag.get(item).get(name) + itemValue);
            if (item.equals("Gold")) {
                gold += itemValue;
            } else if (item.equals("Gem")) {
                gems += itemValue;
            } else {
                cash += itemValue;
            }
        }
        Map<String, Long> finalItemValues = new HashMap<>();
        finalItemValues.put("Gold", gold);
        finalItemValues.put("Cash", cash);
        finalItemValues.put("Gem", gems);

        for (Map.Entry<String, LinkedHashMap<String, Long>> item : bag.entrySet()) {
//            Long sumValues = item.getValue().values().stream().mapToLong(l -> l).sum();
            Long sumValues = finalItemValues.get(item.getKey());

            System.out.println(String.format("<%s> $%s", item.getKey(), sumValues));

            item.getValue().entrySet().stream().sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey())).forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));
        }
    }
}