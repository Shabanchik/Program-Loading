package com.example.pupil.programfiles;


public class Bank {
    private int money;
    private String login;
    private long password;
    Bank(int money, String login, long password){
        super();
        this.money=money;
        this.login=login;
        this.password=password;
    }
    synchronized void takeMoney(String login,long password,int sum){
        if(!checkPassAndLogin(login,password)){
            System.out.println("Wrong login or password");
        }
        if(!checkMoney(sum)){
            System.out.println("You don't have money");
            return;
        }

        transaction(); changeBalance(sum);
        System.out.println(this);
    }
    private boolean checkPassAndLogin(String login,long password){
        return(login.equals(this.login)&&this.password==password);
    }
    private  boolean checkMoney(int money){ return this.money>=money;}//вводиться деньги сколько хотим снять, проверяется если сколько мы снимаем  больше чем сколько мы имеем то ложь
    private void transaction(){//имитируем долгое время снятия, искуственносоздаем длинную операцию
        try{
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    @Override
    public String toString() {
       return "Acount[money="+ money +"]";
    }

    private void changeBalance(int money){ this.money-=money;}//меняем баланс на карте так как он списался отнимаем от старого баланса деньги которые мы списали
}
