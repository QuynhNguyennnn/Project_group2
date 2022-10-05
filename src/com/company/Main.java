package com.company;


import java.io.*;
import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Action{
    void input() ;
    void outputGenerality(); // In thông tin tổng quát
    void outputSpecific(); // In thông tin chi tiết
    void outputSelection(); // In thông tin để lựu chọn

}

public class Main {

    public static void Bill(List<Customer> ListC, List<Staff> ListS, List<Goods> ListG) {
        Scanner sc = new Scanner(System.in);

        String chonC, chonS, nameC = null, nameS = null;
        int TotalAmount = 0;
        int indexC = 0; // Lưu vị trí của khách hàng để lấy thông tin từ chuỗi
        int indexG = 0; // Lưu vị trí của sản phẩm để lấy thông tin từ chuỗi
        int[] QuantityPurchased = new int[20]; // Lưu số lượng sản phẩm mua
        String[] IDG = new String[20]; // Lưu ID của sản phẩm
        String[] NameG = new String[20]; // Lưu tên của sản phẩm
        int[] priceG = new int[20]; // Lưu giá sản phẩm
        String[] Material = new String[20]; // Lưu chất liệu sản phẩm
        int[] Year = new int[20]; // Lưu năm sản xuất của sản phẩm

        while (true) {
            ListC.forEach(Customer::outputSelection);

            System.out.print("\nNhập ID khách hàng: ");
            chonC = sc.nextLine();
            int i = 0;
            boolean check = false;
            for (Customer c : ListC) {
                if (c.getID().equals(chonC)) {
                    indexC = i;
                    check = true;
                    break;
                } else i++;
            }
            if (check) break;
            else System.out.println("\nID bạn tìm không có!!!\n"); // Kiểm tra ID và lưu vị của khách hàng
        }
        System.out.println();
        for (Customer c : ListC)
            if (c.getID().equals(chonC)) nameC = c.getName(); // Lưu tên của khách hàng

        while (true) {
            ListS.forEach(Staff :: outputSelection);

            System.out.print("\nNhập ID nhân viên: ");
            chonS = sc.nextLine();
            System.out.println();
            boolean check = false;
            for (Staff s : ListS) {
                if (s.getID().equals(chonS)) {
                    check = true;
                    break;
                }
            }
            if (check) break;
            else System.out.println("ID bạn tìm không có!!!\n"); // Kiểm tra ID của nhân viên
        }

        for (Staff s : ListS)
            if (s.getID().equals(chonS)) nameS = s.getName(); // Lưu tên của nhân viên

        int k = 0; // Thứ tự của sản phẩm mua
        do {
            ListG.forEach(Goods :: outputSelection);
            while (true) {
                System.out.print("\nNhập ID hàng hóa: ");
                IDG[k] = sc.nextLine();
                int a = 0;
                boolean check = false;
                for (Goods g : ListG) {
                    if (g.getID().equals(IDG[k])) {
                        check = true;
                        indexG = a;
                        break;
                    } else {
                        a++;
                    }
                }
                if (check) {
                    break;
                }else System.out.println("\nID bạn tìm không có!!!"); // Kiểm tra ID và lưu vị của sản phẩm
            }

            if (ListG.get(indexG).getRemainingProduct() == 0) {
                System.out.println("\nKhông còn hàng hóa!!!");
            } else {
                System.out.print("\nNhập số lượng cần mua: ");
                QuantityPurchased[k] = Integer.parseInt(sc.nextLine());
                if (ListG.get(indexG).getRemainingProduct() < QuantityPurchased[k]) {
                    System.out.println("\nKhông đủ hàng hóa!!!"); // Kiểm tra hàng hóa
                } else {
                    ListG.get(indexG).setRemainingProduct(ListG.get(indexG).getRemainingProduct() - QuantityPurchased[k]); // Lưu số lượng sản phẩm còn lại
                    TotalAmount = TotalAmount + QuantityPurchased[k] * ListG.get(indexG).getPrice(); // Tổng Bill
                    priceG[k] = ListG.get(indexG).getPrice(); // Giá tiền lưu vào vị trí k
                    Year[k] = ListG.get(indexG).getYearOfManufacture(); // Năm sản xuất lưu vào vị trí k
                    Material[k] = ListG.get(indexG).getMaterial(); // Lưu chất liệu vào vị trí k
                    NameG[k] = ListG.get(indexG).getName(); // Lưu tên sản phẩm vào vị trí k

                    ListC.get(indexC).setCumulativePoints((ListC.get(indexC).getCumulativePoints() + (float) TotalAmount / 10000000)); // Tính điểm tích lũy
                    if (ListC.get(indexC).getCumulativePoints() >= 10 && ListC.get(indexC).getCumulativePoints() < 100)
                        ListC.get(indexC).setCustomerType("Bạc");
                    else if (ListC.get(indexC).getCumulativePoints() >= 100 && ListC.get(indexC).getCumulativePoints() < 1000)
                        ListC.get(indexC).setCustomerType("Vàng");
                    else if (ListC.get(indexC).getCumulativePoints() >= 1000 && ListC.get(indexC).getCumulativePoints() < 10000)
                        ListC.get(indexC).setCustomerType("Kim cương");
                    else if (ListC.get(indexC).getCumulativePoints() >= 10000)
                        ListC.get(indexC).setCustomerType("VIP"); // xét loại khách hàng dựa trên điểm tích lũy
                }
            }
            String ans ;
            do{
                System.out.print("\nBạn có còn muốn mua thêm sản phẩm(Có/Không): ");
                ans = sc.nextLine();
                System.out.println(ans);
                if (ans.equals("Có") || ans.equals("Không")) break;
                else System.out.println("Vui lòng nhập theo mẫu!!!");
            } while (true);
            System.out.println();
            if (ans.equals("Có")) k++;
            else break;
        } while (true); // Kiểm tra xem khách có muốn mua hàng tiếp không

        System.out.println("------------------- Bill -------------------");
        System.out.println("\nKhách hàng: ");
        System.out.println("-- ID -- Tên --\n" +
                "-- "+ chonC +" -- "+ nameC +" --");
        System.out.println("\nNhân viên: ");
        System.out.println("-- ID -- Tên --\n" +
                "-- "+ chonS +" -- "+ nameS +" --");
        System.out.println("\nHàng hóa: ");
        System.out.println("-- ID -- Tên -- Chất liệu -- Số lượng -- Năm sản xuất -- Giá --");
        for (int i = 0 ; i <= k ; ++i){
            System.out.println("-- "+ IDG[i] +" -- "+ NameG[i] +" -- "+ Material[i] +" -- "+ QuantityPurchased[i] +" -- " + Year[i] +" -- "+ priceG[i] +" --");
        }
        System.out.println("Tổng tiền: "+ TotalAmount+"\n");
        // Xuất ra màn hình

    }

    public static void main(String[] args) throws WrongInputException, IOException {
        Scanner sc = new Scanner(System.in);
        int chon; // Chọn mục thực hiện trong menu
        String chonID; // Chọn ID để sử dụng

        List<Customer> ListC = new ArrayList<>(); // Lưu các khách hàng
        List<Staff> ListS = new ArrayList<>(); // Lưu các nhân viên
        List<Goods> ListG = new ArrayList<>(); // Lưu các sản phẩm đang có

        File customerData = new File("./data/dataCustomer.txt");
        File staffData = new File("./data/dataStaff.txt");
        File goodsData = new File("./data/dataGoods.txt");


        // Kiểm tra xem file đã có chưa nếu chưa có thì sẽ tạo ArrayList mới
        try {

            if (customerData.exists()){
                FileInputStream iC = new FileInputStream(customerData);
                ObjectInputStream iiC = new ObjectInputStream(iC);
                ListC =(List<Customer>) iiC.readObject();
                iiC.close();
            }
            else ListC = new ArrayList<>();

            if (staffData.exists()){
                FileInputStream iS = new FileInputStream(staffData);
                ObjectInputStream iiS = new ObjectInputStream(iS);
                ListS =(List<Staff>) iiS.readObject();
                iiS.close();
            }
            else ListS = new ArrayList<>();

            if (goodsData.exists()) {
                FileInputStream iG = new FileInputStream(goodsData);
                ObjectInputStream iiG = new ObjectInputStream(iG);
                ListG =(List<Goods>) iiG.readObject();
                iiG.close();
            }
            else ListG = new ArrayList<>();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        menu menu = null;
        do {
            do {
                try {
                    chon = menu.menuMain();
                    if (chon > 5 || chon < 1) {
                        throw new WrongInputException("WrongNumberRequest"); // Bắt lỗi nhập khác số trong menu cho nhập lại
                    }
                    else break;
                } catch (WrongInputException e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("Vui lòng nhập số!"); // Bắt lỗi nhập chữ
                }
            } while (true);
            switch (chon){
                case 1: // Thao tác với khách hàng
                    do{
                        do {
                            try {
                                chon = menu.menuCustomer();
                                if (chon > 5 || chon < 1) {
                                    throw new WrongInputException("WrongNumberRequest"); // Bắt lỗi nhập khác số trong menu cho nhập lại
                                }
                                break;
                            } catch (WrongInputException e) {
                                System.out.println(e.getMessage());
                            } catch (NumberFormatException e) {
                                System.out.println("Vui lòng nhập số!!!"); // Bắt lỗi nhập chữ
                            }
                        } while (true);
                        switch (chon){
                            case 1: // xem thông tin tổng quát của khách hàng
                                System.out.println("""
                                        ----- Danh sách thông tin khách hàng -----

                                        -- ID -- Tên -- Loại khách -- Điểm tích lũy --""");
                                ListC.forEach(Customer::outputGenerality);
                                System.out.println();
                                break;
                            case 2: // xem thông tin chi tiết của khách hàng
                                System.out.println("""
                                        ----- Danh sách chi tiết thông tin khách hàng -----

                                        -- ID -- Tên -- Loại khách -- Điểm tích lũy -- Số điện thoại -- Địa chỉ --""");
                                ListC.forEach(Customer::outputSpecific);
                                System.out.println();
                                break;
                            case 3: // thêm khách hàng mới
                                Customer customer = new Customer("a","a","a","a","Đồng",0);
                                customer.input();
                                ListC.add(customer); // nhập khách hàng mới
                                System.out.println("Hoàn thành nhập dữ liệu");
                                System.out.println();
                                break;
                            case 4: // sửa thông tin khách hàng
                                System.out.println("Danh sách khách hàng: \n");
                                ListC.forEach(Customer::outputSelection);

                                while(true) {
                                    System.out.print("\nNhập ID khách hàng cẩn sửa thông tin: ");
                                    chonID = sc.nextLine();
                                    boolean check = false;
                                    for (Customer c : ListC ){
                                        if (c.getID().equals(chonID)){
                                            check = true;
                                            break;
                                        }
                                    }
                                    if (check)break;
                                    else System.out.println("\nID bạn tìm không có!!!"); // Lựa chọn ID của khách để sửa thông tin
                                }
                                do {
                                    do {
                                        System.out.print("""

                                                Chọn thông tin cần sửa
                                                1. Địa chỉ.
                                                2. Số điện thoại.
                                                3. Thoát.
                                                Chọn mục cần sửa:\s""");
                                        chon = Integer.parseInt(sc.nextLine());
                                        if (chon > 3 || chon < 1) System.out.println("Vui lòng nhập từ 1-3!!!");
                                        else break;
                                    } while (true);
                                    for (Customer c : ListC) {
                                        if (c.getID().equals(chonID)) {
                                            switch (chon) {
                                                case 1 -> {
                                                    System.out.print("Nhập địa chỉ mới: ");
                                                    c.setAddress(sc.nextLine());
                                                }
                                                case 2 -> {
                                                    System.out.print("Nhập số điện thoại mới: ");
                                                    c.setPhoneNumber(sc.nextLine());
                                                }
                                                case 3 -> System.out.println("Chỉnh sửa thông tin hoàn tất!!!");
                                            }
                                            break; // Sửa chửa thông tin khách hàng
                                        }
                                    }
                                } while (chon != 3);
                                chon = 0;
                                System.out.println();
                                break;
                            case 5:
                                break;
                        }
                    }while (chon != 5);
                    chon = 0;
                    break;
                case 2: // Thao tác với nhân viên
                    do{
                        do {
                            try {
                                chon = menu.menuStaff();
                                if (chon > 5 || chon < 1) {
                                    throw new WrongInputException("WrongNumberRequest"); // Bắt lỗi nhập khác số trong menu
                                }
                                else break;
                            } catch (WrongInputException e) {
                                System.out.println(e.getMessage());
                            } catch (NumberFormatException e) {
                                System.out.println("Vui lòng nhập số!"); // Bắt lỗi nhập chữ
                            }
                        } while (true);

                        switch (chon) {
                            case 1: // Xem thông tin tổng quát nhân viên
                                System.out.println("----- Danh sách thông tin nhân viên -----\n" +
                                        "-- Tên --");
                                ListS.forEach(Staff :: outputGenerality);
                                System.out.println();
                                break;
                            case 2: // Xem thông tin chi tiết khách hàng
                                System.out.println("""
                                        ----- Danh sách chi tiết thông tin nhân viên -----

                                        -- ID -- Tên -- Chế độ làm việc -- Lương -- Thời gian làm việc -- Số điện thoại -- Địa chỉ --""");
                                ListS.forEach(Staff :: outputSpecific);
                                System.out.println();
                                break;
                            case 3: // Thêm nhân viên mới
                                Staff staff = new Staff("a", "a", "a", "a", "a", "a", 1);
                                staff.input();
                                ListS.add(staff);
                                System.out.println("Hoàn thành nhập dữ liệu");
                                System.out.println();
                                break;
                            case 4: // Sửa thông tin nhân viên
                                System.out.println("Danh sách nhân viên: \n");
                                ListS.forEach(Staff :: outputSelection);
                                while (true) {
                                    System.out.print("\nNhập ID nhân viên cẩn sửa thông tin: ");
                                    chonID = sc.nextLine();
                                    boolean check = false;
                                    for (Staff s : ListS) {
                                        if (s.getID().equals(chonID)) {
                                            check = true;
                                            break;
                                        }
                                    }
                                    if (check) break;
                                    else System.out.println("\nID bạn tìm không có!!!"); // chọn ID nhân viên để sửa
                                }
                                do{

                                    do {
                                        System.out.print("""

                                                Chọn thông tin cần sửa

                                                1. Địa chỉ.
                                                2. Số điện thoại.
                                                3. Thời gian làm việc.
                                                4. Chế độ làm việc.
                                                5. Thoát.

                                                Chọn mục cần sửa:\s""");
                                        chon = Integer.parseInt(sc.nextLine());
                                        if(chon > 5 || chon < 1) System.out.println("Vui lòng nhập số từ 1-5!!!");
                                        else break;
                                    } while (true);
                                    System.out.println();
                                    for (Staff s : ListS) {
                                        if (s.getID().equals(chonID)) {
                                            switch (chon) {
                                                case 1:
                                                    System.out.print("Nhập địa chỉ mới: ");
                                                    s.setAddress(sc.nextLine());
                                                    break;
                                                case 2:
                                                    System.out.print("Nhập số điện thoại mới: ");
                                                    s.setPhoneNumber(sc.nextLine());
                                                    break;
                                                case 3:
                                                    System.out.print("Nhập thời gian làm việc mới: ");
                                                    s.setTimeserving(sc.nextLine());
                                                    break;
                                                case 4:
                                                    do {
                                                        System.out.print("Nhập chế độ làm việc mới(Full time/Part time): ");
                                                        s.setWorkingMode(sc.nextLine());
                                                        if (s.getWorkingMode().equals("Full time")) {
                                                            s.setSalary(30);
                                                            break;
                                                        } else if(s.getWorkingMode().equals("Part time")) {
                                                            s.setSalary(20);
                                                            break;
                                                        } else System.out.println("Vui lòng nhập đúng mẫu (Full time/Part time)");
                                                    } while (true);
                                                    break;
                                                case 5:
                                                    System.out.println("Chỉnh sửa thông tin hoàn tất!!!");
                                                    break;
                                            }
                                            break;
                                        }
                                    }
                                } while (chon != 5);
                                chon = 0;
                                System.out.println();
                                break;
                            case 5:
                                break;
                        }
                    }while (chon != 5);
                    chon = 0;
                    break;
                case 3: // Thao tác với sản phẩm
                    do{
                        do {
                            try {
                                chon = menu.menuGoods();
                                if (chon > 5 || chon < 1) {
                                    throw new WrongInputException("WrongNumberRequest"); // Bắt lỗi nhập số không có trong menu
                                }
                                else break;
                            } catch (WrongInputException e) {
                                System.out.println(e.getMessage());
                            } catch (NumberFormatException e) {
                                System.out.println("Vui lòng nhập số!"); // Bắt lỗi nhập chữ
                            }
                        } while (true);
                        switch (chon){
                            case 1: // Xem thông tin tổng quát sản phẩm
                                System.out.println("""
                                        ----- Danh sách thông tin sản phẩm -----

                                        -- Tên -- Chất liệu --""");
                                ListG.forEach(Goods :: outputGenerality);
                                System.out.println();
                                break;
                            case 2: // Xem thông tin chi tiết sản phẩm
                                System.out.println("""
                                        ----- Danh sách chi tiết thông tin khách hàng -----

                                        -- ID -- Tên -- Chất liệu -- Giá -- Số lượng còn lại -- Năm sản xuất̉ --""");
                                ListG.forEach(Goods :: outputSpecific);
                                System.out.println();
                                break;
                            case 3: // Thêm sản phẩm mới
                                Goods goods = new Goods("a","a","a",1,1,1,0);
                                goods.input();
                                ListG.add(goods);
                                System.out.println("Hoàn thành nhập dữ liệu");
                                System.out.println();
                                break;
                            case 4: // Nhập thêm sản phẩm đã có sẵn
                                System.out.println("Danh sách hàng hóa: \n");
                                ListG.forEach(Goods :: outputSelection);
                                System.out.print("\nNhập ID hàng hóa cẩn nhập: ");
                                chonID = sc.nextLine();
                                boolean check = false; // Biến kiểm tra có sản phẩm hay không
                                for (Goods g : ListG){
                                    if (g.getID().equals(chonID)){
                                        System.out.print("Nhập số lượng nhập vào: ");
                                        g.setRemainingProduct(g.getRemainingProduct() + Integer.parseInt(sc.nextLine()));
                                        check = true;
                                        break;
                                    }
                                }
                                if (check == false) System.out.println("ID bạn nhập không tìm thấy!");
                                chon = 0;
                                System.out.println();
                                break;
                            case 5:
                                break;
                        }
                    }while (chon != 5);
                    chon = 0;
                    break;
                case 4: // Lập Bill
                    Bill(ListC,ListS,ListG);
                    break;
                case 5:
                    System.out.println("Hoàn thành tra cứu!!!");
                    break;
            }
        } while (chon != 5);

        try { //Viết ra file
            FileOutputStream oC = new FileOutputStream(customerData);
            FileOutputStream oS = new FileOutputStream(staffData);
            FileOutputStream oG = new FileOutputStream(goodsData);

            ObjectOutputStream ooC = new ObjectOutputStream(oC);
            ObjectOutputStream ooS = new ObjectOutputStream(oS);
            ObjectOutputStream ooG = new ObjectOutputStream(oG);

            ooC.writeObject(ListC);

            ooS.writeObject(ListS);

            ooG.writeObject(ListG);

            ooC.close();
            ooS.close();
            ooG.close();
        } catch (Exception e){System.out.println(e);}
    }
}
