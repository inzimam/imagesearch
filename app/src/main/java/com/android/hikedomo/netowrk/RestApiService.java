package com.android.hikedomo.netowrk;

import com.android.hikedomo.entity.MainResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface RestApiService {

    @GET("services/rest?")
    Call<MainResponse> getImageList(@QueryMap Map<String, String> options);
}