package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) {
        ArrayList<Employee> staff = loadStaffFromFile();
        //сравнение и сортировка объектов * лямбда-выражения*
        Collections.sort(staff, Comparator.comparing(Employee::getSalary));
//           return (o1.getSalary()).compareTo(o2.getSalary())|(o1.getName().compareTo(o2.getName()));
//        });

        for (Employee employee : staff) {
            System.out.println(employee);
        }
        //увеличение зарплаты всех сотрудников *метод forEach и лямбда-выражения*
        int salaryIncrease = 10000;
        staff.forEach(employee -> employee.setSalary(employee.getSalary()+salaryIncrease));
        System.out.println("\nNew salaries: ");
        staff.forEach(System.out::println);
    }

    private static ArrayList<Employee> loadStaffFromFile() {
        ArrayList<Employee> staff = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for (String line : lines) {
                String[] fragments = line.split("\t");
                if (fragments.length != 3) {
                    System.out.println("Неправильная длина строки" + line);
                    continue;
                }
                staff.add(new Employee(fragments[0],
                        Integer.parseInt(fragments[1]),
                        (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staff;
    }
}