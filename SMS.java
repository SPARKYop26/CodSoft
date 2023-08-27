//package com.example.demo;
//
//import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.util.ArrayList;
import java.util.Scanner;

public class SMS {
    static Scanner sc=new Scanner(System.in);
    static ArrayList<Students> Student= new ArrayList<Students>(1);
    static int count=0;

    public static void main(String[] args) {
        int choice=0;
        while(choice<1 || choice>4){
            menuOptions();

            choice= sc.nextInt();
            System.out.println();
            if(choice<1 || choice>4) {
                System.out.println("***********************---------------------INVALID INPUT---------------------***********************\n");
            }
        }
        boolean Menu=true;
        int loopCount=0;
        while(Menu) {
            if(loopCount>=1){
                choice=0;
                while(choice<1 || choice>4) {
                    menuOptions();
                    choice = sc.nextInt();
                }
            }
            switch (choice) {
                case 1: setStudents();
                        loopCount++;
                        break;
                case 2: displayStudents();
                        loopCount++;
                        break;
                case 3: modifyStudents();
                        loopCount++;
                        break;
                case 4: deleteStudents();
                        loopCount++;
                        break;
                case 5: Menu=false;
                        loopCount++;
                        break;
            }
        }



    }

    static void menuOptions(){
        System.out.println("Press 1--> Add Students into List");
        System.out.println("Press 2--> View Students List");
        System.out.println("Press 3--> Modify Record");
        System.out.println("Press 4--> Delete Record");
        System.out.println("Press 5--> Exit Student Management System");
    }
    static void setStudents(){
        System.out.println("Enter the number of students to be added:");
        int n=sc.nextInt();
        System.out.println();
        for(int i=0;i<n;i++){
            System.out.println("Enter the Name of student "+(count+1)+":");
            String enter=sc.nextLine();
            String name=sc.nextLine();
            System.out.println("Enter the Roll-No of student "+(count+1)+":");
            int rollNo=sc.nextInt();
            System.out.println("Enter the Division of student "+(count+1)+":");
            char div=sc.next().charAt(0);
            System.out.println("Enter the Phone-No of student "+(count+1)+":");
            String phoneNo=sc.next();
            System.out.println("Enter the DOB(MM-DD-YYYY) of student "+(count+1)+":");
            String DOB=sc.next();
            Student.add(new Students(name,rollNo,div,phoneNo,DOB,count));
            System.out.println("Successfully added Student at INDEX-->"+count);
            if(i!=n-1) {
                count++;
            }
            System.out.println();
        }
    }

    static void displayStudents(){
        if(Student.size()==0){
            System.out.println("No Record Found! \n Please add some Records");

        }

        if(Student.size()!=0){
            System.out.println();
            for(int i=0;i<=count;i++){
                System.out.println("Index:"+Student.get(i).getIndex()+" Name:"+Student.get(i).getName()+" Roll-No:"+Student.get(i).getRollNo()+" Div:"+Student.get(i).getDiv()+" PhoneNo:"+ Student.get(i).phoneNo+" DOB:"+Student.get(i).getDOB());
            }
            System.out.println("\n");
        }



    }

    static void modifyStudents(){
        System.out.println("Enter Index No of the Student:");
        int Index=sc.nextInt();
        boolean modifyMenu=true;
        while(modifyMenu){
            System.out.println("Press 1 --> Change Name!");
            System.out.println("Press 2 --> Change Roll-No!");
            System.out.println("Press 3 --> Change Division!");
            System.out.println("Press 4 --> Change Phone-No!");
            System.out.println("Press 5 --> Change DOB!");
            System.out.println("Press 6 --> TO EXIT!");

            int choice=sc.nextInt();
            System.out.println();
            switch(choice){
                case 1: System.out.println("Enter new Name:");
                        String enter=sc.nextLine();
                        String name=sc.nextLine();
                        Student.get(Index).setName(name);
                        System.out.println("New Name added Successfully!");
                        break;
                case 2: System.out.println("Enter new Roll-No:");
                        int rollNo=sc.nextInt();
                        Student.get(Index).setRollNo(rollNo);
                        System.out.println("New Roll-No added Successfully!");
                        break;
                case 3: System.out.println("Enter new Division:");
                        char div=sc.next().charAt(0);
                        Student.get(Index).setDiv(div);
                        System.out.println("New Division added Successfully!");
                        break;
                case 4: System.out.println("Enter new Phone-No:");
                        String phNo=sc.next();
                        Student.get(Index).setPhoneNo(phNo);
                        System.out.println("New Phone-No added Successfully!");
                        break;
                case 5: System.out.println("Enter new DOB:");
                        String DOB=sc.next();
                        Student.get(Index).setDOB(DOB);
                        System.out.println("New DOB added Successfully!");
                        break;
                case 6: modifyMenu=false;
                        break;
            }



        }
    }
    static void deleteStudents(){
        System.out.println();
        System.out.println();
        System.out.println("Press 1 --> To delete a single Record");
        System.out.println("Press 2 --> To delete entire Record");
        int choice=sc.nextInt();
        switch (choice){
            case 1:System.out.println("Enter the index to be deleted:");
                   int index=sc.nextInt();
                   Student.remove(index);
                   System.out.println(" Record"+index+ " Delete Successful!\n");
                   count--;
                   break;
            case 2: Student.clear();
                    System.out.println("Entire Record Delete Successful!\n");
                    count=0;
                    break;
            default: System.out.println("Pressed Invalid Key!\n");
        }

    }


}

class Students{
    String name;
    int rollNo;
    char div;
    String phoneNo;
    String DOB;
    int Index;
    Students(String name, int rollNo, char div, String phoneNo, String DOB,int Index){
           this.name=name;
           this.rollNo=rollNo;
           this.div=div;
           this.phoneNo=phoneNo;
           this.DOB=DOB;
           this.Index=Index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public char getDiv() {
        return div;
    }

    public void setDiv(char div) {
        this.div = div;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public int getIndex() {
        return Index;
    }

    public void setIndex(int index) {
        Index = index;
    }
}
