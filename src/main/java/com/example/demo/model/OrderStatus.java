package com.example.demo.model;

public enum OrderStatus {
    PLACED, APPROVED, DELIVERED;

    public static boolean isExist(String inputStatus){
        for (OrderStatus value : OrderStatus.values()) {
            if (value.toString().equalsIgnoreCase(inputStatus)){
                return true;
            }
        }
        return false;
    }
}
