package com.example.pupil.programfiles;



public class SingleThreat implements Runnable {//все что происходит в аккаунте мы передаем в отдельный поток, каждые снятия будут в отдельном потоке
    private Bank account;//передаем данные, создаем поок, в который выполняем his
    private String login;
    private long password;
    private int sum;
    public SingleThreat(Bank account){//конструктор в который передаем аккаунт
        super();
        this.account=account;
    }
    public void getMoneyFromAccount(String login,long password,int sum){
        this.password=password;
        this.login=login;
        this.sum=sum;
        Thread thread = new Thead(this);
        thread.start();
    }
    public void run(){ account.takeMoney(this.login,this.password,this.sum);}


}

