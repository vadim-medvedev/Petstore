package com.example.demo.model;

public enum PetStatus {
    AVAILABLE, PENDING, SOLD;

    public static boolean isExist(String inputStatus){
        for (PetStatus value : PetStatus.values()) {
            if (value.toString().equalsIgnoreCase(inputStatus)){
                return true;
            }
        }
        return false;
    }
}
