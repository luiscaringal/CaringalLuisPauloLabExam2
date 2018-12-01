package com.caringal.luispaulo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.IntBuffer;

public class MainActivity extends AppCompatActivity {
    TextView tFirstName, tLastName, tExam1, tExam2, tResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tFirstName = findViewById(R.id.fname);
        tLastName = findViewById(R.id.lname);
        tExam1 = findViewById(R.id.exam1);
        tExam2 = findViewById(R.id.exam2);
        tResult = findViewById(R.id.result);
    }

    public void getAve(View v){
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(folder, "ExamAverageExternal.txt");
        FileOutputStream fos = null;
        FileInputStream fin = null;

        String fname = tFirstName.getText().toString();
        String lname = tLastName.getText().toString();
        int exam1 = Integer.parseInt(tExam1.getText().toString());
        int exam2 = Integer.parseInt(tExam2.getText().toString());
        int ave = (exam1 + exam2)/2;
        String average = String.valueOf(ave);

        String statement = "Your average is ";
        int result;
        StringBuffer buffer = new StringBuffer();

        try{
            fos = new FileOutputStream(file);
            fos.write(fname.getBytes());
            fos.write(lname.getBytes());
            fos.write(average.getBytes());
            fos.close();
            fin = new FileInputStream(file);
            buffer.append(statement);
            while((result = fin.read())!= -1){
                buffer.append((char)result);
            }
            tResult.setText(buffer.toString());
            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
        }
        catch(Exception e){
            Toast.makeText(this, "Unsuccessful", Toast.LENGTH_LONG).show();
        }
    }
}
