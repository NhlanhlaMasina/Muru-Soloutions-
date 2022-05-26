package com.example.workintergrated;

public class EnquireData {

    String name;
    String surname;
    String number;
    String email;
    String addressLine1;
    String addressLine2;
    String addressLine3;

    public EnquireData(String name, String surname, String number, String email, String addressLine1, String addressLine2, String addressLine3) {
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.email = email;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }
}
