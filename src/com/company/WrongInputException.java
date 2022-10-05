package com.company;

import java.util.Objects;

public class WrongInputException extends Exception{
    String wr;
    WrongInputException (String input) {
        wr = input;
    }
    public String getMessage() {
        if (Objects.equals(wr, "WrongNumberRequest")) { // lỗi nhập khác số yêu cầu
            return "Vui lòng nhập từ 1 đến 5!";
        }
        else
            return "Error";
    }
}
