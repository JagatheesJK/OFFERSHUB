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
import com.hub.offershub.model.AdminOfferImageModel;
import com.hub.offershub.model.AdminOfferModel;
import com.hub.offershub.model.AdminShopImageModel;
import com.hub.offershub.model.AdminShopModel;
import com.hub.offershub.model.Amenity;
import com.hub.offershub.model.BookModel;
import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.model.CategoryResponse;
import com.hub.offershub.model.FeedbackModel;
import com.hub.offershub.model.OfferDashboardModel;
import com.hub.offershub.model.OfferImageModel;
import com.hub.offershub.model.OfferModel;
import com.hub.offershub.model.RatingModel;
import com.hub.offershub.model.SettingModel;
import com.hub.offershub.model.ShopDashboardModel;
import com.hub.offershub.model.SubscriptionPackageResponse;
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
    private final MutableLiveData<JSONObject> mutableDeleteOfferImages = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableDeleteShopImages = new MutableLiveData<>();
    private final MutableLiveData<RatingModel> mutableRatingData = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableRatingReplyData = new MutableLiveData<>();
    private final MutableLiveData<BookModel> mutableBookingData = new MutableLiveData<>();
    private final MutableLiveData<FeedbackModel> mutableShopFeedbackData = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableAddFeedbackData = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableUpdateShopLocationData = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableUpdateShopAmenitiesData = new MutableLiveData<>();
    private final MutableLiveData<OfferImageModel> mutableGetOfferImageData = new MutableLiveData<>();
    private final MutableLiveData<OfferImageModel> mutableGetShopImageData = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableOfferPriorityData = new MutableLiveData<>();

    private final MutableLiveData<JSONObject> mutableOrderDetails_shopsVisitData = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableorderDetails_mobilenumber_ViewData = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableOrderDetails_shopConfirmStatusData = new MutableLiveData<>();
    private final MutableLiveData<ShopDashboardModel> mutableShopDashData = new MutableLiveData<>();
    private final MutableLiveData<OfferDashboardModel> mutableOfferDashData = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableLoginCheck = new MutableLiveData<>();
    private final MutableLiveData<SubscriptionPackageResponse> mutableSubscriptionData = new MutableLiveData<>();
    private final MutableLiveData<BookModel> mutableNotifyData = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutablePaymentSuccess = new MutableLiveData<>();
    private final MutableLiveData<SettingModel> mutableSettingData = new MutableLiveData<>();

    // Admin
    private final MutableLiveData<AdminShopModel> mutableAdminShopData = new MutableLiveData<>();
    private final MutableLiveData<AdminShopModel> mutableAdminShopsRejected = new MutableLiveData<>();
    private final MutableLiveData<AdminOfferModel> mutableAdminOfferPendingData = new MutableLiveData<>();
    private final MutableLiveData<AdminOfferModel> mutableAdminOfferRejectData = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableAdminShopPendingApprovalData = new MutableLiveData<>();
    private final MutableLiveData<AdminShopImageModel> mutableAdminShopImageData = new MutableLiveData<>();
    private final MutableLiveData<AdminShopImageModel> mutableAdminShopImageRejected = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableAdminShopImagePendingApprovalData = new MutableLiveData<>();
    private final MutableLiveData<AdminOfferImageModel> mutableAdminOfferImagePendingData = new MutableLiveData<>();
    private final MutableLiveData<AdminOfferImageModel> mutableAdminOfferImageRejected = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableAdminOfferImagePendingApprovalData = new MutableLiveData<>();
    private final MutableLiveData<JSONObject> mutableAdminOfferPendingApprovalData = new MutableLiveData<>();

    public CommonViewModel(@NonNull Application application) {
        super(application);
    }

    public void getActiveShops(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JK", "getActiveShops");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.e("Check_JKUpdate", "getActiveShops onPreExecute");
                showDialog(myProgressDialog);
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
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void getInActiveShops(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JK", "getInActiveShops");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
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
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void addOffer(AddOfferDataRequestBody addOfferDataRequestBody, MultipartBody.Part multipartBody, MyProgressDialog myProgressDialog) {
        Log.e("Check_JK", "addOffer Type : "+addOfferDataRequestBody.offer_type);
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
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
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void getActiveOffers(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
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
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void getInActiveOffers(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JK", "getInActiveOffers ID : "+requestData.get("shop_id"));
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
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
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void getAddShop(AddShopDataRequestBody addShopDataRequestBody, MultipartBody.Part multipartBody, MyProgressDialog myProgressDialog) {
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                List<MultipartBody.Part> arrayOfParts = new ArrayList<>();
                if (addShopDataRequestBody.shopamenities.size() > 0) {
                    for (int i = 0; i < addShopDataRequestBody.shopamenities.size(); i++) {
                        String partName = "shopamenities[" + i + "]";
                        String partValue = String.valueOf(addShopDataRequestBody.shopamenities.get(i));
                        arrayOfParts.add(MultipartBody.Part.createFormData(partName, partValue));
                    }
                } else {
                    String partName = "shopamenities[" + 0 + "]";
                    arrayOfParts.add(MultipartBody.Part.createFormData(partName, ""));
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
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void getDeleteShop(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JK", "getDeleteShop ID : "+requestData.get("shop_id"));
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
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
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void deleteShopPermanent(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JK", "getDeleteShop ID : "+requestData.get("shop_id"));
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
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
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void getMasterAmenities(MyProgressDialog myProgressDialog) {
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
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
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void getDeleteOffer(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
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
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void deleteOfferPermanent(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
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
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void getCategory(PrefsHelper prefsHelper, MyProgressDialog myProgressDialog) {
        Log.e("Check_JKCategory", "getCategory");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<CategoryResponse> call = apiInterface.getCategory();
                call.enqueue(new Callback<CategoryResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<CategoryResponse> call, @NonNull Response<CategoryResponse> response) {
                        Log.e("Check_JKCategory", "getCategory onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            prefsHelper.saveSettings(PrefsHelper.CATEGORY, response.body().getData());
                            mutableCategory.postValue(response.body());
                        } else
                            mutableCategory.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<CategoryResponse> call, @NonNull Throwable t) {
                        Log.e("Check_JKCategory", "getCategory Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void updateShopDetails(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JKUpdate", "updateDetails");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
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
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void updateShopImages(MultipartBody.Part filePart, RequestBody requestBody, MyProgressDialog myProgressDialog) {
        Log.e("Check_JKUpdate", "updateShopImages");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
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
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void updateOfferDetails(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JKUpdate", "updateOfferDetails");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
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
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void updateOfferImages(MultipartBody.Part filePart, RequestBody requestBody, MyProgressDialog myProgressDialog) {
        Log.e("Check_JKUpdate", "updateOfferImages");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
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
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void deleteOfferImages(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JKUpdate", "updateOfferImages");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.deleteOfferImage(requestData);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        Log.e("Check_JKUpdate", "deleteOfferImage onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            try {
                                JSONObject root = new JSONObject(response.body().toString());
                                mutableDeleteOfferImages.postValue(root);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        } else
                            mutableDeleteOfferImages.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                        Log.e("Check_JKUpdate", "deleteOfferImage Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void deleteShopImages(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JKUpdate", "updateOfferImages");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.deleteShopImage(requestData);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        Log.e("Check_JKUpdate", "deleteOfferImage onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            try {
                                JSONObject root = new JSONObject(response.body().toString());
                                mutableDeleteShopImages.postValue(root);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        } else
                            mutableDeleteShopImages.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                        Log.e("Check_JKUpdate", "deleteOfferImage Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void getRatingReview(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JKUpdate", "getRatingReview");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
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
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void shopRatingReply(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JKUpdate", "shopRatingReply");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
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
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void getOrderDetailsShops(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JKUpdate", "getOrderDetailsShops");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
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
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void getShopFeedback(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JKUpdate", "getShopFeedback");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<FeedbackModel> call = apiInterface.getshopFeedback(requestData);
                call.enqueue(new Callback<FeedbackModel>() {
                    @Override
                    public void onResponse(@NonNull Call<FeedbackModel> call, @NonNull Response<FeedbackModel> response) {
                        Log.e("Check_JKUpdate", "getShopFeedback onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            mutableShopFeedbackData.postValue(response.body());
                        } else
                            mutableShopFeedbackData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<FeedbackModel> call, @NonNull Throwable t) {
                        Log.e("Check_JKUpdate", "getShopFeedback Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void shopFeedback(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JKUpdate", "shopFeedback");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.shopFeedback(requestData);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        Log.e("Check_JKUpdate", "shopFeedback onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            try {
                                JSONObject root = new JSONObject(response.body().toString());
                                mutableAddFeedbackData.postValue(root);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        } else
                            mutableAddFeedbackData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                        Log.e("Check_JKUpdate", "shopFeedback Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void updateShopLocation(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JKUpdate", "updateShopLocation");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.updateShopLocation(requestData);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        Log.e("Check_JKUpdate", "updateShopLocation onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            try {
                                JSONObject root = new JSONObject(response.body().toString());
                                mutableUpdateShopLocationData.postValue(root);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        } else
                            mutableUpdateShopLocationData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                        Log.e("Check_JKUpdate", "updateShopLocation Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void updateShopAmenities(String shopID, List<Integer> amenitiesList, MyProgressDialog myProgressDialog) {
        Log.e("Check_JKUpdate", "updateShopAmenities");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                List<MultipartBody.Part> arrayOfParts = new ArrayList<>();
                for (int i = 0; i < amenitiesList.size(); i++) {
                    String partName = "shopamenities[" + i + "]";
                    String partValue = String.valueOf(amenitiesList.get(i));
                    arrayOfParts.add(MultipartBody.Part.createFormData(partName, partValue));
                }
                Call<JsonElement> call = apiInterface.updateShopAmenities(RequestBody.create(MediaType.parse("multipart/form-data"), ""+shopID),
                        arrayOfParts);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        Log.e("Check_JKUpdate", "updateShopAmenities onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            try {
                                JSONObject root = new JSONObject(response.body().toString());
                                mutableUpdateShopAmenitiesData.postValue(root);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        } else
                            mutableUpdateShopAmenitiesData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                        Log.e("Check_JKUpdate", "updateShopAmenities Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void getOfferImages(Map<String, Object> requestData,MyProgressDialog myProgressDialog) {
        Log.e("Check_Moorthy", "getOfferImages");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<OfferImageModel> call = apiInterface.getImagesbyOfferid(requestData);
                call.enqueue(new Callback<OfferImageModel>() {
                    @Override
                    public void onResponse(@NonNull Call<OfferImageModel> call, @NonNull Response<OfferImageModel> response) {
                        Log.e("Check_Moorthy", "getOfferImages onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            mutableGetOfferImageData.postValue(response.body());

                        } else
                            mutableGetOfferImageData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<OfferImageModel> call, @NonNull Throwable t) {
                        Log.e("Check_Moorthy", "getOfferImages Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void getShopImages(Map<String, Object> requestData,MyProgressDialog myProgressDialog) {
        Log.e("Check_Moorthy", "getShopImages");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<OfferImageModel> call = apiInterface.getImagesbyShopid(requestData);
                call.enqueue(new Callback<OfferImageModel>() {
                    @Override
                    public void onResponse(@NonNull Call<OfferImageModel> call, @NonNull Response<OfferImageModel> response) {
                        Log.e("Check_Moorthy", "getShopImages onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            mutableGetShopImageData.postValue(response.body());

                        } else
                            mutableGetShopImageData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<OfferImageModel> call, @NonNull Throwable t) {
                        Log.e("Check_Moorthy", "getShopImages Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void offerPriority(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JKUpdate", "offerPriority");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.offerPriority(requestData);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        Log.e("Check_JKUpdate", "offerPriority onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            try {
                                JSONObject root = new JSONObject(response.body().toString());
                                mutableOfferPriorityData.postValue(root);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        } else
                            mutableOfferPriorityData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                        Log.e("Check_JKUpdate", "offerPriority Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void orderDetails_shopsVisit(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JKUpdate", "orderDetails_shopsVisit "+new Gson().toJson(requestData));
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.orderDetails_shopsVisit(requestData);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        Log.e("Check_JKUpdate", "orderDetails_shopsVisit onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            try {
                                JSONObject object = new JSONObject(response.body().toString());
                                mutableOrderDetails_shopsVisitData.postValue(object);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else
                            mutableOrderDetails_shopsVisitData.postValue(null);
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
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void orderDetails_mobilenumber_View(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JKUpdate", "orderDetails_shopsVisit "+new Gson().toJson(requestData));
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.orderDetails_mobilenumber_View(requestData);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        Log.e("Check_JKUpdate", "orderDetails_shopsVisit onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            try {
                                JSONObject object = new JSONObject(response.body().toString());
                                mutableorderDetails_mobilenumber_ViewData.postValue(object);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else
                            mutableorderDetails_mobilenumber_ViewData.postValue(null);
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
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void OrderDetails_shopConfirmStatusData(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JKUpdate", "orderDetails_shopsVisit "+new Gson().toJson(requestData));
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.orderDetails_shopConfirmStatusData(requestData);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        Log.e("Check_JKUpdate", "orderDetails_shopsVisit onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            try {
                                JSONObject object = new JSONObject(response.body().toString());
                                mutableOrderDetails_shopConfirmStatusData.postValue(object);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else
                            mutableOrderDetails_shopConfirmStatusData.postValue(null);
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
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void getShopsDashData(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JK", "getActiveShops");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.e("Check_JKUpdate", "getActiveShops onPreExecute");
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<ShopDashboardModel> call = apiInterface.getShopsDashData(requestData);
                call.enqueue(new Callback<ShopDashboardModel>() {
                    @Override
                    public void onResponse(@NonNull Call<ShopDashboardModel> call, @NonNull Response<ShopDashboardModel> response) {
                        Log.e("Check_JK", "getActiveShops onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            mutableShopDashData.postValue(response.body());
                        } else
                            mutableShopDashData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<ShopDashboardModel> call, @NonNull Throwable t) {
                        Log.e("Check_JK", "getActiveShops Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void getOfferDashData(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JK", "getOfferDashData");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.e("Check_JKUpdate", "getOfferDashData onPreExecute");
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<OfferDashboardModel> call = apiInterface.getOfferDashData(requestData);
                call.enqueue(new Callback<OfferDashboardModel>() {
                    @Override
                    public void onResponse(@NonNull Call<OfferDashboardModel> call, @NonNull Response<OfferDashboardModel> response) {
                        Log.e("Check_JK", "getOfferDashData onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            mutableOfferDashData.postValue(response.body());
                        } else
                            mutableOfferDashData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<OfferDashboardModel> call, @NonNull Throwable t) {
                        Log.e("Check_JK", "getOfferDashData Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void loginCheck(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JK", "loginCheck requestData : "+new Gson().toJson(requestData));
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.e("Check_JKUpdate", "loginCheck onPreExecute");
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.loginCheck(requestData);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        Log.e("Check_JK", "loginCheck onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            try {
                                JSONObject object = new JSONObject(response.body().toString());
                                mutableLoginCheck.postValue(object);
                            } catch (Exception e) {
                                e.printStackTrace();
                                mutableLoginCheck.postValue(null);
                            }
                        } else
                            mutableLoginCheck.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                        Log.e("Check_JK", "loginCheck Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void getSubscriptionDetails(MyProgressDialog myProgressDialog) {
        Log.e("Check_JK", "getSubscriptionDetails");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<SubscriptionPackageResponse> call = apiInterface.getSubscriptionDetails();
                call.enqueue(new Callback<SubscriptionPackageResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<SubscriptionPackageResponse> call, @NonNull Response<SubscriptionPackageResponse> response) {
                        Log.e("Check_JK", "getInActiveShops onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            mutableSubscriptionData.postValue(response.body());
                        } else
                            mutableSubscriptionData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<SubscriptionPackageResponse> call, @NonNull Throwable t) {
                        Log.e("Check_JK", "mutableSubscriptionData Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void getNotify(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JK", "getNotify");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<BookModel> call = apiInterface.getNotify(requestData);
                call.enqueue(new Callback<BookModel>() {
                    @Override
                    public void onResponse(@NonNull Call<BookModel> call, @NonNull Response<BookModel> response) {
                        Log.e("Check_JK", "getNotify onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            mutableNotifyData.postValue(response.body());
                        } else
                            mutableNotifyData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<BookModel> call, @NonNull Throwable t) {
                        Log.e("Check_JK", "getNotify Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void getPaymentSuccess(Map<String, Object> requestData) {
//        Log.e("Check_JK", "getPaymentSuccess ID : "+requestData.get("shop_id"));
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.getPaymentSuccess(requestData);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
//                        Log.e("Check_JK", "getPaymentSuccess onResponse : "+response.body().toString());
                        if (response.body() != null) {
                            try {
                                JSONObject root = new JSONObject(response.body().toString());
                                mutablePaymentSuccess.postValue(root);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        } else
                            mutablePaymentSuccess.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
//                        Log.e("Check_JK", "getPaymentSuccess Error Message : " + t.getMessage());
                    }
                });
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void getAdminShop(MyProgressDialog myProgressDialog) {
        Log.e("Check_JKAdmin", "getAdminShop");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<AdminShopModel> call = apiInterface.getAdminShops();
                call.enqueue(new Callback<AdminShopModel>() {
                    @Override
                    public void onResponse(@NonNull Call<AdminShopModel> call, @NonNull Response<AdminShopModel> response) {
                        Log.e("Check_JKAdmin", "getAdminShop onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            mutableAdminShopData.postValue(response.body());
                        } else
                            mutableAdminShopData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<AdminShopModel> call, @NonNull Throwable t) {
                        Log.e("Check_JKAdmin", "getAdminShop Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void getAdminShopsRejected(MyProgressDialog myProgressDialog) {
        Log.e("Check_JKAdmin", "getAdminShopsRejected");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<AdminShopModel> call = apiInterface.getAdminShopsRejected();
                call.enqueue(new Callback<AdminShopModel>() {
                    @Override
                    public void onResponse(@NonNull Call<AdminShopModel> call, @NonNull Response<AdminShopModel> response) {
                        Log.e("Check_JKAdmin", "getAdminShopsRejected onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            mutableAdminShopsRejected.postValue(response.body());
                        } else
                            mutableAdminShopsRejected.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<AdminShopModel> call, @NonNull Throwable t) {
                        Log.e("Check_JKAdmin", "getAdminShopsRejected Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void getAdminShopPendingApproval(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JKAdmin", "getAdminShopPendingApproval");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.getAdminShopPendingApproval(requestData);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        Log.e("Check_JKAdmin", "getAdminShopPendingApproval onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            try {
                                JSONObject object = new JSONObject(response.body().toString());
                                mutableAdminShopPendingApprovalData.postValue(object);
                            } catch (Exception e) {
                                e.printStackTrace();
                                mutableAdminShopPendingApprovalData.postValue(null);
                            }
                        } else
                            mutableAdminShopPendingApprovalData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                        Log.e("Check_JKAdmin", "getAdminShopPendingApproval Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void getAdminShopImages(MyProgressDialog myProgressDialog) {
        Log.e("Check_JKAdmin", "getAdminShop");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<AdminShopImageModel> call = apiInterface.getShopsImagePending();
                call.enqueue(new Callback<AdminShopImageModel>() {
                    @Override
                    public void onResponse(@NonNull Call<AdminShopImageModel> call, @NonNull Response<AdminShopImageModel> response) {
                        Log.e("Check_JKAdmin", "getAdminShop onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            mutableAdminShopImageData.postValue(response.body());
                        } else
                            mutableAdminShopImageData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<AdminShopImageModel> call, @NonNull Throwable t) {
                        Log.e("Check_JKAdmin", "getAdminShop Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void getAdminShopImagesRejected(MyProgressDialog myProgressDialog) {
        Log.e("Check_JKAdmin", "getAdminShopImagesRejected");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<AdminShopImageModel> call = apiInterface.getShopsImageRejected();
                call.enqueue(new Callback<AdminShopImageModel>() {
                    @Override
                    public void onResponse(@NonNull Call<AdminShopImageModel> call, @NonNull Response<AdminShopImageModel> response) {
                        Log.e("Check_JKAdmin", "getAdminShopImagesRejected onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            mutableAdminShopImageRejected.postValue(response.body());
                        } else
                            mutableAdminShopImageRejected.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<AdminShopImageModel> call, @NonNull Throwable t) {
                        Log.e("Check_JKAdmin", "getAdminShopImagesRejected Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void getAdminShopImagePendingApproval(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JKAdmin", "getAdminShopImagePendingApproval");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.getAdminShopImagePendingApproval(requestData);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        Log.e("Check_JKAdmin", "getAdminShopImagePendingApproval onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            try {
                                JSONObject object = new JSONObject(response.body().toString());
                                mutableAdminShopImagePendingApprovalData.postValue(object);
                            } catch (Exception e) {
                                e.printStackTrace();
                                mutableAdminShopImagePendingApprovalData.postValue(null);
                            }
                        } else
                            mutableAdminShopImagePendingApprovalData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                        Log.e("Check_JKAdmin", "getAdminShopImagePendingApproval Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void getAdminOfferPending(MyProgressDialog myProgressDialog) {
        Log.e("Check_JKAdmin", "getAdminOfferPending");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<AdminOfferModel> call = apiInterface.getAdminOfferPending();
                call.enqueue(new Callback<AdminOfferModel>() {
                    @Override
                    public void onResponse(@NonNull Call<AdminOfferModel> call, @NonNull Response<AdminOfferModel> response) {
                        Log.e("Check_JKAdmin", "getAdminOfferPending onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            mutableAdminOfferPendingData.postValue(response.body());
                        } else
                            mutableAdminOfferPendingData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<AdminOfferModel> call, @NonNull Throwable t) {
                        Log.e("Check_JKAdmin", "getAdminOfferPending Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void getAdminOfferImagesPending(MyProgressDialog myProgressDialog) {
        Log.e("Check_JKAdmin", "getAdminOfferImagesPending");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<AdminOfferImageModel> call = apiInterface.getOfferImagePending();
                call.enqueue(new Callback<AdminOfferImageModel>() {
                    @Override
                    public void onResponse(@NonNull Call<AdminOfferImageModel> call, @NonNull Response<AdminOfferImageModel> response) {
                        Log.e("Check_JKAdmin", "getAdminOfferImagesPending onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            mutableAdminOfferImagePendingData.postValue(response.body());
                        } else
                            mutableAdminOfferImagePendingData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<AdminOfferImageModel> call, @NonNull Throwable t) {
                        Log.e("Check_JKAdmin", "getAdminOfferImagesPending Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void getAdminOfferImagesRejected(MyProgressDialog myProgressDialog) {
        Log.e("Check_JKAdmin", "getAdminOfferImagesRejected");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<AdminOfferImageModel> call = apiInterface.getOfferImageRejected();
                call.enqueue(new Callback<AdminOfferImageModel>() {
                    @Override
                    public void onResponse(@NonNull Call<AdminOfferImageModel> call, @NonNull Response<AdminOfferImageModel> response) {
                        Log.e("Check_JKAdmin", "getAdminOfferImagesRejected onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            mutableAdminOfferImageRejected.postValue(response.body());
                        } else
                            mutableAdminOfferImageRejected.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<AdminOfferImageModel> call, @NonNull Throwable t) {
                        Log.e("Check_JKAdmin", "getAdminOfferImagesRejected Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void getAdminOfferRejected(MyProgressDialog myProgressDialog) {
        Log.e("Check_JKAdmin", "getAdminOfferRejected");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<AdminOfferModel> call = apiInterface.getAdminOfferRejected();
                call.enqueue(new Callback<AdminOfferModel>() {
                    @Override
                    public void onResponse(@NonNull Call<AdminOfferModel> call, @NonNull Response<AdminOfferModel> response) {
                        Log.e("Check_JKAdmin", "getAdminOfferRejected onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            mutableAdminOfferRejectData.postValue(response.body());
                        } else
                            mutableAdminOfferRejectData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<AdminOfferModel> call, @NonNull Throwable t) {
                        Log.e("Check_JKAdmin", "getAdminOfferRejected Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void getAdminOfferPendingApproval(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JKAdmin", "getAdminOfferPendingApproval");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.getAdminOfferPendingApproval(requestData);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        Log.e("Check_JKAdmin", "getAdminOfferPendingApproval onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            try {
                                JSONObject object = new JSONObject(response.body().toString());
                                mutableAdminOfferPendingApprovalData.postValue(object);
                            } catch (Exception e) {
                                e.printStackTrace();
                                mutableAdminOfferPendingApprovalData.postValue(null);
                            }
                        } else
                            mutableAdminOfferPendingApprovalData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                        Log.e("Check_JKAdmin", "getAdminOfferPendingApproval Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void getAdminOfferImagePendingApproval(Map<String, Object> requestData, MyProgressDialog myProgressDialog) {
        Log.e("Check_JKAdmin", "getAdminOfferImagePendingApproval");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<JsonElement> call = apiInterface.getAdminOfferImagePendingApproval(requestData);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        Log.e("Check_JKAdmin", "getAdminOfferImagePendingApproval onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            try {
                                JSONObject object = new JSONObject(response.body().toString());
                                mutableAdminOfferImagePendingApprovalData.postValue(object);
                            } catch (Exception e) {
                                e.printStackTrace();
                                mutableAdminOfferImagePendingApprovalData.postValue(null);
                            }
                        } else
                            mutableAdminOfferImagePendingApprovalData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                        Log.e("Check_JKAdmin", "getAdminOfferImagePendingApproval Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void getSettingsConfig(MyProgressDialog myProgressDialog) {
        Log.e("Check_JK", "getSettingsConfig");
        new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.e("Check_JKUpdate", "getSettingsConfig onPreExecute");
                showDialog(myProgressDialog);
            }

            @Override
            protected String doInBackground(String... strings) {
                API apiInterface = RetrofitClient.getApiClient().create(API.class);
                Call<SettingModel> call = apiInterface.getSettingConfig();
                call.enqueue(new Callback<SettingModel>() {
                    @Override
                    public void onResponse(@NonNull Call<SettingModel> call, @NonNull Response<SettingModel> response) {
                        Log.e("Check_JK", "getSettingsConfig onResponse : "+new Gson().toJson(response.body()));
                        if (response.body() != null) {
                            AppApplication.getInstance().prefsHelper.savePref(PrefsHelper.FORCE_UPDATE, response.body().force_update);
                            AppApplication.getInstance().prefsHelper.savePref(PrefsHelper.LITE_UPDATE, response.body().lite_update);
                            mutableSettingData.postValue(response.body());
                        } else
                            mutableSettingData.postValue(null);
                    }

                    @Override
                    public void onFailure(@NonNull Call<SettingModel> call, @NonNull Throwable t) {
                        Log.e("Check_JK", "getSettingsConfig Error Message : " + t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                closeDialog(myProgressDialog);
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

    public MutableLiveData<FeedbackModel> getMutableShopFeedbackData() {
        return mutableShopFeedbackData;
    }

    public MutableLiveData<JSONObject> getMutableAddFeedbackData() {
        return mutableAddFeedbackData;
    }

    public MutableLiveData<JSONObject> getMutableUpdateShopLocationData() {
        return mutableUpdateShopLocationData;
    }

    public MutableLiveData<JSONObject> getMutableUpdateShopAmenitiesData() {
        return mutableUpdateShopAmenitiesData;
    }

    public MutableLiveData<JSONObject> getMutableOfferPriorityData() {
        return mutableOfferPriorityData;
    }

    public MutableLiveData<JSONObject> getMutableOrderDetails_shopsVisitData() {
        return mutableOrderDetails_shopsVisitData;
    }

    public MutableLiveData<JSONObject> getMutableOrderDetails_MobileNumber_ViewData() {
        return mutableorderDetails_mobilenumber_ViewData;
    }

    public MutableLiveData<JSONObject> getMutableOrderDetails_shopConfirmStatusData() {
        return mutableOrderDetails_shopConfirmStatusData;
    }
    public MutableLiveData<JSONObject> deleteMutableOfferImageData() {
        return mutableDeleteOfferImages;
    }

    public MutableLiveData<JSONObject> deleteMutableShopImageData() {
        return mutableDeleteShopImages;
    }

    public MutableLiveData<OfferImageModel> getMutableGetOfferImages() {
        return mutableGetOfferImageData;
    }

    public MutableLiveData<OfferImageModel> getMutableGetShopImages() {
        return mutableGetShopImageData;
    }

    public MutableLiveData<ShopDashboardModel> getMutableShopDashData() {
        return mutableShopDashData;
    }

    public MutableLiveData<OfferDashboardModel> getMutableOfferDashData() {
        return mutableOfferDashData;
    }

    public MutableLiveData<SubscriptionPackageResponse> getMutableSubscriptionData() {
        return mutableSubscriptionData;
    }

    public MutableLiveData<JSONObject> getMutableLoginCheck() {
        return mutableLoginCheck;
    }

    public MutableLiveData<BookModel> getMutableNotifyData() {
        return mutableNotifyData;
    }

    public MutableLiveData<JSONObject> getMutablePaymentSuccess() {
        return mutablePaymentSuccess;
    }

    public MutableLiveData<AdminShopModel> getMutableAdminShopData() {
        return mutableAdminShopData;
    }

    public MutableLiveData<AdminShopModel> getMutableAdminShopsRejected() {
        return mutableAdminShopsRejected;
    }

    public MutableLiveData<AdminOfferModel> getMutableAdminOfferPendingData() {
        return mutableAdminOfferPendingData;
    }

    public MutableLiveData<AdminOfferModel> getMutableAdminOfferRejectData() {
        return mutableAdminOfferRejectData;
    }

    public MutableLiveData<JSONObject> getMutableAdminShopPendingApprovalData() {
        return mutableAdminShopPendingApprovalData;
    }

    public MutableLiveData<AdminShopImageModel> getMutableAdminShopImageData() {
        return mutableAdminShopImageData;
    }

    public MutableLiveData<AdminShopImageModel> getMutableAdminShopImageRejected() {
        return mutableAdminShopImageRejected;
    }

    public MutableLiveData<JSONObject> getMutableAdminShopImagePendingApprovalData() {
        return mutableAdminShopImagePendingApprovalData;
    }

    public MutableLiveData<AdminOfferImageModel> getMutableAdminOfferImagePendingData() {
        return mutableAdminOfferImagePendingData;
    }

    public MutableLiveData<AdminOfferImageModel> getMutableAdminOfferImageRejected() {
        return mutableAdminOfferImageRejected;
    }

    public MutableLiveData<JSONObject> getMutableAdminOfferImagePendingApprovalData() {
        return mutableAdminOfferImagePendingApprovalData;
    }

    public MutableLiveData<JSONObject> getMutableAdminOfferPendingApprovalData() {
        return mutableAdminOfferPendingApprovalData;
    }

    public MutableLiveData<SettingModel> getMutableSettingData() {
        return mutableSettingData;
    }

    public void showDialog(MyProgressDialog myProgressDialog) {
        try {
            if (myProgressDialog != null && !myProgressDialog.isShowing())
                myProgressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Check_JKUpdate", "showDialog Error : "+e.getMessage());
        }
    }

    public void closeDialog(MyProgressDialog myProgressDialog) {
        try {
            if (myProgressDialog != null && myProgressDialog.isShowing())
                myProgressDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
