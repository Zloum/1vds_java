package ru.irk.usef.vds;

public class Spare {
    private String name;
    private Balance balance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Spare{" +
                "name='" + name + '\'' +
                ", balance=" + balance.toString() +
                '}';
    }
}
