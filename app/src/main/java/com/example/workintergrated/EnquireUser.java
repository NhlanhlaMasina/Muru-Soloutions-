package com.example.workintergrated;

public class EnquireUser {

    String Name;
    String Surname;
    String Email;
    String Number;
    String Message;

    public EnquireUser()
    {

    }

    public EnquireUser( String Name, String Surname, String Email, String Number, String Message) {

        this.Name = Name;
        this.Surname = Surname;
        this.Email = Email;
        this.Number = Number;
        this.Message = Message;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getEmail() {
        return Email;
    }

    public String getNumber() {
        return Number;
    }

    public String getMessage() {
        return Message;
    }
}
