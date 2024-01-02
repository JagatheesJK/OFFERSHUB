package com.hub.offershub.viewmodel;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.hub.offershub.AppApplication;
import com.hub.offershub.PrefsHelper;
import com.hub.offershub.model.AddOfferDataRequestBody;
import com.hub.offershub.model.AddShopDataRequestBody;
import com.hub.offershub.model.Amenity;
import com.hub.offershub.model.BookModel;
import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.model.CategoryResponse;
import com.hub.offershub.model.OfferModel;
import com.hub.offershub.model.RatingModel;
import com.hub.offershub.retrofit.API;
import com.hub.offershub.retrofit.RetrofitClient;
import com.hub.offershub.utils.loading.MyProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonViewModel extends AndroidViewModel {

    public MyProgressDialog myProgressDialog;

    private final MutableLiveData<BusinessModel> mutableActiveBusiness = new MutableLiveData<>();
    private final MutableLiveData<BusinessModel> mutableInActiveBusiness = new MutableLiveData<>();
    private final MutableLiveData<OfferModel> mutableActiveOffers = new MutableLiveData<>();
    private final MutableLiveData<OfferModel> mutableInActiveOffers = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableaddShop = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableAddOffer = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableDeleteShop = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableDeleteShopPermanent = new MutableLiveData<>();
    private final MutableLiveData<Amenity> mutableAmenity = new MutableLiveData<>();
    private final MutableLiveData<CategoryResponse> mutableCategory = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableDeleteOffer = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableDeleteOfferPermanent = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableUpdateShopDetails = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableUpdateShopImages = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableUpdateOfferDetails = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableUpdateOfferImages = new MutableLiveData<>();
    private final MutableLiveData<RatingModel> mutableRatingData = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableRatingReplyData = new MutableLiveData<>();
    private final MutableLiveData<BookModel> mutableBookingData = new MutableLiveData<>();

    public CommonViewModel(@NonNull Application application) {
        super(application);
        myProgressDialog = new MyProgressDialog(AppApplication.getInstance());
    }

    public void getActiveShops(Map<String, Object> requestData) {
        Log.e("Check_JK", "getActiveShops");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.e("Check_JKUpdate", "getActiveShops onPreExecute");
                showDialog();
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<BusinessModel> call = apiInterface.getActiveShops(requestData);
                call.enqueue(new Callback<BusinessModel>() {
                    @Override
                    public void onResponse(@NonNull Call<BusinessModel> call, @NonNull Response<BusinessModel> response) {
                        Log.e("Check_JK", "getActiveShops onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            mutableActiveBusiness.postValue(response.body());
                        } else
                            mutableActiveBusiness.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<BusinessModel> call, @NonNull Throwable t) {
                        Log.e("Check_JK", "getActiveShops Error Message : " + t.getMessage());
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
        Log.e("Check_JK", "getInActiveShops");
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
                        Log.e("Check_JK", "getInActiveShops onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            mutableInActiveBusiness.postValue(response.body());
                        } else
                            mutableInActiveBusiness.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<BusinessModel> call, @NonNull Throwable t) {
                        Log.e("Check_JK", "getInActiveShops Error Message : " + t.getMessage());
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

    public void addOffer(AddOfferDataRequestBody addOfferDataRequestBody, MultipartBody.Part multipartBody) {
        Log.e("Check_JK", "addOffer Type : "+addOfferDataRequestBody.offer_type);
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog();
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.addOffer(multipartBody,
                        RequestBody.create(MediaType.parse("multipart/form-data"), ""+addOfferDataRequestBody.shop_id),
                        RequestBody.create(MediaType.parse("multipart/form-data"), ""+addOfferDataRequestBody.offer_name),
                        RequestBody.create(MediaType.parse("multipart/form-data"), ""+addOfferDataRequestBody.offer_desc),
                        RequestBody.create(MediaType.parse("multipart/form-data"), ""+addOfferDataRequestBody.offer_type),
                        RequestBody.create(MediaType.parse("multipart/form-data"), ""+addOfferDataRequestBody.amount),
                        RequestBody.create(MediaType.parse("multipart/form-data"), ""+addOfferDataRequestBody.original_amount),
                        RequestBody.create(MediaType.parse("multipart/form-data"), ""+addOfferDataRequestBody.offer_amount),
                        RequestBody.create(MediaType.parse("multipart/form-data"), ""+addOfferDataRequestBody.flat_percentage)
                );
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        Log.e("Check_JK", "addOffer onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            try {
                                JSONObject root = new JSONObject(response.body().toString());
                                mutableAddOffer.postValue(root);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                        } else
                            mutableAddOffer.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                        Log.e("Check_JK", "addOffer Error Message : " + t.getMessage());
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
        Log.e("Check_JK", "getInActiveOffers ID : "+requestData.get("shop_id"));
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
                        Log.e("Check_JK", "getInActiveOffers onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            mutableInActiveOffers.postValue(response.body());
                        } else
                            mutableInActiveOffers.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<OfferModel> call, @NonNull Throwable t) {
                        Log.e("Check_JK", "getInActiveOffers Error Message : " + t.getMessage());
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

    public void getAddShop(AddShopDataRequestBody addShopDataRequestBody, MultipartBody.Part multipartBody) {
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog();
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                List<MultipartBody.Part> arrayOfParts = new ArrayList<>();
                for (int i = 0; i < addShopDataRequestBody.shopamenities.size(); i++) {
                    String partName = "shopamenities[" + i + "]";
                    String partValue = String.valueOf(addShopDataRequestBody.shopamenities.get(i));
                    arrayOfParts.add(MultipartBody.Part.createFormData(partName, partValue));
                }
                Call<JsonElement> call = apiInterface.addShops(multipartBody,
                        RequestBody.create(MediaType.parse("multipart/form-data"), ""+addShopDataRequestBody.shopownerid),
                        RequestBody.create(MediaType.parse("multipart/form-data"), ""+addShopDataRequestBody.shopname),
                        RequestBody.create(MediaType.parse("multipart/form-data"), ""+addShopDataRequestBody.mobile),
                        RequestBody.create(MediaType.parse("multipart/form-data"), ""+addShopDataRequestBody.upi),
                        arrayOfParts,
                        RequestBody.create(MediaType.parse("multipart/form-data"), ""+addShopDataRequestBody.address1),
                        RequestBody.create(MediaType.parse("multipart/form-data"), ""+addShopDataRequestBody.address2),
                        RequestBody.create(MediaType.parse("multipart/form-data"), ""+addShopDataRequestBody.city),
                        RequestBody.create(MediaType.parse("multipart/form-data"), ""+addShopDataRequestBody.state),
                        RequestBody.create(MediaType.parse("multipart/form-data"), ""+addShopDataRequestBody.pincode),
                        RequestBody.create(MediaType.parse("multipart/form-data"), ""+addShopDataRequestBody.categoryid),
                        RequestBody.create(MediaType.parse("multipart/form-data"), ""+addShopDataRequestBody.latitude),
                        RequestBody.create(MediaType.parse("multipart/form-data"), ""+addShopDataRequestBody.longitude)
                        );
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        if (response.body() != null) {
                            try {
                                JSONObject root = new JSONObject(response.body().toString());
                                mutableaddShop.postValue(root);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                        } else
                            mutableaddShop.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
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

    public void getDeleteShop(Map<String, Object> requestData) {
        Log.e("Check_JK", "getDeleteShop ID : "+requestData.get("shop_id"));
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog();
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.deleteShop(requestData);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        Log.e("Check_JK", "getDeleteShop onResponse : "+response.body().toString());
                        if (response.body() != null) {
                            try {
                                JSONObject root = new JSONObject(response.body().toString());
                                mutableDeleteShop.postValue(root);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        } else
                            mutableDeleteShop.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                        Log.e("Check_JK", "getDeleteShop Error Message : " + t.getMessage());
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

    public void deleteShopPermanent(Map<String, Object> requestData) {
        Log.e("Check_JK", "getDeleteShop ID : "+requestData.get("shop_id"));
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog();
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.deleteShopPermanent(requestData);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        Log.e("Check_JK", "getDeleteShop onResponse : "+response.body().toString());
                        if (response.body() != null) {
                            try {
                                JSONObject root = new JSONObject(response.body().toString());
                                mutableDeleteShopPermanent.postValue(root);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        } else
                            mutableDeleteShopPermanent.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                        Log.e("Check_JK", "getDeleteShop Error Message : " + t.getMessage());
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

    public void getMasterAmenities() {
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog();
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<Amenity> call = apiInterface.getMasterAmenities();
                call.enqueue(new Callback<Amenity>() {
                    @Override
                    public void onResponse(@NonNull Call<Amenity> call, @NonNull Response<Amenity> response) {
                        if (response.body() != null) {
                            mutableAmenity.postValue(response.body());
                        } else
                            mutableAmenity.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Amenity> call, @NonNull Throwable t) {
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

    public void getDeleteOffer(Map<String, Object> requestData) {
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog();
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.deleteOffer(requestData);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        if (response.body() != null) {
                            try {
                                JSONObject root = new JSONObject(response.body().toString());
                                mutableDeleteOffer.postValue(root);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        } else
                            mutableDeleteOffer.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
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

    public void deleteOfferPermanent(Map<String, Object> requestData) {
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog();
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.deleteOfferPermanent(requestData);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        if (response.body() != null) {
                            try {
                                JSONObject root = new JSONObject(response.body().toString());
                                mutableDeleteOfferPermanent.postValue(root);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        } else
                            mutableDeleteOfferPermanent.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
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

    public void getCategory(PrefsHelper prefsHelper) {
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog();
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<CategoryResponse> call = apiInterface.getCategory();
                call.enqueue(new Callback<CategoryResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<CategoryResponse> call, @NonNull Response<CategoryResponse> response) {
                        if (response.body() != null) {
                            prefsHelper.saveSettings(PrefsHelper.CATEGORY, response.body().getData());
                            mutableCategory.postValue(response.body());
                        } else
                            mutableCategory.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<CategoryResponse> call, @NonNull Throwable t) {
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

    public void updateShopDetails(Map<String, Object> requestData) {
        Log.e("Check_JKUpdate", "updateDetails");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog();
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.updateShopsDetails(requestData);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        Log.e("Check_JKUpdate", "updateDetails onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            try {
                                JSONObject root = new JSONObject(response.body().toString());
                                mutableUpdateShopDetails.postValue(root);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        } else
                            mutableUpdateShopDetails.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                        Log.e("Check_JKUpdate", "updateDetails Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void updateShopImages(MultipartBody.Part filePart, RequestBody requestBody) {
        Log.e("Check_JKUpdate", "updateShopImages");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog();
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.updateShopImages(filePart, requestBody);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        Log.e("Check_JKUpdate", "updateShopImages onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            try {
                                JSONObject root = new JSONObject(response.body().toString());
                                mutableUpdateShopImages.postValue(root);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        } else
                            mutableUpdateShopImages.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                        Log.e("Check_JKUpdate", "updateShopImages Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void updateOfferDetails(Map<String, Object> requestData) {
        Log.e("Check_JKUpdate", "updateOfferDetails");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog();
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.updateOfferDetails(requestData);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        Log.e("Check_JKUpdate", "updateOfferDetails onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            try {
                                JSONObject root = new JSONObject(response.body().toString());
                                mutableUpdateOfferDetails.postValue(root);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        } else
                            mutableUpdateOfferDetails.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                        Log.e("Check_JKUpdate", "updateOfferDetails Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void updateOfferImages(MultipartBody.Part filePart, RequestBody requestBody) {
        Log.e("Check_JKUpdate", "updateOfferImages");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog();
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.updateOfferImages(filePart, requestBody);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        Log.e("Check_JKUpdate", "updateOfferImages onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            try {
                                JSONObject root = new JSONObject(response.body().toString());
                                mutableUpdateOfferImages.postValue(root);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        } else
                            mutableUpdateOfferImages.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                        Log.e("Check_JKUpdate", "updateOfferImages Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void getRatingReview(Map<String, Object> requestData) {
        Log.e("Check_JKUpdate", "getRatingReview");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog();
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<RatingModel> call = apiInterface.getRatingReview(requestData);
                call.enqueue(new Callback<RatingModel>() {
                    @Override
                    public void onResponse(@NonNull Call<RatingModel> call, @NonNull Response<RatingModel> response) {
                        Log.e("Check_JKUpdate", "getRatingReview onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            mutableRatingData.postValue(response.body());
                        } else
                            mutableRatingData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<RatingModel> call, @NonNull Throwable t) {
                        Log.e("Check_JKUpdate", "getRatingReview Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void shopRatingReply(Map<String, Object> requestData) {
        Log.e("Check_JKUpdate", "shopRatingReply");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog();
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.shopRatingReply(requestData);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        Log.e("Check_JKUpdate", "shopRatingReply onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            try {
                                JSONObject object = new JSONObject(response.body().toString());
                                mutableRatingReplyData.postValue(object);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else
                            mutableRatingReplyData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                        Log.e("Check_JKUpdate", "shopRatingReply Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void getOrderDetailsShops(Map<String, Object> requestData) {
        Log.e("Check_JKUpdate", "getOrderDetailsShops");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog();
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<BookModel> call = apiInterface.getOrderDetailsShops(requestData);
                call.enqueue(new Callback<BookModel>() {
                    @Override
                    public void onResponse(@NonNull Call<BookModel> call, @NonNull Response<BookModel> response) {
                        Log.e("Check_JKUpdate", "getOrderDetailsShops onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            mutableBookingData.postValue(response.body());
                        } else
                            mutableBookingData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<BookModel> call, @NonNull Throwable t) {
                        Log.e("Check_JKUpdate", "getOrderDetailsShops Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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

    public MutableLiveData<Amenity> getMutableAmenity() {
        return mutableAmenity;
    }

    public MutableLiveData<JSONObject> getMutableAddShop() {
        return mutableaddShop;
    }

    public MutableLiveData<JSONObject> getMutableDeleteShop() {
        return mutableDeleteShop;
    }

    public MutableLiveData<JSONObject> getMutableDeleteShopPermanent() {
        return mutableDeleteShopPermanent;
    }

    public MutableLiveData<OfferModel> getMutableInActiveOffers() {
        return mutableInActiveOffers;
    }

    public MutableLiveData<CategoryResponse> getMutableCategory() {
        return mutableCategory;
    }

    public MutableLiveData<JSONObject> getMutableDeleteOffer() {
        return mutableDeleteOffer;
    }

    public MutableLiveData<JSONObject> getMutableDeleteOfferPermanent() {
        return mutableDeleteOfferPermanent;
    }

    public MutableLiveData<JSONObject> getMutableAddOffer() {
        return mutableAddOffer;
    }

    public MutableLiveData<JSONObject> getMutableUpdateShopDetails() {
        return mutableUpdateShopDetails;
    }

    public MutableLiveData<JSONObject> getMutableUpdateShopImages() {
        return mutableUpdateShopImages;
    }

    public MutableLiveData<JSONObject> getMutableUpdateOfferDetails() {
        return mutableUpdateOfferDetails;
    }

    public MutableLiveData<JSONObject> getMutableUpdateOfferImages() {
        return mutableUpdateOfferImages;
    }

    public MutableLiveData<RatingModel> getMutableRatingData() {
        return mutableRatingData;
    }

    public MutableLiveData<JSONObject> getMutableRatingReplyData() {
        return mutableRatingReplyData;
    }

    public MutableLiveData<BookModel> getMutableBookingData() {
        return mutableBookingData;
    }

    public void showDialog() {
        try {
            if (myProgressDialog != null && !myProgressDialog.isShowing())
                myProgressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Check_JKUpdate", "showDialog Error : "+e.getMessage());
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
