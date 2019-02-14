package com.example.pupil.programfiles;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ProgressBar progress;
    Button btnOk;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOk = findViewById(R.id.btnOk);
        progress = findViewById(R.id.progress);
        progress.setMax(100);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProgress();
                getMoney();
            }
        });

        try {
            writeToFile(createFile(getFilesDir().getAbsolutePath() + "Temp.txt"));
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    private void getMoney() {
        Bank account = new Bank(200, "login", 123);//оздем аккаунт
        SingleThreat stOne = new SingleThreat(account);
        SingleThreat stTwo = new SingleThreat(account);//создаем два потока
        stOne.getMoneyFromAccount("login", 123, 150);//снимаем 150
        stTwo.getMoneyFromAccount("login", 123, 150);// снимаем 150, при том что на аккаунте 200

    }//наша программа с аккаунтом

    private void iskluchenia() {
        int a = 10;
        int b = 0;
        try {
            int c = a / b;
        } catch (ArithmeticException e) {
            Log.e("Error", e.getLocalizedMessage());
        } finally {
            Log.d("App", "CLOSE");
        }
    }


    private void startProgress() {
        final int[] p = {0};
        progress.setProgress(p[0]);
        CountDownTimer countDownTimer = new CountDownTimer(10000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                p[0] += 1;
                progress.setProgress(p[0]);

            }

            @Override
            public void onFinish() {
                progress.setProgress(100);
            }
        }.start();
    }

    private File createFile(String path) throws IOException {
        File file = new File(path);
        if (!checkFile(file)) createNewFile(file);
        return file;
    }

    private boolean checkFile(File path) {
        return file.exists();
    }

    private boolean createNewFile(File file) throws IOException {
        return file.createNewFile();
    }

    private void writeToFile(File file) {
        String str = "Hello world";
        byte[] mass = str.getBytes();
        FileOutputStream steam = null;
        try {
            steam = new FileOutputStream(file.getAbsolutePath());
            steam.write(mass);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (steam != null) {
                    steam.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String readFile() {
        StringBuilder str = new StringBuilder();
        BufferedReader reader=null;
        try {
            reader = new BufferedReader((new FileReader(file)));//заносим в поток файл,преобразует файл, читаем пото опускаемся на следующую строку и читаем
            String line;
            while ((line = reader.readLine()) != null) {
                str.append(line);
                System.out.println("Text test : " + str + " : end");
                str.append("/n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }finally {
            try{
                if(reader!=null){//закрываем риадер
                    reader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }


        return str.toString();//приравниваем риадер ту стринг, возвращаем стрингу
    }
}

