package militaryElite;

import militaryElite.repositories.SoldierRepository;
import militaryElite.baseClasses.Soldier;
import militaryElite.soldiers.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        String input;
        List<Soldier> soldiers = new ArrayList<>();

        while (!"End".equals(input = bfr.readLine())) {
            String[] extractedData;
            Soldier soldier = null;
            String corps;
            int startIndex;
            double salary = -1;

            try {
                String[] lineData = input.split("\\s+");
                String soldierType = lineData[0];
                int id = Integer.parseInt(lineData[1]);
                String firstName = lineData[2];
                String lastName = lineData[3];

                try {
                    salary = Double.parseDouble(lineData[4]);
                } catch (Exception ignored) {
                }

                switch (soldierType) {
                    case "Private":
                        soldier = new PrivateImpl(id, firstName, lastName, salary);
                        break;
                    case "LeutenantGeneral":
                        startIndex = 4;
                        extractedData = extractDataFromLine(lineData, startIndex);
                        soldier = new LeutenantGeneralImpl(id, firstName, lastName, salary, extractedData);
                        break;
                    case "Commando":
                        corps = lineData[5];
                        startIndex = 5;
                        extractedData = extractDataFromLine(lineData, startIndex);
                        soldier = new Commando(id, firstName, lastName, salary, corps, extractedData);
                        break;
                    case "Engineer":
                        corps = lineData[5];
                        startIndex = 5;
                        extractedData = extractDataFromLine(lineData, startIndex);
                        soldier = new Engineer(id, firstName, lastName, salary, corps, extractedData);
                        break;
                    case "Spy":
                        String codeName = lineData[4];
                        soldier = new Spy(id, firstName, lastName, codeName);
                        break;
                }
            } catch (Exception e) {
                continue;
            }
            if (soldier != null && !soldier.getClass().getSimpleName().equals("LeutenantGeneralImpl")) {
                SoldierRepository.addPrivate(soldier);
            }
            if (soldier != null) {
                soldiers.add(soldier);
            }
        }
        StringBuilder soldiersData = new StringBuilder();
        for (Soldier soldier : soldiers) {
            soldiersData.append(soldier.toString());
        }
        System.out.println(soldiersData.toString());
    }

    private static String[] extractDataFromLine(String[] lineData, int startIndex) {
        String[] extractedData = new String[lineData.length - 1 - startIndex];
        int counter = 0;
        for (int i = startIndex + 1; i <= lineData.length - 1; i++) {
            extractedData[counter++] = lineData[i];
        }
        return extractedData;
    }
}
