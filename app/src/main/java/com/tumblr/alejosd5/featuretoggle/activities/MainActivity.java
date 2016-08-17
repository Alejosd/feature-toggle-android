package com.tumblr.alejosd5.featuretoggle.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tumblr.alejosd5.featuretoggle.R;
import com.tumblr.alejosd5.featuretoggle.rest.MessageRest;
import java.util.List;

import models.Message;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

String API = "https://featuretogglejava.herokuapp.com";
    public void sendMessage(View view) {
        getMessages();
    }


    public void getMessages(){

        MessageRest messageRest = new MessageRest(API);
        Call<Message> call = messageRest.message();
        call.enqueue(new Callback<Message>() {
                         @Override
                         public void onResponse(Call<Message> call, Response<Message> response) {
                             Message messages = response.body();
                             String message = messages.getMessage();
                             messageView(message);
                         }

                         @Override
                         public void onFailure(Call<Message> call, Throwable t) {
                             Log.e("++++ERROR SERVICIO++++", t.getMessage());
                         }

                     }
        );
    }

    private void messageView(String message) {

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, message, duration).show();
    }
}
