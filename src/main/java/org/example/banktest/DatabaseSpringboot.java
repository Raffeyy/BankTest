package org.example.banktest;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DatabaseSpringboot {

    @Id
    private String username;
    private String passwort;
    private double balance = 0.0;
    private int iban;

    public DatabaseSpringboot() {
    }


    public DatabaseSpringboot(String username, String passwort) {
        this.username = username;
        this.passwort = passwort;
        this.balance = balance;
        this.iban = iban;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getIban() {
        return iban;
    }

    public void setIban(int iban) {
        this.iban = iban;
    }


}

