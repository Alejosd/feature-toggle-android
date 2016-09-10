package com.tumblr.alejosd5.featuretoggle.activities;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.tumblr.alejosd5.featuretoggle.R;
import com.tumblr.alejosd5.featuretoggle.rest.MessageRest;

import java.util.ArrayList;
import java.util.List;

import models.Message;
import models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final static String API = "https://featuretogglejava.herokuapp.com";
    private List<User> users;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        user = new User("","");
        users = new ArrayList<>();

        User user1 = new User("alejosd5","1");
        User user2 = new User("Alvaro","2");
        User user3 = new User("Sergio","3");
        User user4 = new User("Marcelo","4");
        User user5 = new User("Pedro","5");
        User user6 = new User("1010101010","6");
        User user7 = new User("Jorge","7");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);

        setListUsers(users);
        updateUser(users);
    }

    public ListView setListUsers(List<User> users) {

        ArrayList<String> list = new ArrayList<>();

        for (User user : users) {
            list.add(user.getUsername()+","+user.getNumberPhone());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);

        ListView listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(adapter);

        return listView;
    }

    public void updateUser(List<User> users){

        ListView listView = setListUsers(users);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String username = String.valueOf(parent.getItemAtPosition(position));
                Log.e("ACTUALIZADO", username);
                TextView text = (TextView) findViewById(R.id.optionText);
                text.setText(username);
                String [] list = username.split(",");
                user.setUsername(list[0]);
                user.setNumberPhone(list[1]);

            }
        });
    }


    public void sendMessage(View view) {
        getMessages();
    }


    public void getMessages() {

        MessageRest messageRest = new MessageRest(API);
        Call<Message> call = messageRest.message(user);
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
