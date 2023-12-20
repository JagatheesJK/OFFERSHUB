package com.hub.offershub.retrofit;

import com.google.gson.JsonElement;
import com.hub.offershub.model.AddShopDataRequestBody;
import com.hub.offershub.model.Amenity;
import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.model.CategoryResponse;
import com.hub.offershub.model.OfferModel;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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
    @Multipart
    Call<JsonElement> addShops(@Part MultipartBody.Part shopimage,
                               @Part("shopownerid") RequestBody shopownerid,
                               @Part("shopname") RequestBody shopname,
                               @Part("mobile") RequestBody mobile,
                               @Part("upi") RequestBody upi,
                               @Part() List<MultipartBody.Part> shopamenities,
                               @Part("address1") RequestBody address1,
                               @Part("address2") RequestBody address2,
                               @Part("city") RequestBody city,
                               @Part("state") RequestBody state,
                               @Part("pincode") RequestBody pincode,
                               @Part("categoryid") RequestBody categoryid,
                               @Part("latitude") RequestBody latitude,
                               @Part("longitude") RequestBody longitude
                               );

    @POST("deleteShop")
    Call<JsonElement> deleteShop(@Body Map<String, Object> body);

    @GET("getMasterAmenities")
    Call<Amenity> getMasterAmenities();

    @GET("categoryListWithCount")
    Call<CategoryResponse> getCategory();

    @POST("deleteOffer")
    Call<JsonElement> deleteOffer(@Body Map<String, Object> body);
}