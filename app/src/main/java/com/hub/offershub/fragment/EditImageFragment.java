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
import java.util.List;

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
        getUpdateOfferImages();
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
        binding.imageUploadCard.setOnClickListener(this);
    }

    private void initUI() {
        if (businessModel != null) {
            if (isShop) {
                if (businessModel.image_url != null && !businessModel.image_url.equals("null"))
                    Glide.with(getActivity()).load(Uri.parse(businessModel.image_url)).into(binding.editImg);
            } else {
                if (offerModel.image_url != null && !offerModel.image_url.equals("null"))
                    Glide.with(getActivity()).load(Uri.parse(offerModel.image_url)).into(binding.editImg);
            }
        }
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
            case R.id.addImg:
                getPermission(this);
                break;
            case R.id.editDelete:
                break;
            case R.id.imageUploadCard:
                if (file != null) {
                    if (isShop) {
                        Log.e("Check_JKUpdate", "updateShopImages ShopID : "+businessModel.id);
                        commonViewModel.updateShopImages(MultipartBody.Part.createFormData("shopimage", file.getName(),
                                        RequestBody.create(MediaType.parse("multipart/form-data"), file)),
                                RequestBody.create(MediaType.parse("multipart/form-data"), ""+businessModel.id));
                    } else {
                        commonViewModel.updateOfferImages(MultipartBody.Part.createFormData("offerimage[]", file.getName(),
                                        RequestBody.create(MediaType.parse("multipart/form-data"), file)),
                                RequestBody.create(MediaType.parse("multipart/form-data"), ""+offerModel.offer_id));
                    }
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
                            getActivity().finish();
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
                            getActivity().finish();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (commonViewModel != null) {
            commonViewModel.getMutableUpdateShopImages().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableUpdateOfferImages().removeObservers(getViewLifecycleOwner());
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
                    }).into(binding.editImg);
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
                            }).into(binding.editImg);
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