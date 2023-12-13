package com.hub.offershub.viewmodel;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.model.OfferModel;
import com.hub.offershub.retrofit.API;
import com.hub.offershub.retrofit.RetrofitClient;
import com.hub.offershub.utils.loading.MyProgressDialog;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonViewModel extends AndroidViewModel {

    public MyProgressDialog myProgressDialog;

    private final MutableLiveData<BusinessModel> mutableActiveBusiness = new MutableLiveData<>();
    private final MutableLiveData<BusinessModel> mutableInActiveBusiness = new MutableLiveData<>();
    private final MutableLiveData<OfferModel> mutableActiveOffers = new MutableLiveData<>();
    private final MutableLiveData<OfferModel> mutableInActiveOffers = new MutableLiveData<>();

    public CommonViewModel(@NonNull Application application) {
        super(application);
        myProgressDialog = new MyProgressDialog(application.getApplicationContext());
    }

    public void getActiveShops(Map<String, Object> requestData) {
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog();
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<BusinessModel> call = apiInterface.getActiveShops(requestData);
                call.enqueue(new Callback<BusinessModel>() {
                    @Override
                    public void onResponse(@NonNull Call<BusinessModel> call, @NonNull Response<BusinessModel> response) {
                        if (response.body() != null) {
                            mutableActiveBusiness.postValue(response.body());
                        } else
                            mutableActiveBusiness.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<BusinessModel> call, @NonNull Throwable t) {
                        Log.e("TAG", "settingsData Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog();
            }
        }.execute();

    }

    public void getInActiveShops(Map<String, Object> requestData) {
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog();
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<BusinessModel> call = apiInterface.getInactiveShops(requestData);
                call.enqueue(new Callback<BusinessModel>() {
                    @Override
                    public void onResponse(@NonNull Call<BusinessModel> call, @NonNull Response<BusinessModel> response) {
                        if (response.body() != null) {
                            mutableInActiveBusiness.postValue(response.body());
                        } else
                            mutableInActiveBusiness.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<BusinessModel> call, @NonNull Throwable t) {
                        Log.e("TAG", "settingsData Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog();
            }
        }.execute();

    }

    public void getActiveOffers(Map<String, Object> requestData) {
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog();
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<OfferModel> call = apiInterface.getActiveOffers(requestData);
                call.enqueue(new Callback<OfferModel>() {
                    @Override
                    public void onResponse(@NonNull Call<OfferModel> call, @NonNull Response<OfferModel> response) {
                        if (response.body() != null) {
                            mutableActiveOffers.postValue(response.body());
                        } else
                            mutableActiveOffers.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<OfferModel> call, @NonNull Throwable t) {
                        Log.e("TAG", "settingsData Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog();
            }
        }.execute();

    }

    public void getInActiveOffers(Map<String, Object> requestData) {
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog();
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<OfferModel> call = apiInterface.getInActiveOffers(requestData);
                call.enqueue(new Callback<OfferModel>() {
                    @Override
                    public void onResponse(@NonNull Call<OfferModel> call, @NonNull Response<OfferModel> response) {
                        if (response.body() != null) {
                            mutableInActiveOffers.postValue(response.body());
                        } else
                            mutableInActiveOffers.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<OfferModel> call, @NonNull Throwable t) {
                        Log.e("TAG", "settingsData Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog();
            }
        }.execute();

    }

    public MutableLiveData<BusinessModel> getMutableActiveBusiness() {
        return mutableActiveBusiness;
    }

    public MutableLiveData<BusinessModel> getMutableInActiveBusiness() {
        return mutableInActiveBusiness;
    }

    public MutableLiveData<OfferModel> getMutableActiveOffers() {
        return mutableActiveOffers;
    }

    public MutableLiveData<OfferModel> getMutableInActiveOffers() {
        return mutableInActiveOffers;
    }

    public void showDialog() {
        try {
            if (myProgressDialog != null && !myProgressDialog.isShowing())
                myProgressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeDialog() {
        try {
            if (myProgressDialog != null && myProgressDialog.isShowing())
                myProgressDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
