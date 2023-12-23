package com.hub.offershub.activity;

import static com.hub.offershub.utils.Constants.CAMERA_REQUEST_CODE;
import static com.hub.offershub.utils.Constants.GALLERY_REQUEST_CODE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.hub.offershub.AppApplication;
import com.hub.offershub.PrefsHelper;
import com.hub.offershub.R;
import com.hub.offershub.adapter.ImageAdapter;
import com.hub.offershub.base.BaseActivity;
import com.hub.offershub.databinding.ActivityAddOfferBinding;
import com.hub.offershub.listener.ImageChooseListener;
import com.hub.offershub.listener.PermissionListener;
import com.hub.offershub.model.AddOfferDataRequestBody;
import com.hub.offershub.utils.Constants;
import com.hub.offershub.utils.Utils;
import com.hub.offershub.utils.compress.CompressImage;
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.skydoves.powerspinner.OnSpinnerOutsideTouchListener;
import com.skydoves.powerspinner.SpinnerAnimation;

import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddOfferActivity extends BaseActivity implements View.OnClickListener, PermissionListener, ImageChooseListener {

    private ActivityAddOfferBinding binding;
    private List<Uri> selectedImages = new ArrayList<>();
    private ImageAdapter imageAdapter;
    private GridLayoutManager gridLayoutManager;
    int selectedType;
    private int shopID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddOfferBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbarLayout.toolbarTitle.setText("Add Offer");
        binding.toolbarLayout.toolbarBack.setOnClickListener(v -> {
            finish();
        });

        init();
        setListener();
        setUpRecycler();
        getAddOfferData();

        binding.offerImgCard.setOnClickListener(v -> {
            getPermission(this);
        });
    }

    private void init() {
        shopID = Integer.parseInt(getIntent().getStringExtra("shop_id"));
        Log.e("Check_JK", "init ID --> "+shopID);
        Log.e("Check_Spinner","Size "+AppApplication.getInstance().prefsHelper.getCategory().size());
        binding.categorySpinner.setSpinnerPopupAnimation(SpinnerAnimation.DROPDOWN);
        binding.categorySpinner.setSpinnerPopupMaxHeight(Utils.dpToPx(AddOfferActivity.this, 300));

        binding.categorySpinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener<String>() {
            @Override
            public void onItemSelected(int position, @Nullable String item, int spinnerIndex, String t1) {
                Log.e("Check_JK", "onItemSelected item : "+position);
                Log.e("Check_JK", "onItemSelected t1 : "+spinnerIndex);
                if (spinnerIndex == 0) {
                    binding.plainLinear.setVisibility(View.VISIBLE);
                    binding.discountLinear.setVisibility(View.GONE);
                    binding.flatPerLinear.setVisibility(View.GONE);
                } else if (spinnerIndex == 1) {
                    binding.plainLinear.setVisibility(View.GONE);
                    binding.discountLinear.setVisibility(View.VISIBLE);
                    binding.flatPerLinear.setVisibility(View.GONE);
                } else if (spinnerIndex == 2) {
                    binding.plainLinear.setVisibility(View.GONE);
                    binding.discountLinear.setVisibility(View.GONE);
                    binding.flatPerLinear.setVisibility(View.VISIBLE);
                }
                selectedType = (spinnerIndex + 1);
            }
        });
    }

    private void setListener() {
        binding.offerSubmitBtn.setOnClickListener(this);
        binding.categorySpinner.setSpinnerOutsideTouchListener(new OnSpinnerOutsideTouchListener() {
            @Override
            public void onSpinnerOutsideTouch(@NonNull View view, @NonNull MotionEvent motionEvent) {
                if (binding.categorySpinner.isShowing())
                    binding.categorySpinner.dismiss();
            }
        });

        binding.flatPerEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // This method is called to notify you that the characters within charSequence are about to be replaced with new text
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // This method is called to notify you that somewhere within charSequence, the text has been changed
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // This method is called to notify you that somewhere within editable, the text has been changed
                String inputStr = editable.toString();
                if (!inputStr.isEmpty()) {
                    int input = Integer.parseInt(inputStr);

                    if (input > 0 && input < 100) {
                        // Valid number between 0 and 100
                        binding.flatPerEd.setError(null);
                    } else {
                        // Invalid number
                        binding.flatPerEd.setError("Flat % applicable between 0 and 100");
                    }
                }
            }
        });
    }

    private void setUpRecycler() {
        gridLayoutManager = new GridLayoutManager(this, 3);
        imageAdapter = new ImageAdapter(selectedImages, AddOfferActivity.this);
        binding.offerImgRecycler.setLayoutManager(gridLayoutManager);
        binding.offerImgRecycler.setAdapter(imageAdapter);
        setNotifyData();
    }

    private void setNotifyData() {
        binding.offerImgRecycler.getRecycledViewPool().clear();
        imageAdapter.notifyDataSetChanged();
    }

    private File file;
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE) {
                String path = getPath(AddOfferActivity.this, cameraUri);
                if (path != null) {
                    file = new File(path);
                    if (getImageSizeInKb(file) > Constants.MAXIMUM_FILE_SIZE) {
                        Toast.makeText(AddOfferActivity.this, "Please Select file below 5MB", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    compressImage();
                    showProgress(getString(R.string.please_wait));
                    binding.offerAddImg.setVisibility(View.GONE);
                    Glide.with(AddOfferActivity.this).load(file).listener(new RequestListener<Drawable>() {
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
                    }).into(binding.offerImg);
                } else {
                    Toast.makeText(AddOfferActivity.this, "path is null", Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == GALLERY_REQUEST_CODE) {
                if (data != null) {
                    binding.offerAddImg.setVisibility(View.GONE);
                    binding.offerImgRecycler.setVisibility(View.VISIBLE);
                    if (data.getClipData() != null) {
                        // Multiple images selected
                        int count = data.getClipData().getItemCount();
                        int totalSize = 0;
                        if (count <= 2)
                            totalSize = count;
                        else
                            totalSize = 3;
                        for (int i = 0; i < totalSize; i++) {
                            Uri imageUri = data.getClipData().getItemAt(i).getUri();
                            // Handle each selected image URI
//                            if (selectedImages.size() > 3)
//                                selectedImages.clear();
                            selectedImages.add(imageUri);
                            setNotifyData();
                        }
                    }
                } else {
                    Toast.makeText(AddOfferActivity.this, "data is null", Toast.LENGTH_SHORT).show();
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
                CompressImage.INSTANCE.compressBitmap(AddOfferActivity.this, file, it -> {
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

    @Override
    public void onImageRemove(int size) {
        if (size == 0) {
            binding.offerAddImg.setVisibility(View.VISIBLE);
            binding.offerImgRecycler.setVisibility(View.GONE);
        }
    }

    private void callAddOffer() {
        Log.e("Check_JK", "callAddOffer ID --> "+shopID);
        if(file != null) {
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(),
                    RequestBody.create(MediaType.parse("multipart/form-data"), file));
            AddOfferDataRequestBody addOfferDataRequestBody = new AddOfferDataRequestBody();
            addOfferDataRequestBody.shop_id = shopID;
            addOfferDataRequestBody.offer_name = "" + binding.offerNameEd.getText().toString();
            addOfferDataRequestBody.offer_desc = binding.offerDescEd.getText().toString();
            addOfferDataRequestBody.offer_type = selectedType;
            addOfferDataRequestBody.amount = priceAmount;
            addOfferDataRequestBody.original_amount = originalPrice;
            addOfferDataRequestBody.offer_amount = offerPrice;
            addOfferDataRequestBody.flat_percentage = flatPer;

            commonViewModel.addOffer(addOfferDataRequestBody, null);
            showProgress("Please Wait...");
        } else {
            AddOfferDataRequestBody addOfferDataRequestBody = new AddOfferDataRequestBody();
            addOfferDataRequestBody.shop_id = shopID;
            addOfferDataRequestBody.offer_name = "" + binding.offerNameEd.getText().toString();
            addOfferDataRequestBody.offer_desc = binding.offerDescEd.getText().toString();
            addOfferDataRequestBody.offer_type = selectedType;
            addOfferDataRequestBody.amount = priceAmount;
            addOfferDataRequestBody.original_amount = originalPrice;
            addOfferDataRequestBody.offer_amount = offerPrice;
            addOfferDataRequestBody.flat_percentage = flatPer;

            commonViewModel.addOffer(addOfferDataRequestBody, null);
            showProgress("Please Wait...");
        }
    }

    private void getAddOfferData() {
        commonViewModel.getMutableAddOffer().observeForever( jsonObject -> {
            hideProgress();
            if (jsonObject != null) {
                try {
                    if(jsonObject.getString("status").equals("success")) {
                        Toast.makeText(this, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {

                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
            binding.offerSubmitBtn.setEnabled(true);
        });
    }

    boolean isAllFieldsChecked = false;
    private int priceAmount = 0, originalPrice = 0, offerPrice = 0, flatPer = 0;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.offerSubmitBtn:
                isAllFieldsChecked = CheckAllFields();
                binding.offerSubmitBtn.setEnabled(false);
                if (binding.offerPriceEd.length() == 0)
                    priceAmount = 0;
                else
                    priceAmount = Integer.parseInt(binding.offerPriceEd.getText().toString());
                if (binding.offerOriginalPriceEd.length() == 0)
                    originalPrice = 0;
                else
                    originalPrice = Integer.parseInt(binding.offerOriginalPriceEd.getText().toString());
                if (binding.offerOfferPriceEd.length() == 0)
                    offerPrice = 0;
                else
                    offerPrice = Integer.parseInt(binding.offerOfferPriceEd.getText().toString());
                if (binding.flatPerEd.length() == 0)
                    flatPer = 0;
                else
                    flatPer = Integer.parseInt(binding.flatPerEd.getText().toString());
                if (isAllFieldsChecked) {
                    callAddOffer();
                }
                break;
            default:
                break;
        }
    }

    private boolean CheckAllFields() {
        if (binding.offerNameEd.length() == 0) {
            binding.offerNameEd.setError("Input required");
            binding.offerNameEd.setFocusableInTouchMode(true);
            binding.offerNameEd.requestFocus();
            return false;
        }

        if (binding.offerDescEd.length() == 0) {
            binding.offerDescEd.setError("Input required");
            binding.offerDescEd.requestFocus();
            return false;
        }

        if (binding.categorySpinner.length() == 0) {
            binding.categorySpinner.setError("Input required");
            runOnUiThread(() -> {
                binding.categorySpinner.requestFocus();
                binding.categorySpinner.performClick();
            });
            return false;
        }

        if (binding.plainLinear.getVisibility() == View.VISIBLE && binding.offerPriceEd.length() == 0) {
            binding.offerPriceEd.setError("Input required");
            binding.offerPriceEd.requestFocus();
            return false;
        }

        if (binding.discountLinear.getVisibility() == View.VISIBLE && binding.offerOriginalPriceEd.length() == 0) {
            binding.offerOriginalPriceEd.setError("Input required");
            binding.offerOriginalPriceEd.requestFocus();
            return false;
        }

        if (binding.discountLinear.getVisibility() == View.VISIBLE && binding.offerOfferPriceEd.length() == 0) {
            binding.offerOfferPriceEd.setError("Input required");
            binding.offerOfferPriceEd.requestFocus();
            return false;
        }

        if (binding.flatPerLinear.getVisibility() == View.VISIBLE && binding.flatPerEd.length() == 0) {
            binding.flatPerEd.setError("Input required");
            binding.flatPerEd.requestFocus();
            return false;
        } else if(binding.flatPerLinear.getVisibility() == View.VISIBLE && binding.flatPerEd.getText().toString() == "0") {
            binding.flatPerEd.setError("Flat % applicable between 0 and 100");
        }
        // after all validation return true.
        return true;
    }
}