package com.example.testv2;

public class Cookie {
    private double p;
    private String n;
    private int a;
    public Cookie(String name, double price, int amount){
        n = name;
        p = price;
        a = amount;
    }

    public void add(int amount){
        a+=amount;
    }
    public void subtract(int amount){
        a-=amount;
    }
    public double getPrice(){
        p*=100;
        p = Math.floor(p);
        p/=100;

        return p;
    }
    public void changePrice(){
        boolean x = (int) Math.floor(Math.random() * (1 + 1)) == 1;
        if (x){
            p*=1.05;
        }
        else{
            p*=.95;
        }
        p*=100;
        p = (int) p;
        p/=100;
    }
    public int getAmount(){
        return a;
    }
}
