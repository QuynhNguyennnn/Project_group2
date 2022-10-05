package com.company;

import java.io.Serializable;
import java.util.Scanner;

public class People implements Action, Serializable {
    private String name;
    private String ID;
    String Address;
    String PhoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public People(String name, String ID, String address, String phoneNumber) {
        this.name = name;
        this.ID = ID;
        Address = address;
        PhoneNumber = phoneNumber;
    }

    @Override
    public void input() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập ID: ");
        String ID = sc.nextLine();
        this.setID(ID);

        System.out.print("Nhập tên: ");
        String name = sc.nextLine();
        this.setName(name);

        System.out.print("Nhập địa chỉ: ");
        String address = sc.nextLine();
        this.setAddress(address);

        System.out.print("Nhập số điện thoại: ");
        String PhoneNumber = sc.nextLine();
        this.setPhoneNumber(PhoneNumber);
    }

    @Override
    public void outputGenerality() {
        System.out.print("-- "+ getID());
    }

    @Override
    public void outputSpecific(){
        System.out.print("-- "+ getID());
    }

    @Override
    public void outputSelection(){
        System.out.print("-- "+ getID());
    }
}

class Customer extends People implements Action, Serializable{
    private String CustomerType;
    private float CumulativePoints;

    public String getCustomerType() {
        return CustomerType;
    }

    public void setCustomerType(String customerType) {
        CustomerType = customerType;
    }

    public float getCumulativePoints() {
        return CumulativePoints;
    }

    public void setCumulativePoints(float cumulativePoints) {
        CumulativePoints = cumulativePoints;
    }

    public Customer(String name, String ID, String address, String phoneNumber, String customerType, int cumulativePoints) {
        super(name, ID, address, phoneNumber);
        CustomerType = customerType;
        CumulativePoints = cumulativePoints;
    }

    @Override
    public void outputGenerality(){
        super.outputGenerality();
        System.out.println(" -- "+ getName()+" -- "+ getCustomerType() +" -- "+ getCumulativePoints() +" --");
    }

    @Override
    public void outputSelection(){

        super.outputSelection();
        System.out.println(" -- "+ getName() +" --");
    }

    @Override
    public void outputSpecific(){
        super.outputSpecific();
        System.out.println(" -- "+ getName() +" -- "+ getCustomerType() +" -- "+ getCumulativePoints() +" -- "+ getPhoneNumber() +" -- "+ getAddress() +" --");
    }
}

class Staff extends People implements Action, Serializable{
    private String Timeserving;
    private String WorkingMode;
    private int Salary;

    public String getTimeserving() {
        return Timeserving;
    }

    public void setTimeserving(String timeserving) {
        Timeserving = timeserving;
    }

    public String getWorkingMode() {
        return WorkingMode;
    }

    public void setWorkingMode(String workingMode) {
        WorkingMode = workingMode;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public Staff(String name, String ID, String address, String phoneNumber, String timeserving, String workingMode, int salary) {
        super(name, ID, address, phoneNumber);
        Timeserving = timeserving;
        WorkingMode = workingMode;
        Salary = salary;
    }

    @Override
    public void input() {
        super.input();
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập khung giờ làm việc: ");
        String Timeserving = sc.nextLine();
        this.setTimeserving(Timeserving);

        do{
            System.out.print("Nhập chể độ làm việc(Full time/ Part time): ");
            String WorkingMode = sc.nextLine();
            this.setWorkingMode(WorkingMode);
            // Bắt buộc người nhập phải nhập đúng Full time hoặc Part time
            if (this.getWorkingMode().equals("Full time") || this.getWorkingMode().equals("Part time")) {
                if (this.getWorkingMode().equals("Full time")) {
                    this.setSalary(30);
                    break;
                } else if(this.getWorkingMode().equals("Part time")) {
                    this.setSalary(20);
                    break;
                }
            }
            else {
                System.out.println("Vui lòng nhập đúng mẫu (Full time/Part time)");
            }
        } while (true);

    }

    @Override
    public void outputGenerality(){
        super.outputGenerality();
        System.out.println(" -- "+ getName()+" --");
    }

    @Override
    public void outputSelection(){
        super.outputSelection();
        System.out.println(" -- "+ getName() +" --");
    }

    @Override
    public void outputSpecific(){
        super.outputSpecific();
        System.out.println(" -- "+ getName() +" -- "+ getWorkingMode() +" -- "+ getSalary() +".000/h -- "+ getTimeserving() +" -- "+ getPhoneNumber() +" -- "+ getAddress() +" --");
    }
}