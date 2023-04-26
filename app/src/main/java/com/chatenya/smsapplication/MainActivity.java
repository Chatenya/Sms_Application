package com.chatenya.smsapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txt_phone, txt_msg;
    Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_phone = (EditText)findViewById(R.id.txt_phone);
        txt_msg = (EditText)findViewById(R.id.txt_msg);
        btn_send = findViewById(R.id.btn_send);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking condition for permission
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                    //when permission is granted
                    // create a method
                    sendSMS();
                }
                else{
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS}, 0);
                }
            }
        });
    }


    private void sendSMS() {
        //get value from edittext
        String phoneNumber = txt_phone.getText().toString();
        String Message = txt_msg.getText().toString();
        //check condition if string is empty or not
        if(!phoneNumber.isEmpty() && !Message.isEmpty()){

            //initialize SMS manager

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, Message, null, null);

            //toast foe sending msg

            Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please Enter Number or Message", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // check condition
                if(requestCode==0 && grantResults.length >  0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            //permission granted
                    sendSMS();

                } else{
                    Toast.makeText(this, "You don't have Required permission", Toast.LENGTH_SHORT).show();
                }

    }
}