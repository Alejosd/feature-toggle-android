package com.tumblr.alejosd5.featuretoggle.rest;

import com.tumblr.alejosd5.featuretoggle.api.IMessage;

import java.util.List;

import models.Message;
import models.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by asierrdu on 16-08-2016.
 */
public class MessageRest {

    private String API;
    private Retrofit retrofit;

    public MessageRest(String API){
        this.API = API;
        retrofit = new Retrofit.Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<Message> message(User user){

        IMessage service = retrofit.create(IMessage.class);
        return service.message(user.getUsername(),user.getNumberPhone());

    }



}
