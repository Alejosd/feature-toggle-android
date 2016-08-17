package com.tumblr.alejosd5.featuretoggle.api;

import java.util.List;

import models.Message;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by asierrdu on 16-08-2016.
 */
public interface IMessage {

    @GET("/")
    Call<Message> message();
}
