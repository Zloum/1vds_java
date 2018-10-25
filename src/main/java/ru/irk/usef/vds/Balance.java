package ru.irk.usef.vds;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Balance {
    private int count;
    private int mustbe;
    private int arrive;
    private boolean alert;

    public Balance() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMustbe() {
        return mustbe;
    }

    public void setMustbe(int mustbe) {
        this.mustbe = mustbe;
    }

    public int getArrive() {
        return arrive;
    }

    public void setArrive(int arrive) {
        this.arrive = arrive;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "count=" + count +
                ", mustbe=" + mustbe +
                ", arrive=" + arrive +
                ", alert=" + alert +
                '}';
    }
}
