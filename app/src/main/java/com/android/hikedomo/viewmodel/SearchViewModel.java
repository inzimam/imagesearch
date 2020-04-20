package com.android.hikedomo.viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.hikedomo.entity.MainResponse;
import com.android.hikedomo.netowrk.RestApiService;
import com.android.hikedomo.utils.Logs;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchViewModel extends ViewModel {
    private String TAG = SearchViewModel.class.getSimpleName();

    private MutableLiveData<MainResponse> mutableLiveData;

    public MutableLiveData<MainResponse> getImageList(Retrofit retrofit, Map<String, String> options) {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            RestApiService restApiService = retrofit.create(RestApiService.class);

            Call<MainResponse> call = restApiService.getImageList(options);
            call.enqueue(new Callback<MainResponse>() {
                @Override
                public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                    mutableLiveData.setValue(response.body());
                }

                @Override
                public void onFailure(Call<MainResponse> call, Throwable t) {
                    Logs.v(TAG, t.getMessage());
                    mutableLiveData.setValue(null);
                }
            });
        }
        return mutableLiveData;
    }

    public void setNull() {
        mutableLiveData = null;
    }
}
