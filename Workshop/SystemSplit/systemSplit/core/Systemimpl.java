package systemSplit.core;

import systemSplit.components.hardware.Hardware;
import systemSplit.components.hardware.HeavyHardware;
import systemSplit.components.hardware.PowerHardware;
import systemSplit.components.software.Software;

import java.util.LinkedHashMap;
import java.util.Map;

public class Systemimpl {
    private static Map<String, Hardware> HARDWARE = new LinkedHashMap<>();
    private static Map<String, Hardware> DUMP = new LinkedHashMap<>();

    public static void registerHardware(Hardware hardware) {
        HARDWARE.put(hardware.getName(), hardware);
    }

    public static void registerSoftware(String hardwareComponent, Software software) {
        if (HARDWARE.containsKey(hardwareComponent)) {
            HARDWARE.get(hardwareComponent).registerSoftware(software);
        }
    }

    private static int getSoftwareComponentsCount() {
        int count = 0;
        for (Hardware component : HARDWARE.values()) {
            count += component.softwareComponentsCount();
        }
        return count;
    }

    private static int getHardwareComponentsCount() {
        return HARDWARE.size();
    }

    private static String getTotalMemory() {
        int memory = 0;
        int takenMemory = 0;
        for (Hardware component : HARDWARE.values()) {
            memory += component.getMemory();
            takenMemory += component.getTakenMemory();
        }
        return String.format("Total Operational Memory: %d / %d", takenMemory, memory);
    }

    private static String getTotalCapacity() {
        int capacity = 0;
        int takenCapacity = 0;
        for (Hardware component : HARDWARE.values()) {
            capacity += component.getCapacity();
            takenCapacity += component.getTakenCapacity();
        }
        return String.format("Total Capacity Taken: %d / %d", takenCapacity, capacity);
    }

    public static String analyze() {
        StringBuilder analysis = new StringBuilder();
        analysis
                .append("System Analysis")
                .append(java.lang.System.lineSeparator())
                .append(String.format("Hardware Components: %d", Systemimpl.getHardwareComponentsCount()))
                .append(java.lang.System.lineSeparator())
                .append(String.format("Software Components: %d", Systemimpl.getSoftwareComponentsCount()))
                .append(System.lineSeparator())
                .append(Systemimpl.getTotalMemory())
                .append(java.lang.System.lineSeparator())
                .append(Systemimpl.getTotalCapacity());

        return analysis.toString();
    }

    public static String systemSplit() {
        StringBuilder powerInfo = new StringBuilder();
        StringBuilder heavyInfo = new StringBuilder();
        for (Hardware hardware : HARDWARE.values()) {
            if (hardware instanceof PowerHardware) {
                powerInfo
                        .append(hardware.toString())
                        .append(System.lineSeparator());
            } else {
                heavyInfo
                        .append(hardware.toString())
                        .append(System.lineSeparator());
            }

        }
        return String.format("%s%s", powerInfo.toString(), heavyInfo.toString().trim());
    }

    public static void releaseSoftware(String hardwareComponent, String softwareComponent) {
        if (HARDWARE.containsKey(hardwareComponent)) {
            HARDWARE.get(hardwareComponent).releaseSoftwareComponent(softwareComponent);
        }
    }

    public static void dump(String component) {
        if (HARDWARE.containsKey(component)) {
            Hardware hardware = HARDWARE.get(component);
            HARDWARE.remove(component);
            DUMP.put(component, hardware);
        }
    }

    public static void restore(String component) {
        if (DUMP.containsKey(component)) {
            Hardware hardware = DUMP.get(component);
            Systemimpl.registerHardware(hardware);
            DUMP.remove(component);
        }
    }

    public static void destroy(String component) {
        DUMP.remove(component);
    }

    public static String dumpAnalysis() {
        int heavyCount = 0;
        int powerCount = 0;
        int dumpedMemory = 0;
        int dumpedCapacity = 0;
        int expressCount = 0;
        int lightCount = 0;

        for (Hardware hardware : DUMP.values()) {
            if (hardware instanceof HeavyHardware) {
                heavyCount++;
            } else {
                powerCount++;
            }
            dumpedCapacity += hardware.getTakenCapacity();
            dumpedMemory += hardware.getTakenMemory();
            expressCount += hardware.getExpressSoftwareCount();
            lightCount += hardware.getLightSoftwareCount();
        }
        StringBuilder analysis = new StringBuilder();
        analysis
                .append("Dump Analysis")
                .append(System.lineSeparator())
                .append(String.format("Power Hardware Components: %d", powerCount))
                .append(System.lineSeparator())
                .append(String.format("Heavy Hardware Components: %d", heavyCount))
                .append(System.lineSeparator())
                .append(String.format("Express Software Components: %d", expressCount))
                .append(System.lineSeparator())
                .append(String.format("Light Software Components: %d", lightCount))
                .append(System.lineSeparator())
                .append(String.format("Total Dumped Memory: %d", dumpedMemory))
                .append(System.lineSeparator())
                .append(String.format("Total Dumped Capacity: %d", dumpedCapacity));

        return analysis.toString();
    }
}
