package com.company;

import java.util.Scanner;

public class menu {

    public static int menuMain(){
        Scanner sc = new Scanner(System.in);
        int chon;

        System.out.println("""
                ---------------- Chọn đối tượng quản lý ----------------

                1. Khách hàng.
                2. Nhân Viên.
                3. Hàng hóa.
                4. Bill.
                5. Thoát.
                """);
        System.out.print("Chọn thao tác: ");
        chon = Integer.parseInt(sc.nextLine());
        System.out.println();
        return chon;
    }

    public static int menuCustomer(){
        Scanner sc = new Scanner(System.in);
        int chon;

        System.out.println("""
                ---------------- Chọn thao tác cần thực hiện ----------------

                1. Thông tin khách hàng.
                2. Thông tin chi tiết của khách hàng.
                3. Thêm khách hàng.
                4. Chỉnh sửa thông tin.
                5. Quit.
                """);
        System.out.print("Chọn thao tác: ");
        chon = Integer.parseInt(sc.nextLine());
        System.out.println();
        return chon;
    }

    public static int menuStaff(){
        Scanner sc = new Scanner(System.in);
        int chon;

        System.out.println("""
                ---------------- Chọn thao tác cần thực hiện ----------------

                1. Thông tin nhân viên.
                2. Thông tin chi tiết của nhân viên.
                3. Thêm nhân viên.
                4. Chỉnh sửa thông tin.
                5. Quit.
                """);
        System.out.print("Chọn thao tác: ");

        chon = Integer.parseInt(sc.nextLine());
        System.out.println();
        return chon;
    }

    public static int menuGoods(){
        Scanner sc = new Scanner(System.in);
        int chon;

        System.out.println("""
                ---------------- Chọn thao tác cần thực hiện ----------------

                1. Thông tin hàng hóa.
                2. Thông tin chi tiết của hàng hóa.
                3. Thêm hàng hóa.
                4. Nhập hàng hóa.
                5. Quit.
                """);
        System.out.print("Chọn thao tác: ");

        chon = Integer.parseInt(sc.nextLine());
        System.out.println();
        return chon;
    }
}
