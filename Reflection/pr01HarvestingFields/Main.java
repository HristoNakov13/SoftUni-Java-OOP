package pr01HarvestingFields;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        RichSoilLand richSoilLand = new RichSoilLand();
        Class clazz = richSoilLand.getClass();

        String input;
        while (!"HARVEST".equals(input = bfr.readLine())) {
            List<Field> fieldsAsList = Arrays.asList(clazz.getDeclaredFields());
            StringBuilder print = new StringBuilder();

            if (input.equals("all")) {
                fieldsAsList.forEach(field -> {
                    String curretMod = getModifier(field.getModifiers());
                    print.append(String.format("%s %s %s", curretMod, field.getType().getSimpleName(), field.getName()))
                            .append(System.lineSeparator());
                });
                System.out.println(print.toString().trim());
                continue;
            }
            String finalInput = input;
            fieldsAsList.stream().filter(field -> {
                String currentMod = getModifier(field.getModifiers());
                return currentMod.equals(finalInput);
            }).forEach(field -> {
                print.append(String.format("%s %s %s", finalInput, field.getType().getSimpleName(), field.getName()))
                        .append(System.lineSeparator());
            });
            System.out.println(print.toString().trim());
        }
    }

    private static String getModifier(int mod) {
        String modifier = "private";
        if (Modifier.PROTECTED == mod) {
            modifier = "protected";
        } else if (Modifier.PUBLIC == mod) {
            modifier = "public";
        }
        return modifier;
    }
}

