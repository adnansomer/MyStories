package com.adnansomer.mystories;

import com.adnansomer.mystories.WebServiceModel.Story;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestInterface {
    @GET("prod")
    Call<List<Story>> getStory();
    
}
