package com.hub.offershub.retrofit;

import com.google.gson.JsonElement;
import com.hub.offershub.model.AddShopDataRequestBody;
import com.hub.offershub.model.Amenity;
import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.model.OfferModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface API {

    @Headers("Content-Type: application/json")
    @POST("registerShopOwner")
    Call<JsonElement> registerShopOwner(
            @Body Map<String, Object> body
    );

    @Headers("Content-Type: application/json")
    @POST("loginShopOwner")
    Call<JsonElement> loginShopOwner(
            @Body Map<String, Object> body
    );

    @Headers("Content-Type: application/json")
    @POST("getActiveShops")
    Call<BusinessModel> getActiveShops(
            @Body Map<String, Object> body
    );

    @Headers("Content-Type: application/json")
    @POST("getInactiveShops")
    Call<BusinessModel> getInactiveShops(
            @Body Map<String, Object> body
    );

    @Headers("Content-Type: application/json")
    @POST("getActiveOffers")
    Call<OfferModel> getActiveOffers(
            @Body Map<String, Object> body
    );

    @Headers("Content-Type: application/json")
    @POST("getInactiveOffers")
    Call<OfferModel> getInActiveOffers(
            @Body Map<String, Object> body
    );

    @POST("addShops")
    Call<JsonElement> addShops(@Body AddShopDataRequestBody requestBody);

    @GET("getMasterAmenities")
    Call<Amenity> getMasterAmenities();
}