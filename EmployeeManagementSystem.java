package Project1;

import java.io.*;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;


public class EmployeeManagementSystem extends MainMenu {
    public static void main(String[] args) throws Exception {
        try {
            MainMenu menu = new MainMenu();
            menu.menu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class MainMenu {
    public void menu() throws Exception{
        Scanner scan = new Scanner(System.in);

        System.out.println();
        System.out.println("    ************************************************");
        System.out.println("          Organizational Management System          ");
        System.out.println("    ************************************************\n");
        System.out.println("Press 1 : Add an Employee Details");
        System.out.println("Press 2 : See an Employee Details");
        System.out.println("Press 3 : Remove an Employee");
        System.out.println("Press 4 : Update an Employee Details");
        System.out.println("Press 5 : Exit the EMS Portal\n");
        System.out.print("Please enter choice: ");
        String choice = scan.next();
        if (!(choice.matches("[1-5]+") && (choice.length() > 0))) {
            do {
                System.out.print("Please enter correct choice: ");
                choice = scan.next();
            } while (!(choice.matches("[1-5]+") && (choice.length() > 0)));
        }

        switch (choice){
            case "1": {
                case1();
                break;
            }
            case "2":{
                case2();
                break;
            }
            case "3":{
                case3();
                break;
            }
            case "4":{
                case4();
                break;
            }
            case "5": {
                case5();
                break;
            }
        }

        do {
            System.out.println("\nPress Enter to Continue ...");
            switch (System.in.read()) {
            }
            String key = scan.nextLine();
            while ("".equals(key)) {
                menu();
            }
        } while (!choice.equals(5));
    }

    public void case1(){
        try {
            Employee_Add employee = new Employee_Add();
            employee.createFile();
        } catch (Exception e) {
            System.out.println("File not created!");
        }
    }
    public void case2() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please Enter Employee's ID : ");
        String s = scanner.next();
        Employee_Show employee = new Employee_Show();
        employee.viewFile(s);
    }
    public void case3() throws FileNotFoundException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee's ID to remove file: ");
        int ID = scanner.nextInt();
        Employee_Remove employee = new Employee_Remove();
        employee.removeFile(ID);
    }
    public void case4() throws Exception,FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee's ID to update file: ");
        String s = scanner.next();
        Employee_Show employee_show = new Employee_Show();
        employee_show.viewFile(s);
        System.out.println("Please Enter the detail you want to Update : ");
        System.out.println("For example: ");
        System.out.print("If you want to Change the Name, then Enter Current Name and Press Enter. Then write the new Name then Press Enter. It will Update the Name.\n");
        System.out.print("\n");
        scanner.nextLine();
        String o = scanner.nextLine();
        System.out.print("Please Enter the Updated Info: ");
        String n = scanner.nextLine();
        Employee_Update employee = new Employee_Update();
        employee.update_file(s,o,n);
    }
    public void case5(){
        CodeExit exit = new CodeExit();
        exit.out();
    }
}

abstract class EmployeeDetail {
    Scanner in = new Scanner(System.in);
    protected int ID;
    protected String name;
    protected int age;
    protected String email;
    protected String position;
    protected long contact;
    protected float salary;

    public void employee_detail() throws Exception {
        System.out.print("Enter Employee's name ---------: ");
        name = in.nextLine();
        if (!(name.matches("[a-zA-z]+") && (name.length() > 0))) {
            do {
                System.out.print("Please enter correct Employee's name: ");
                name = in.nextLine();
            } while (!(name.matches("[a-zA-z]+") && (name.length() > 0)));
        }

        System.out.print("Enter Employee's Age --: ");
        String correctAge = String.valueOf(in.next());
        if (!(correctAge.matches("[0-9]+") && (correctAge.length() > 0))) {
            do {
                System.out.print("Please enter correct Employee's Age: ");
                correctAge = in.next();
            } while (!((correctAge).matches("[0-9]+") && (correctAge.length() > 0)));
        }
        age = Integer.parseInt(correctAge);

        System.out.print("Enter Employee's ID -----------: ");
        String correctID = String.valueOf(in.next());
        if (!(correctID.matches("[0-9]+") && (correctID.length() > 0))) {
            do {
                System.out.print("Please enter correct Employee's ID: ");
                correctID = in.next();
            } while (!((correctID).matches("[0-9]+") && (correctID.length() > 0)));
        }
        ID = Integer.parseInt(correctID);

        try{
            System.out.print("Enter Employee's Email ID -----: ");
            email = in.next();
        }
        catch (NumberFormatException nm){
            System.out.println("entered email type is not correct");
        }

        try{
            System.out.print("Enter Employee's Position -----: ");
            position = in.next();
        }
        catch (NumberFormatException nm){
            System.out.println("entered position type is not correct");
        }

        System.out.print("Enter Employee's contact Info -: ");
        String correctContact = String.valueOf(in.next());
        if (!(correctContact.matches("[0-9]+") && (correctContact.length() > 0))) {
            do {
                System.out.print("Please enter correct Employee's contact Info: ");
                correctContact = in.next();
            } while (!((correctContact).matches("[0-9]+") && (correctContact.length() > 0)));
        }
        contact = Long.parseLong(correctContact);

        System.out.print("Enter Employee's Salary -------: ");
        String correctSalary = String.valueOf(in.next());
        if (!(correctSalary.matches("[0-9]+") && (correctSalary.length() > 0))) {
            do {
                System.out.print("Please enter correct Employee's Salary: ");
                correctSalary = in.next();
            } while (!((correctSalary).matches("[0-9]+") && (correctSalary.length() > 0)));
        }
        salary = Float.parseFloat(correctSalary);
    }
}


class Employee_Add extends EmployeeDetail{
    public Employee_Add(){}
    public Employee_Add(int id,String name,int age,String email,String position,long contact,float salary){
        this.ID = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.position = position;
        this.contact = contact;
        this.salary = salary;
    }

    public void createFile() throws Exception,FileNotFoundException {
        EmployeeDetail employee = new Employee_Add();
        employee.employee_detail();
        List<String> arr = getStrings(employee);
        FileWriter fileWriter = new FileWriter("src/Project1/" + "file" + employee.ID + ".txt");
        if(new File("src/Project1/" + "file" + employee.ID + ".txt").exists()){
            int i = 0;
            while (i < arr.size()) {
                fileWriter.write(arr.get(i));
                i++;
            }
            System.out.println(employee.name + " employee has been added :)");
            fileWriter.close();
        }
        else
            throw new FileNotFoundException("File with " + employee.ID + " ID not found!");
    }

    private static List<String> getStrings(EmployeeDetail employee) {
        List<String> strings = new ArrayList<String>(7);
        strings.add("Employee's ID ---------: " + employee.ID +"\n");
        strings.add("Employee's name -------: " + employee.name +"\n");
        strings.add("Employee's Age --------: " + employee.age +"\n");
        strings.add("Employee contact Info -: " + employee.contact +"\n");
        strings.add("Email Information -----: " + employee.email +"\n");
        strings.add("Employee Position -----: " + employee.position +"\n");
        strings.add("Employee Salary -------: " + employee.salary +"\n");
        return strings;
    }


}

class Employee_Show {
    public void viewFile(String s) throws FileNotFoundException{
        try {
            Scanner scan = new Scanner(new File("src/Project1/file" + s + ".txt"));
            if (scan.hasNextLine()) {
                do {
                    String lines = scan.nextLine();
                    System.out.println(lines);
                } while (scan.hasNextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File with this ID not found!");
        }

    }
}

interface Remove{
    void removeFile(int ID) throws FileNotFoundException;
}

class Employee_Remove implements Remove {
    @Override
    public void removeFile(int ID) throws FileNotFoundException{
        String fullName = "src/Project1/file" + ID + ".txt";
        File file = new File(fullName);
        try {
            if (!file.exists()) {
                System.out.println("File with this ID doesn't exist");
            } else {
                if (!file.delete()) {
                    System.out.println("Delete Employee failed!");
                } else {
                    System.out.println();
                    System.out.println("Employee has been removed Successfully");
                }
            }

        } catch (Exception e) {
            System.out.println();
            System.out.println("Employee does not exists :( ");
        }

    }
}


class Employee_Update {
    public Employee_Update(){}
    public void update_file(String s, String o,String n) throws Exception {
        File file = new File("src/Project1/file" + s + ".txt");
        Scanner in;
        try {
            in = new Scanner(file);
            Object [][] strings = new Object[1+1+1+1+1+1+1][1+1];
            int i=0;
            while (in.hasNextLine()){
                String str = in.nextLine();
                Pattern pattern = Pattern.compile(":");
                strings[i] = pattern.split(str);
                i++;
            }
            if ("Employee name".equals(o)) {
                o = "Employee's name -------";
            } else if ("Employee ID".equals(o)) {
                o = "Employee's ID ---------";
                String fullName2 = "src/Project1/file" + n + ".txt";
                File file2 = new File(fullName2);
                Employee_Remove remove_employee= new Employee_Remove();
                int ID = Integer.parseInt(s);
                remove_employee.removeFile(ID);
                writer(file2, strings);
            } else if ("Employee Age".equals(o)) {
                o = "Employee's Age --------";
            } else if ("Employee contact Info".equals(o)) {
                o = "Employee contact Info -";
            } else if ("Email Information".equals(o)) {
                o = "Email Information -----";
            } else if ("Employee Position".equals(o)) {
                o = "Employee Position -----";
            } else if ("Employee Salary".equals(o)) {
                o = "Employee Salary -------";
            }
            equalizer(o, n, strings);
            writer(file, strings);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File with this ID not exists!");
        }


    }

    private static void equalizer(String o, String n, Object[][] strings) {
        int k = 0;
        while (k < 7) {
            int l = 0;
            while (l < 1) {
                if(strings[k][l].equals(o)){
                    strings[k][l+1] = " "+ n;
                }
                l++;
            }
            k++;
        }
    }

    private void writer(File file, Object[][] strings) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(file);
        int k = 0;
        while (k < 7) {
            int l = 0;
            while (l < 2) {
                switch (l) {
                    case 0 -> writer.write(strings[k][l] + ":");
                    default -> writer.write(strings[k][l] + "");
                }
                l++;
            }
            writer.write("\n");
            k++;
        }
        writer.close();
    }

}



class CodeExit {
    public void out(){
        System.out.println("\nThank You For Sharing your details :) ");
        System.exit(0);
    }
}

