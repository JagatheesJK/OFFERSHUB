package com.hub.offershub.activity;

import static com.hub.offershub.utils.Constants.CAMERA_REQUEST_CODE;
import static com.hub.offershub.utils.Constants.GALLERY_REQUEST_CODE;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.hub.offershub.R;
import com.hub.offershub.adapter.ImageAdapter;
import com.hub.offershub.base.BaseActivity;
import com.hub.offershub.databinding.ActivityAddBusinessBinding;
import com.hub.offershub.listener.ImageChooseListener;
import com.hub.offershub.listener.PermissionListener;
import com.hub.offershub.utils.Constants;
import com.hub.offershub.utils.compress.CompressImage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddBusinessActivity extends BaseActivity implements PermissionListener,
        ImageChooseListener {

    private ActivityAddBusinessBinding binding;
    private List<Uri> selectedImages = new ArrayList<>();
    private ImageAdapter imageAdapter;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBusinessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbarLayout.toolbarTitle.setText("AddBusiness");
        binding.toolbarLayout.toolbarBack.setOnClickListener(v -> {
            finish();
        });

        setUpRecycler();

        binding.shopImgCard.setOnClickListener(v -> {
            Log.e("Check_test", "shopImgCard");
            getPermission(this);
        });


    }

    private void setUpRecycler() {
        gridLayoutManager = new GridLayoutManager(this, 3);
        imageAdapter = new ImageAdapter(selectedImages, AddBusinessActivity.this);
        binding.shopImgRecycler.setLayoutManager(gridLayoutManager);
        binding.shopImgRecycler.setAdapter(imageAdapter);
        setNotifyData();
    }

    private void setNotifyData() {
        binding.shopImgRecycler.getRecycledViewPool().clear();
        imageAdapter.notifyDataSetChanged();
    }

    private File file;
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE) {
                String path = getPath(AddBusinessActivity.this, cameraUri);
                if (path != null) {
                    file = new File(path);
                    if (getImageSizeInKb(file) > Constants.MAXIMUM_FILE_SIZE) {
                        Toast.makeText(AddBusinessActivity.this, "Please Select file below 5MB", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    compressImage();
                    showProgress(getString(R.string.please_wait));
                    binding.shopAddImg.setVisibility(View.GONE);
                    Glide.with(AddBusinessActivity.this).load(file).listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            hideProgress();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            hideProgress();
                            return false;
                        }
                    }).into(binding.shopImg);
                } else {
                    Toast.makeText(AddBusinessActivity.this, "path is null", Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == GALLERY_REQUEST_CODE) {
                if (data != null) {
                    binding.shopAddImg.setVisibility(View.GONE);
                    binding.shopImgRecycler.setVisibility(View.VISIBLE);
                    if (data.getClipData() != null) {
                        // Multiple images selected
                        int count = data.getClipData().getItemCount();
                        for (int i = 0; i < count; i++) {
                            Uri imageUri = data.getClipData().getItemAt(i).getUri();
                            // Handle each selected image URI
                            if (selectedImages.size() > 3)
                                selectedImages.clear();
                            selectedImages.add(imageUri);
                            setNotifyData();
                        }
                    }
                    /*Uri uri = data.getData();
                    if (uri != null) {
                        String path = getPath(AddBusinessActivity.this, uri);
                        if (path != null) {
                            file = new File(path);
                            if (getImageSizeInKb(file) > Constants.MAXIMUM_FILE_SIZE) {
                                Toast.makeText(AddBusinessActivity.this, "Please Select file below 5MB", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            compressImage();
                            showProgress(getString(R.string.please_wait));
                            binding.shopAddImg.setVisibility(View.GONE);
                            Glide.with(AddBusinessActivity.this).load(file.getAbsolutePath()).listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    hideProgress();
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    hideProgress();
                                    return false;
                                }
                            }).into(binding.shopImg);
                        } else {
                            Toast.makeText(AddBusinessActivity.this, "path is null", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AddBusinessActivity.this, "uri is null", Toast.LENGTH_SHORT).show();
                    }*/
                } else {
                    Toast.makeText(AddBusinessActivity.this, "data is null", Toast.LENGTH_SHORT).show();
                }
                hideProgress();
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

    private void compressImage() {
        try {
            boolean res = CompressImage.INSTANCE.compressCurrentBitmapFile(file);
            if (res) {
                CompressImage.INSTANCE.compressBitmap(AddBusinessActivity.this, file, it -> {
                    file = it;
                    return null;
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onImageChoose() {
        getPermission(this);
    }
}