package com.hub.offershub.retrofit;

import com.google.gson.JsonElement;
import com.hub.offershub.model.Amenity;
import com.hub.offershub.model.BookModel;
import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.model.CategoryResponse;
import com.hub.offershub.model.FeedbackModel;
import com.hub.offershub.model.OfferModel;
import com.hub.offershub.model.RatingModel;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
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

    @POST("addOffers")
    @Multipart
    Call<JsonElement> addOffer(@Part MultipartBody.Part offer_image,
                               @Part("shop_id") RequestBody shop_id,
                               @Part("offer_name") RequestBody offer_name,
                               @Part("offer_desc") RequestBody offer_desc,
                               @Part("offer_type") RequestBody offer_type,
                               @Part("amount") RequestBody amount,
                               @Part("original_amount") RequestBody original_amount,
                               @Part("offer_amount") RequestBody offer_amount,
                               @Part("flat_percentage") RequestBody flat_percentage);

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

    @POST("deleteShopPermanent")
    Call<JsonElement> deleteShopPermanent(@Body Map<String, Object> body);

    @GET("getMasterAmenities")
    Call<Amenity> getMasterAmenities();

    @GET("categoryListWithCount")
    Call<CategoryResponse> getCategory();

    @POST("deleteOffer")
    Call<JsonElement> deleteOffer(@Body Map<String, Object> body);

    @POST("deleteOfferPermanent")
    Call<JsonElement> deleteOfferPermanent(@Body Map<String, Object> body);

    @POST("updateShopsDetails")
    Call<JsonElement> updateShopsDetails(@Body Map<String, Object> body);

    @POST("updateShopImage")
    @Multipart
    Call<JsonElement> updateShopImages(@Part MultipartBody.Part shopimage,
                                       @Part("shop_id") RequestBody shop_id);

    @POST("updateOfferDetails")
    Call<JsonElement> updateOfferDetails(@Body Map<String, Object> body);

    @POST("updateOfferImage")
    @Multipart
    Call<JsonElement> updateOfferImages(@Part MultipartBody.Part shopimage,
                                       @Part("offer_id") RequestBody shop_id);

    @POST("getRatingReview")
    Call<RatingModel> getRatingReview(@Body Map<String, Object> body);

    @POST("shopRatingReply")
    Call<JsonElement> shopRatingReply(@Body Map<String, Object> body);

    @POST("getOrderDetails_shops")
    Call<BookModel> getOrderDetailsShops(@Body Map<String, Object> body);

    @POST("getshopFeedback")
    Call<FeedbackModel> getshopFeedback(@Body Map<String, Object> body);

    @POST("shopFeedback")
    Call<JsonElement> shopFeedback(@Body Map<String, Object> body);

    @POST("updateShopLocation")
    Call<JsonElement> updateShopLocation(@Body Map<String, Object> body);

    @POST("updateShopAmenities")
    @Multipart
    Call<JsonElement> updateShopAmenities(@Part("shop_id") RequestBody shopownerid,
                                          @Part() List<MultipartBody.Part> shopamenities);

    @POST("offerPriority")
    Call<JsonElement> offerPriority(@Body Map<String, Object> body);
}