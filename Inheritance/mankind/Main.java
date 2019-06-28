package mankind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

//        String[] studentInfo = bfr.readLine().split(" ");
        try {
//            String name = studentInfo[0];
//            String lastName = studentInfo[1];
//            String facultyNum = studentInfo[2];

            Student student = new Student("NormalFirstName", "illegalSecondName", "7654321");
            System.out.println(student.toString());

//            String[] workerInfo = bfr.readLine().split(" ");
//            name = workerInfo[0];
//            lastName = workerInfo[1];
//            double salary = Double.parseDouble(workerInfo[2]);
//            double workHours = Double.parseDouble(workerInfo[3]);

//            Worker worker = new Worker(name, lastName, salary, workHours);
//            System.out.println(worker.toString());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }



    }
}
