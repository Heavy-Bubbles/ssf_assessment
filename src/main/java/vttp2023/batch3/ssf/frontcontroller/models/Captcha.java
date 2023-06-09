package vttp2023.batch3.ssf.frontcontroller.models;

import java.io.Serializable;
import java.util.Random;

public class Captcha implements Serializable{

    private int firstRandomNumber;
    private int secondRandomNumber;
    private int total;
    
    public int getFirstRandomNumber() {
        return firstRandomNumber;
    }
    public void setFirstRandomNumber(int firstRandomNumber) {
        this.firstRandomNumber = firstRandomNumber;
    }
    public int getSecondRandomNumber() {
        return secondRandomNumber;
    }
    public void setSecondRandomNumber(int secondRandomNumber) {
        this.secondRandomNumber = secondRandomNumber;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public Captcha() {
        Random random = new Random();
        this.firstRandomNumber = random.nextInt(50 + 1 - 1) + 1;
        this.secondRandomNumber = random.nextInt(50 + 1 - 1) + 1;
        this.total = firstRandomNumber + secondRandomNumber;
    }

    
    
}
