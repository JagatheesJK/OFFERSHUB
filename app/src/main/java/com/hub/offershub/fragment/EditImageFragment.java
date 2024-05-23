package com.hub.offershub.fragment;

import static android.app.Activity.RESULT_OK;
import static com.hub.offershub.utils.Constants.CAMERA_REQUEST_CODE;
import static com.hub.offershub.utils.Constants.GALLERY_REQUEST_CODE;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.hub.offershub.R;
import com.hub.offershub.adapter.EditImageAdapter;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentEditImageBinding;
import com.hub.offershub.listener.ImageChooseListener;
import com.hub.offershub.listener.PermissionListener;
import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.model.OfferModel;
import com.hub.offershub.retrofit.API;
import com.hub.offershub.retrofit.RetrofitClient;
import com.hub.offershub.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditImageFragment extends BaseFragment implements View.OnClickListener, ImageChooseListener,
        PermissionListener {

    private FragmentEditImageBinding binding;
    private List<Uri> selectedImages = new ArrayList<>();
    private EditImageAdapter adapter;

    private static BusinessModel.Data businessModel;
    private static OfferModel.Data offerModel;
    private static boolean isShop = false;
    private String offerID;

    String editImage = "";
    private List<File> fileList = new ArrayList<>();

    public static EditImageFragment newInstance(BusinessModel.Data model, OfferModel.Data offer, boolean isShopData) {
        EditImageFragment fragment = new EditImageFragment();
        businessModel = model;
        offerModel = offer;
        isShop = isShopData;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEditImageBinding.inflate(getLayoutInflater());
        init();
        setListener();
//        setUpRecycler();
        getUpdateShopImages();
        getDeleteOfferImages();
        getDeleteShopImages();

        return binding.getRoot();
    }

    private void init() {
        fileList.add(null);
        fileList.add(null);
        fileList.add(null);
        initUI();
    }

    private void setListener() {
        binding.addImg.setOnClickListener(this);
        binding.editImg.setOnClickListener(this);
        binding.editDelete.setOnClickListener(this);
        binding.uploadshopLinear.setOnClickListener(this);
        binding.imageClear.setOnClickListener(this);
        binding.addofferImg1.setOnClickListener(this);
        binding.editofferDelete1.setOnClickListener(this);
        binding.uploadOfferLinear1.setOnClickListener(this);
        binding.offerClose1.setOnClickListener(this);
        binding.addofferImg2.setOnClickListener(this);
        binding.editofferDelete2.setOnClickListener(this);
        binding.uploadOfferLinear2.setOnClickListener(this);
        binding.offerClose2.setOnClickListener(this);
        binding.addofferImg3.setOnClickListener(this);
        binding.editofferDelete3.setOnClickListener(this);
        binding.uploadOfferLinear3.setOnClickListener(this);
        binding.offerClose3.setOnClickListener(this);
    }

    private void initUI() {
            if (isShop) {
                binding.shopconstraint.setVisibility(View.VISIBLE);
                binding.offerImageConstraint.setVisibility(View.GONE);
                getShopImages();
                commonViewModel.getShopImages(makeShopRequest(businessModel.id), myProgressDialog);
            } else {
                offerID = offerModel.offer_id;
                binding.shopconstraint.setVisibility(View.GONE);
                binding.offerImageConstraint.setVisibility(View.VISIBLE);
                Log.e("Check_JKImage", "getOfferImages 1 "+offerID);
                getOffersImages();
                commonViewModel.getOfferImages(makeRequest(offerID), myProgressDialog);
                Log.e("Check_JKImage", "getOfferImages 2 ");
            }
    }

    private Map<String, Object> makeRequest(String offerID) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("offer_id", offerID);
        return requestData;
    }

    private Map<String, Object> makeShopRequest(String shopID) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("shop_id", shopID);
        return requestData;
    }

    private Map<String, Object> makeImageRequest(String imageId) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("image_id", imageId);
        return requestData;
    }

    private void setUpRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        adapter = new EditImageAdapter(selectedImages, EditImageFragment.this, false);
        binding.editRecycler.setLayoutManager(layoutManager);
        binding.editRecycler.setAdapter(adapter);
        setNotifyData();
    }

    private void setNotifyData() {
        binding.editRecycler.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onImageChoose() {

    }

    @Override
    public void onImageRemove(int size) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.editImg:
                editImage = "editImg";
                getPermission(this);
                break;*/
            case R.id.addofferImg1:
                editImage = "offerImg1";
                getPermission(this);
                break;
            case R.id.addofferImg2:
                editImage = "offerImg2";
                getPermission(this);
                break;
            case R.id.addofferImg3:
                editImage = "offerImg3";
                getPermission(this);
                break;
            case R.id.addImg:
                editImage = "shopImg";
                getPermission(this);
                break;
            case R.id.editDelete:
                editImage = "shopImg";
                commonViewModel.deleteShopImages(makeImageRequest(""+(Integer) v.getTag()),myProgressDialog);
                break;
            case R.id.editofferDelete1:
                editImage = "offerImg1";
                commonViewModel.deleteOfferImages(makeImageRequest(""+(Integer) v.getTag()),myProgressDialog);
                break;
            case R.id.editofferDelete2:
                editImage = "offerImg2";
                commonViewModel.deleteOfferImages(makeImageRequest(""+(Integer) v.getTag()),myProgressDialog);
                break;
            case R.id.editofferDelete3:
                editImage = "offerImg3";
                commonViewModel.deleteOfferImages(makeImageRequest(""+(Integer) v.getTag()),myProgressDialog);
                break;
            case R.id.uploadshopLinear:
                if (isShop) {
                    showProgress();
                    commonViewModel.updateShopImages(MultipartBody.Part.createFormData("shopimage", file.getName(),
                                    RequestBody.create(MediaType.parse("multipart/form-data"), file)),
                            RequestBody.create(MediaType.parse("multipart/form-data"), ""+businessModel.id), myProgressDialog);
                }
                break;
            case R.id.uploadOfferLinear1:
                if (fileList != null && fileList.size() > 0) {
                    showProgress();
                    editImage = "offerImg1";
                    uploadOfferImage(MultipartBody.Part.createFormData("offerimage[]", fileList.get(0).getName(),
                                    RequestBody.create(MediaType.parse("multipart/form-data"), fileList.get(0))),
                            RequestBody.create(MediaType.parse("multipart/form-data"), "" + offerID));
                }
                break;
            case R.id.uploadOfferLinear2:
                if (fileList != null && fileList.size() > 0) {
                    showProgress();
                    editImage = "offerImg2";
                    uploadOfferImage(MultipartBody.Part.createFormData("offerimage[]", fileList.get(1).getName(),
                                    RequestBody.create(MediaType.parse("multipart/form-data"), fileList.get(1))),
                            RequestBody.create(MediaType.parse("multipart/form-data"), "" + offerID));
                }
                break;
            case R.id.uploadOfferLinear3:
                if (fileList != null && fileList.size() > 0) {
                    showProgress();
                    editImage = "offerImg3";
                    uploadOfferImage(MultipartBody.Part.createFormData("offerimage[]", fileList.get(2).getName(),
                                    RequestBody.create(MediaType.parse("multipart/form-data"), fileList.get(2))),
                            RequestBody.create(MediaType.parse("multipart/form-data"), "" + offerID));
                }
                /*if (file != null) {
                    if (isShop) {
                        showProgress();
                        commonViewModel.updateShopImages(MultipartBody.Part.createFormData("shopimage", file.getName(),
                                        RequestBody.create(MediaType.parse("multipart/form-data"), file)),
                                RequestBody.create(MediaType.parse("multipart/form-data"), ""+businessModel.id), myProgressDialog);
                    } else {
                        showProgress();
                        getUpdateOfferImages();
                        commonViewModel.updateOfferImages(MultipartBody.Part.createFormData("offerimage[]", file.getName(),
                                        RequestBody.create(MediaType.parse("multipart/form-data"), file)),
                                RequestBody.create(MediaType.parse("multipart/form-data"), ""+offerID), myProgressDialog);
                    }
                }*/
                break;
            case R.id.imageClear:
                file = null;
                binding.addImg.setVisibility(View.VISIBLE);
                binding.uploadshopLinear.setVisibility(View.GONE);
                binding.imageClear.setVisibility(View.GONE);
                binding.editImg.setImageDrawable(null);
                binding.shopImgStatus.setVisibility(View.GONE);
                break;
            case R.id.offerClose1:
                file = null;
                binding.addofferImg1.setVisibility(View.VISIBLE);
                binding.uploadOfferLinear1.setVisibility(View.GONE);
                binding.offerClose1.setVisibility(View.GONE);
                binding.editofferImg1.setImageDrawable(null);
                binding.offerImgStatus1.setVisibility(View.GONE);
                try {
                    if (fileList.size() > 0)
                        fileList.add(0, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.offerClose2:
                file = null;
                binding.addofferImg2.setVisibility(View.VISIBLE);
                binding.uploadOfferLinear2.setVisibility(View.GONE);
                binding.offerClose2.setVisibility(View.GONE);
                binding.editofferImg2.setImageDrawable(null);
                binding.offerImgStatus2.setVisibility(View.GONE);
                try {
                    if (fileList.size() > 0)
                        fileList.add(1, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.offerClose3:
                file = null;
                binding.addofferImg3.setVisibility(View.VISIBLE);
                binding.uploadOfferLinear3.setVisibility(View.GONE);
                binding.offerClose3.setVisibility(View.GONE);
                binding.editofferImg3.setImageDrawable(null);
                binding.offerImgStatus3.setVisibility(View.GONE);
                try {
                    if (fileList.size() > 0)
                        fileList.add(2, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    private void getUpdateShopImages() {
        commonViewModel.getMutableUpdateShopImages().observe(getViewLifecycleOwner(), jsonObject -> {
            if (EditImageFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    try {
                        if("success".equals(jsonObject.getString("status"))) {
                            if(editImage.equals("shopImg")) {
                                binding.addImg.setVisibility(View.GONE);
                                binding.uploadshopLinear.setVisibility(View.GONE);
                                binding.editDelete.setVisibility(View.VISIBLE);
                                binding.imageClear.setVisibility(View.GONE);
                                binding.editDelete.setTag(jsonObject.getInt("data"));
                            }
                        } else {

                        }
                        Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                hideProgress();
            }
        });
    }

    private void getUpdateOfferImages() {
        commonViewModel.getMutableUpdateOfferImages().observe(getViewLifecycleOwner(), jsonObject -> {
            if (EditImageFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    try {
                        if("success".equals(jsonObject.getString("status"))) {
                            if(editImage.equals("offerImg1")) {
                                binding.addofferImg1.setVisibility(View.GONE);
                                binding.uploadOfferLinear1.setVisibility(View.GONE);
                                binding.offerClose1.setVisibility(View.GONE);
                                binding.editofferDelete1.setVisibility(View.VISIBLE);
                                binding.editofferDelete1.setTag(jsonObject.getInt("data"));
//                                binding.offerImgStatus1.setVisibility(View.GONE);
                            }
                            else if(editImage.equals("offerImg2")) {
                                binding.addofferImg2.setVisibility(View.GONE);
                                binding.uploadOfferLinear2.setVisibility(View.GONE);
                                binding.offerClose2.setVisibility(View.GONE);
                                binding.editofferDelete2.setVisibility(View.VISIBLE);
                                binding.editofferDelete2.setTag(jsonObject.getInt("data"));
//                                binding.offerImgStatus2.setVisibility(View.GONE);
                            } else if(editImage.equals("offerImg3")) {
                                binding.addofferImg3.setVisibility(View.GONE);
                                binding.uploadOfferLinear3.setVisibility(View.GONE);
                                binding.offerClose3.setVisibility(View.GONE);
                                binding.editofferDelete3.setVisibility(View.VISIBLE);
                                binding.editofferDelete3.setTag(jsonObject.getInt("data"));
//                                binding.offerImgStatus3.setVisibility(View.GONE);
                            }
                        } else {

                        }
                        Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                hideProgress();
            }
        });
    }

    private void getDeleteOfferImages() {
        commonViewModel.deleteMutableOfferImageData().observe(getViewLifecycleOwner(), jsonObject -> {
            if (EditImageFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    try {
                        if("success".equals(jsonObject.getString("status"))) {
                            if(editImage.equals("offerImg1")) {
                                binding.addofferImg1.setVisibility(View.VISIBLE);
                                binding.uploadOfferLinear1.setVisibility(View.GONE);
                                binding.offerClose1.setVisibility(View.GONE);
                                binding.editofferDelete1.setVisibility(View.GONE);
                                binding.editofferImg1.setImageDrawable(null);
                                binding.offerImgStatus1.setVisibility(View.GONE);
                            }
                            else if(editImage.equals("offerImg2")) {
                                binding.addofferImg2.setVisibility(View.VISIBLE);
                                binding.uploadOfferLinear2.setVisibility(View.GONE);
                                binding.offerClose2.setVisibility(View.GONE);
                                binding.editofferDelete2.setVisibility(View.GONE);
                                binding.editofferImg2.setImageDrawable(null);
                                binding.offerImgStatus2.setVisibility(View.GONE);
                            } else if(editImage.equals("offerImg3")) {
                                binding.addofferImg3.setVisibility(View.VISIBLE);
                                binding.uploadOfferLinear3.setVisibility(View.GONE);
                                binding.offerClose3.setVisibility(View.GONE);
                                binding.editofferDelete3.setVisibility(View.GONE);
                                binding.editofferImg3.setImageDrawable(null);
                                binding.offerImgStatus3.setVisibility(View.GONE);
                            }
                        } else {

                        }
                        Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void getDeleteShopImages() {
        commonViewModel.deleteMutableShopImageData().observe(getViewLifecycleOwner(), jsonObject -> {
            if (EditImageFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    try {
                        if("success".equals(jsonObject.getString("status"))) {
                            if(editImage.equals("shopImg")) {
                                binding.addImg.setVisibility(View.VISIBLE);
                                binding.uploadshopLinear.setVisibility(View.GONE);
                                binding.imageClear.setVisibility(View.GONE);
                                binding.editDelete.setVisibility(View.GONE);
                                binding.editImg.setImageDrawable(null);
                                binding.shopImgStatus.setVisibility(View.GONE);
                            }
                        } else {

                        }
                        Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void getOffersImages() {
        commonViewModel.getMutableGetOfferImages().observe(getViewLifecycleOwner(), jsonObject -> {
            if (EditImageFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    try {
                        if("success".equals(jsonObject.status)) {
                            Log.e("Check_Moorthy"," Size "+jsonObject.data.size());
                            for(int i =0; i < jsonObject.data.size(); i++){
                                Log.e("Check_Moorthy","  i "+i);
                                if (i==0) {
                                    binding.addofferImg1.setVisibility(View.GONE);
                                    binding.editofferDelete1.setVisibility(View.VISIBLE);
                                    binding.editofferDelete1.setTag(jsonObject.data.get(i).id);
                                    binding.offerImgStatus1.setVisibility(View.VISIBLE);
                                    binding.editofferProgressBar1.setVisibility(View.VISIBLE);
                                    if ("Verified".equals(jsonObject.data.get(i).adminverifystatus)) {
                                        binding.offerImgStatus1.setImageResource(R.drawable.ic_approval_badge);
                                        int tintColor = ContextCompat.getColor(getActivity(), R.color.green);
                                        binding.offerImgStatus1.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
                                    } else if ("Rejected".equals(jsonObject.data.get(i).adminverifystatus)) {
                                        binding.offerImgStatus1.setImageResource(R.drawable.ic_reject_badge);
                                        binding.offerImgStatus1.setColorFilter(null);
                                    } else {
                                        binding.offerImgStatus1.setImageResource(R.drawable.ic_pending_status);
                                        int tintColor = ContextCompat.getColor(getActivity(), R.color.yellow);
                                        binding.offerImgStatus1.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
                                    }
                                    Glide.with(getActivity()).load(Uri.parse(jsonObject.data.get(i).image_stored_path)).listener(new RequestListener<Drawable>() {
                                        @Override
                                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                            binding.editofferProgressBar1.setVisibility(View.GONE);
                                            return false;
                                        }
                                    }).into(binding.editofferImg1);
                                } else if (i==1) {
                                    binding.addofferImg2.setVisibility(View.GONE);
                                    binding.editofferDelete2.setVisibility(View.VISIBLE);
                                    binding.editofferDelete2.setTag(jsonObject.data.get(i).id);
                                    binding.offerImgStatus2.setVisibility(View.VISIBLE);
                                    binding.editofferProgressBar2.setVisibility(View.VISIBLE);
                                    if ("Verified".equals(jsonObject.data.get(i).adminverifystatus)) {
                                        binding.offerImgStatus2.setImageResource(R.drawable.ic_approval_badge);
                                        int tintColor = ContextCompat.getColor(getActivity(), R.color.green);
                                        binding.offerImgStatus2.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
                                    } else if ("Rejected".equals(jsonObject.data.get(i).adminverifystatus)) {
                                        binding.offerImgStatus2.setImageResource(R.drawable.ic_reject_badge);
                                        binding.offerImgStatus2.setColorFilter(null);
                                    } else {
                                        binding.offerImgStatus2.setImageResource(R.drawable.ic_pending_status);
                                        int tintColor = ContextCompat.getColor(getActivity(), R.color.yellow);
                                        binding.offerImgStatus2.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
                                    }
                                    Glide.with(getActivity()).load(Uri.parse(jsonObject.data.get(i).image_stored_path)).listener(new RequestListener<Drawable>() {
                                        @Override
                                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                            binding.editofferProgressBar2.setVisibility(View.GONE);
                                            return false;
                                        }
                                    }).into(binding.editofferImg2);
                                }
                                else if (i==2) {
                                    binding.addofferImg3.setVisibility(View.GONE);
                                    binding.editofferDelete3.setVisibility(View.VISIBLE);
                                    binding.editofferDelete3.setTag(jsonObject.data.get(i).id);
                                    binding.offerImgStatus3.setVisibility(View.VISIBLE);
                                    binding.editofferProgressBar3.setVisibility(View.VISIBLE);
                                    if ("Verified".equals(jsonObject.data.get(i).adminverifystatus)) {
                                        binding.offerImgStatus3.setImageResource(R.drawable.ic_approval_badge);
                                        int tintColor = ContextCompat.getColor(getActivity(), R.color.green);
                                        binding.offerImgStatus3.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
                                    } else if ("Rejected".equals(jsonObject.data.get(i).adminverifystatus)) {
                                        binding.offerImgStatus3.setImageResource(R.drawable.ic_reject_badge);
                                        binding.offerImgStatus3.setColorFilter(null);
                                    } else {
                                        binding.offerImgStatus3.setImageResource(R.drawable.ic_pending_status);
                                        int tintColor = ContextCompat.getColor(getActivity(), R.color.yellow);
                                        binding.offerImgStatus3.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
                                    }
                                    Glide.with(getActivity()).load(Uri.parse(jsonObject.data.get(i).image_stored_path)).listener(new RequestListener<Drawable>() {
                                        @Override
                                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                            binding.editofferProgressBar3.setVisibility(View.GONE);
                                            return false;
                                        }
                                    }).into(binding.editofferImg3);
                                }
                            }
                        } else {

                        }
                        Toast.makeText(getActivity(), ""+jsonObject.message, Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public void getShopImages() {
        commonViewModel.getMutableGetShopImages().observe(getViewLifecycleOwner(), jsonObject -> {
            if (EditImageFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    try {
                        if("success".equals(jsonObject.status)) {
                            binding.addImg.setVisibility(View.GONE);
                            binding.editDelete.setVisibility(View.VISIBLE);
                            binding.editDelete.setTag(jsonObject.data.get(0).id);
                            binding.shopImgStatus.setVisibility(View.VISIBLE);
                            binding.editShopProgressBar.setVisibility(View.GONE);
                            if ("Verified".equals(jsonObject.data.get(0).adminverifystatus)) {
                                binding.shopImgStatus.setImageResource(R.drawable.ic_approval_badge);
                                int tintColor = ContextCompat.getColor(getActivity(), R.color.green);
                                binding.shopImgStatus.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
                            } else if ("Rejected".equals(jsonObject.data.get(0).adminverifystatus)) {
                                binding.shopImgStatus.setImageResource(R.drawable.ic_reject_badge);
                                binding.shopImgStatus.setColorFilter(null);
                            } else {
                                binding.shopImgStatus.setImageResource(R.drawable.ic_pending_status);
                                int tintColor = ContextCompat.getColor(getActivity(), R.color.yellow);
                                binding.shopImgStatus.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
                            }
                            Glide.with(getActivity()).load(Uri.parse(jsonObject.data.get(0).image_stored_path)).listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    binding.editShopProgressBar.setVisibility(View.GONE);
                                    return false;
                                }
                            }).into(binding.editImg);
                        } else {

                        }
                        Toast.makeText(getActivity(), ""+jsonObject.message, Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (commonViewModel != null) {
            commonViewModel.getMutableUpdateShopImages().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableUpdateOfferImages().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableGetOfferImages().removeObservers(getViewLifecycleOwner());
            commonViewModel.deleteMutableOfferImageData().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableGetShopImages().removeObservers(getViewLifecycleOwner());
            commonViewModel.deleteMutableShopImageData().removeObservers(getViewLifecycleOwner());
        }
    }

    private File file;
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE) {
                String path = getPath(getActivity(), cameraUri);
                if (path != null) {
                    file = new File(path);
//                    fileList.add(file);
                    if (getImageSizeInKb(file) > Constants.MAXIMUM_FILE_SIZE) {
//                        fileList.remove(file);
                        file = null;
                        Toast.makeText(getActivity(), "Please Select file below 2MB", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    // compressImage();
                    showDialog();
//                    binding.addImg.setVisibility(View.GONE);
                    Glide.with(getActivity()).load(file).listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            closeDialog();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            closeDialog();
                            return false;
                        }
                    }).into((editImage.equals("offerImg1")) ? binding.editofferImg1 : (editImage.equals("offerImg2")) ? binding.editofferImg2 : (editImage.equals("offerImg3")) ? binding.editofferImg3 : binding.editImg);

                    if(editImage.equals("offerImg1")) {
                        fileList.add(0, file);
                    } else if (editImage.equals("offerImg2")) {
                        fileList.add(1, file);
                    } else if (editImage.equals("offerImg3")) {
                        fileList.add(2, file);
                    }
                } else {
                    Toast.makeText(getActivity(), "path is null", Toast.LENGTH_SHORT).show();
                }
            }
            else if (requestCode == GALLERY_REQUEST_CODE) {
                if (data != null) {
                    Uri uri = data.getData();
                    if (uri != null) {
                        String path = getPath(getActivity(), uri);
                        if (path != null) {
                            file = new File(path);
//                            fileList.add(file);
                            if (getImageSizeInKb(file) > Constants.MAXIMUM_FILE_SIZE) {
//                                fileList.remove(file);
                                file = null;
                                Toast.makeText(getActivity(), "Please Select file below 2MB", Toast.LENGTH_SHORT).show();
                                return;
                            }
//                            compressImage();
                            showDialog();
//                            binding.addImg.setVisibility(View.GONE);
                            Glide.with(getActivity()).load(file.getAbsolutePath()).listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    closeDialog();
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    closeDialog();
                                    return false;
                                }
                            }).into((editImage.equals("offerImg1")) ? binding.editofferImg1 : (editImage.equals("offerImg2")) ? binding.editofferImg2 : (editImage.equals("offerImg3")) ? binding.editofferImg3 : binding.editImg);

                            if(editImage.equals("offerImg1")) {
                                fileList.add(0, file);
                                binding.addofferImg1.setVisibility(View.GONE);
                                binding.uploadOfferLinear1.setVisibility(View.VISIBLE);
                                binding.offerClose1.setVisibility(View.VISIBLE);
                                binding.offerImgStatus1.setVisibility(View.VISIBLE);
                                binding.offerImgStatus1.setImageResource(R.drawable.ic_pending_status);
                                int tintColor = ContextCompat.getColor(getActivity(), R.color.yellow);
                                binding.offerImgStatus1.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
                                binding.editofferProgressBar1.setVisibility(View.GONE);
                            } else if (editImage.equals("offerImg2")) {
                                fileList.add(1, file);
                                binding.addofferImg2.setVisibility(View.GONE);
                                binding.uploadOfferLinear2.setVisibility(View.VISIBLE);
                                binding.offerClose2.setVisibility(View.VISIBLE);
                                binding.offerImgStatus2.setVisibility(View.VISIBLE);
                                binding.offerImgStatus2.setImageResource(R.drawable.ic_pending_status);
                                int tintColor = ContextCompat.getColor(getActivity(), R.color.yellow);
                                binding.offerImgStatus2.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
                                binding.editofferProgressBar2.setVisibility(View.GONE);
                            } else if (editImage.equals("offerImg3")) {
                                fileList.add(2, file);
                                binding.addofferImg3.setVisibility(View.GONE);
                                binding.uploadOfferLinear3.setVisibility(View.VISIBLE);
                                binding.offerClose3.setVisibility(View.VISIBLE);
                                binding.offerImgStatus3.setVisibility(View.VISIBLE);
                                binding.offerImgStatus3.setImageResource(R.drawable.ic_pending_status);
                                int tintColor = ContextCompat.getColor(getActivity(), R.color.yellow);
                                binding.offerImgStatus3.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
                                binding.editofferProgressBar3.setVisibility(View.GONE);
                            }
                            else if (editImage.equals("shopImg")) {
                                binding.addImg.setVisibility(View.GONE);
                                binding.uploadshopLinear.setVisibility(View.VISIBLE);
                                binding.imageClear.setVisibility(View.VISIBLE);
                                binding.shopImgStatus.setVisibility(View.VISIBLE);
                                binding.shopImgStatus.setImageResource(R.drawable.ic_pending_status);
                                int tintColor = ContextCompat.getColor(getActivity(), R.color.yellow);
                                binding.shopImgStatus.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
                                binding.editShopProgressBar.setVisibility(View.GONE);
                            }
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), "path is null", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "uri is null", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "data is null", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onPermissionGranted() {
        chooseImageDialog();
    }

    @Override
    public void onPermissionDenied() {
        showPermissionDialog("In order to upload shop image, "+getResources().getString(R.string.app_name) + " " + "needs access to your Camera and Photos & videos. Go to Settings to enable");
    }

    private void uploadOfferImage(MultipartBody.Part filePart, RequestBody requestBody) {
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
                                JSONObject jsonObject = new JSONObject(response.body().toString());
                                if (jsonObject != null) {
                                    try {
                                        if("success".equals(jsonObject.getString("status"))) {
                                            if(editImage.equals("offerImg1")) {
                                                binding.addofferImg1.setVisibility(View.GONE);
                                                binding.uploadOfferLinear1.setVisibility(View.GONE);
                                                binding.offerClose1.setVisibility(View.GONE);
                                                binding.editofferDelete1.setVisibility(View.VISIBLE);
                                                binding.editofferDelete1.setTag(jsonObject.getInt("data"));
//                                                binding.offerImgStatus1.setVisibility(View.GONE);
                                            }
                                            else if(editImage.equals("offerImg2")) {
                                                binding.addofferImg2.setVisibility(View.GONE);
                                                binding.uploadOfferLinear2.setVisibility(View.GONE);
                                                binding.offerClose2.setVisibility(View.GONE);
                                                binding.editofferDelete2.setVisibility(View.VISIBLE);
                                                binding.editofferDelete2.setTag(jsonObject.getInt("data"));
//                                                binding.offerImgStatus2.setVisibility(View.GONE);
                                            } else if(editImage.equals("offerImg3")) {
                                                binding.addofferImg3.setVisibility(View.GONE);
                                                binding.uploadOfferLinear3.setVisibility(View.GONE);
                                                binding.offerClose3.setVisibility(View.GONE);
                                                binding.editofferDelete3.setVisibility(View.VISIBLE);
                                                binding.editofferDelete3.setTag(jsonObject.getInt("data"));
//                                                binding.offerImgStatus3.setVisibility(View.GONE);
                                            }
                                        } else {

                                        }
                                        Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        hideProgress();
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
}