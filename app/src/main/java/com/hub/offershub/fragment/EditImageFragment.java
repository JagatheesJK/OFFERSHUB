package com.hub.offershub.fragment;

import static android.app.Activity.RESULT_OK;
import static com.hub.offershub.utils.Constants.CAMERA_REQUEST_CODE;
import static com.hub.offershub.utils.Constants.GALLERY_REQUEST_CODE;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
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
import com.hub.offershub.R;
import com.hub.offershub.adapter.EditImageAdapter;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentEditImageBinding;
import com.hub.offershub.listener.ImageChooseListener;
import com.hub.offershub.listener.PermissionListener;
import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.model.OfferModel;
import com.hub.offershub.utils.Constants;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class EditImageFragment extends BaseFragment implements View.OnClickListener, ImageChooseListener,
        PermissionListener {

    private FragmentEditImageBinding binding;
    private String image1 = "https://static.vecteezy.com/system/resources/previews/001/381/216/non_2x/special-offer-sale-banner-with-megaphone-free-vector.jpg";
    private String image2 = "https://static.vecteezy.com/system/resources/previews/001/381/216/non_2x/special-offer-sale-banner-with-megaphone-free-vector.jpg";
    private String image3 = "https://static.vecteezy.com/system/resources/previews/001/381/216/non_2x/special-offer-sale-banner-with-megaphone-free-vector.jpg";
    private List<Uri> selectedImages = new ArrayList<>();
    private EditImageAdapter adapter;

    private static BusinessModel.Data businessModel;
    private static OfferModel.Data offerModel;
    private static boolean isShop = false;

    String editImage = "";

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
//        selectedImages.add(Uri.parse(image1));
//        selectedImages.add(Uri.parse(image2));
//        selectedImages.add(Uri.parse(image3));
        initUI();
    }

    private void setListener() {
        binding.addImg.setOnClickListener(this);
        binding.editImg.setOnClickListener(this);
        binding.editDelete.setOnClickListener(this);
        binding.uploadshopImg.setOnClickListener(this);
        binding.imageClear.setOnClickListener(this);
        binding.addofferImg1.setOnClickListener(this);
        binding.editofferDelete1.setOnClickListener(this);
        binding.uploadofferImg1.setOnClickListener(this);
        binding.offerClose1.setOnClickListener(this);
        binding.addofferImg2.setOnClickListener(this);
        binding.editofferDelete2.setOnClickListener(this);
        binding.uploadofferImg2.setOnClickListener(this);
        binding.offerClose2.setOnClickListener(this);
        binding.addofferImg3.setOnClickListener(this);
        binding.editofferDelete3.setOnClickListener(this);
        binding.uploadofferImg3.setOnClickListener(this);
        binding.offerClose3.setOnClickListener(this);
    }

    private void initUI() {
            if (isShop) {
                binding.shopconstraint.setVisibility(View.VISIBLE);
                binding.offerImageConstraint.setVisibility(View.GONE);
                getShopImages();
                commonViewModel.getShopImages(makeShopRequest(businessModel.id),myProgressDialog);
            } else {
                binding.shopconstraint.setVisibility(View.GONE);
                binding.offerImageConstraint.setVisibility(View.VISIBLE);
                Log.e("Check_Moorthy", "getOfferImages 1 "+offerModel.offer_id);
                getOffersImages();
                commonViewModel.getOfferImages(makeRequest(offerModel.offer_id),myProgressDialog);
                Log.e("Check_Moorthy", "getOfferImages 2 ");
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
            case R.id.editImg:
                editImage = "editImg";
                getPermission(this);
                break;
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
            case R.id.uploadshopImg:
            case R.id.uploadofferImg1:
            case R.id.uploadofferImg2:
            case R.id.uploadofferImg3:
                if (file != null) {
                    if (isShop) {
                        commonViewModel.updateShopImages(MultipartBody.Part.createFormData("shopimage", file.getName(),
                                        RequestBody.create(MediaType.parse("multipart/form-data"), file)),
                                RequestBody.create(MediaType.parse("multipart/form-data"), ""+businessModel.id), myProgressDialog);
                    } else {
                        getUpdateOfferImages();
                        commonViewModel.updateOfferImages(MultipartBody.Part.createFormData("offerimage[]", file.getName(),
                                        RequestBody.create(MediaType.parse("multipart/form-data"), file)),
                                RequestBody.create(MediaType.parse("multipart/form-data"), ""+offerModel.offer_id), myProgressDialog);
                    }
                }
                break;
            case R.id.imageClear:
                file = null;
                binding.addImg.setVisibility(View.VISIBLE);
                binding.uploadshopImg.setVisibility(View.GONE);
                binding.imageClear.setVisibility(View.GONE);
                binding.editImg.setImageDrawable(null);
                break;
            case R.id.offerClose1:
                file = null;
                binding.addofferImg1.setVisibility(View.VISIBLE);
                binding.uploadofferImg1.setVisibility(View.GONE);
                binding.offerClose1.setVisibility(View.GONE);
                binding.editofferImg1.setImageDrawable(null);
                break;
            case R.id.offerClose2:
                file = null;
                binding.addofferImg2.setVisibility(View.VISIBLE);
                binding.uploadofferImg2.setVisibility(View.GONE);
                binding.offerClose2.setVisibility(View.GONE);
                binding.editofferImg2.setImageDrawable(null);
                break;
            case R.id.offerClose3:
                file = null;
                binding.addofferImg3.setVisibility(View.VISIBLE);
                binding.uploadofferImg3.setVisibility(View.GONE);
                binding.offerClose3.setVisibility(View.GONE);
                binding.editofferImg3.setImageDrawable(null);
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
                                binding.uploadshopImg.setVisibility(View.GONE);
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
                                binding.uploadofferImg1.setVisibility(View.GONE);
                                binding.offerClose1.setVisibility(View.GONE);
                                binding.editofferDelete1.setVisibility(View.VISIBLE);
                                binding.editofferDelete1.setTag(jsonObject.getInt("data"));
                            }
                            else if(editImage.equals("offerImg2")) {
                                binding.addofferImg2.setVisibility(View.GONE);
                                binding.uploadofferImg2.setVisibility(View.GONE);
                                binding.offerClose2.setVisibility(View.GONE);
                                binding.editofferDelete2.setVisibility(View.VISIBLE);
                                binding.editofferDelete2.setTag(jsonObject.getInt("data"));
                            } else if(editImage.equals("offerImg3")) {
                                binding.addofferImg3.setVisibility(View.GONE);
                                binding.uploadofferImg3.setVisibility(View.GONE);
                                binding.offerClose3.setVisibility(View.GONE);
                                binding.editofferDelete3.setVisibility(View.VISIBLE);
                                binding.editofferDelete3.setTag(jsonObject.getInt("data"));
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

    private void getDeleteOfferImages() {
        commonViewModel.deleteMutableOfferImageData().observe(getViewLifecycleOwner(), jsonObject -> {
            if (EditImageFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    try {
                        if("success".equals(jsonObject.getString("status"))) {
                            if(editImage.equals("offerImg1")) {
                                binding.addofferImg1.setVisibility(View.VISIBLE);
                                binding.uploadofferImg1.setVisibility(View.GONE);
                                binding.offerClose1.setVisibility(View.GONE);
                                binding.editofferDelete1.setVisibility(View.GONE);
                                binding.editofferImg1.setImageDrawable(null);
                            }
                            else if(editImage.equals("offerImg2")) {
                                binding.addofferImg2.setVisibility(View.VISIBLE);
                                binding.uploadofferImg2.setVisibility(View.GONE);
                                binding.offerClose2.setVisibility(View.GONE);
                                binding.editofferDelete2.setVisibility(View.GONE);
                                binding.editofferImg2.setImageDrawable(null);
                            } else if(editImage.equals("offerImg3")) {
                                binding.addofferImg3.setVisibility(View.VISIBLE);
                                binding.uploadofferImg3.setVisibility(View.GONE);
                                binding.offerClose3.setVisibility(View.GONE);
                                binding.editofferDelete3.setVisibility(View.GONE);
                                binding.editofferImg3.setImageDrawable(null);
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
                                binding.uploadshopImg.setVisibility(View.GONE);
                                binding.imageClear.setVisibility(View.GONE);
                                binding.editDelete.setVisibility(View.GONE);
                                binding.editImg.setImageDrawable(null);
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
                                    Glide.with(getActivity()).load(Uri.parse(jsonObject.data.get(i).image_stored_path)).into(binding.editofferImg1);
                                } else if (i==1) {
                                    binding.addofferImg2.setVisibility(View.GONE);
                                    binding.editofferDelete2.setVisibility(View.VISIBLE);
                                    binding.editofferDelete2.setTag(jsonObject.data.get(i).id);
                                    Glide.with(getActivity()).load(Uri.parse(jsonObject.data.get(i).image_stored_path)).into(binding.editofferImg2);
                                }
                                else if (i==2) {
                                    binding.addofferImg3.setVisibility(View.GONE);
                                    binding.editofferDelete3.setVisibility(View.VISIBLE);
                                    binding.editofferDelete3.setTag(jsonObject.data.get(i).id);
                                    Glide.with(getActivity()).load(Uri.parse(jsonObject.data.get(i).image_stored_path)).into(binding.editofferImg3);
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
                            Glide.with(getActivity()).load(Uri.parse(jsonObject.data.get(0).image_stored_path)).into(binding.editImg);
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
                    if (getImageSizeInKb(file) > Constants.MAXIMUM_FILE_SIZE) {
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
                            if (getImageSizeInKb(file) > Constants.MAXIMUM_FILE_SIZE) {
                                Toast.makeText(getActivity(), "Please Select file below 5MB", Toast.LENGTH_SHORT).show();
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
                                binding.addofferImg1.setVisibility(View.GONE);
                                binding.uploadofferImg1.setVisibility(View.VISIBLE);
                                binding.offerClose1.setVisibility(View.VISIBLE);

                            } else if (editImage.equals("offerImg2")) {
                                binding.addofferImg2.setVisibility(View.GONE);
                                binding.uploadofferImg2.setVisibility(View.VISIBLE);
                                binding.offerClose2.setVisibility(View.VISIBLE);

                            } else if (editImage.equals("offerImg3")) {
                                binding.addofferImg3.setVisibility(View.GONE);
                                binding.uploadofferImg3.setVisibility(View.VISIBLE);
                                binding.offerClose3.setVisibility(View.VISIBLE);
                            }
                            else if (editImage.equals("shopImg")) {
                                binding.addImg.setVisibility(View.GONE);
                                binding.uploadshopImg.setVisibility(View.VISIBLE);
                                binding.imageClear.setVisibility(View.VISIBLE);
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
}