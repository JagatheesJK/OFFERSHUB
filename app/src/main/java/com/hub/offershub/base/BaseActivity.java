package com.hub.offershub.base;

import static com.hub.offershub.utils.Constants.CAMERA_REQUEST_CODE;
import static com.hub.offershub.utils.Constants.GALLERY_REQUEST_CODE;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.hub.offershub.R;
import com.hub.offershub.dialogfragment.ExitDialogFragment;
import com.hub.offershub.dialogfragment.PaymentDialogFragment;
import com.hub.offershub.listener.PermissionListener;
import com.hub.offershub.utils.CommonMethods;
import com.hub.offershub.utils.loading.MyProgressDialog;
import com.hub.offershub.viewmodel.CommonViewModel;
import com.permissionx.guolindev.PermissionX;

import java.io.File;

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    public CommonViewModel commonViewModel;
    public CommonMethods commonMethods;
    public ExitDialogFragment exitDialogFragment;
    public MyProgressDialog myProgressDialog;
    public PaymentDialogFragment paymentDialogFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        progressDialog = new ProgressDialog(BaseActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        commonViewModel = new ViewModelProvider(BaseActivity.this).get(CommonViewModel.class);
        commonMethods = new CommonMethods();
        exitDialogFragment = new ExitDialogFragment();
        myProgressDialog = new MyProgressDialog(BaseActivity.this);
        paymentDialogFragment = new PaymentDialogFragment();
    }

    public static String getPath(Context context, Uri uri) {
        Cursor cursor = null;
        if (uri == null)
            return null;
        try {
            String[] projection = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(uri, projection, null, null, null);
            if (cursor != null) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                return cursor.getString(column_index);
            }
        } catch (Exception e) {
            return null;
        }
        finally {
            if (cursor != null)
                cursor.close();
        }
        if (uri.isAbsolute())
            return uri.getPath();
        else
            return null;
    }

    public long getImageSizeInKb(File file) {
        return (file.length() / (1024));
    }

    public void getPermission(PermissionListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            PermissionX.init(BaseActivity.this)
                    .permissions(Manifest.permission.CAMERA, Manifest.permission.READ_MEDIA_IMAGES)
                    .request((allGranted, grantedList, deniedList) -> {
                        if (allGranted) {
                            listener.onPermissionGranted();
                        } else {
                            listener.onPermissionDenied();
                        }
                    });
        } else {
            PermissionX.init(BaseActivity.this)
                    .permissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .request((allGranted, grantedList, deniedList) -> {
                        if (allGranted) {
                            listener.onPermissionGranted();
                        } else {
                            listener.onPermissionDenied();
                        }
                    });
        }
    }

    public void getAllPermission(PermissionListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            PermissionX.init(BaseActivity.this)
                    .permissions(Manifest.permission.CAMERA, Manifest.permission.READ_MEDIA_IMAGES,
                            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                    .request((allGranted, grantedList, deniedList) -> {
                        if (allGranted) {
                            listener.onPermissionGranted();
                        } else {
                            listener.onPermissionDenied();
                        }
                    });
        } else {
            PermissionX.init(BaseActivity.this)
                    .permissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                    .request((allGranted, grantedList, deniedList) -> {
                        if (allGranted) {
                            listener.onPermissionGranted();
                        } else {
                            listener.onPermissionDenied();
                        }
                    });
        }
    }

    public Uri cameraUri;
    public void chooseImageDialog() {
        final Dialog dialog = new Dialog(BaseActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_choose_image);

        TextView camera = dialog.findViewById(R.id.dialog_camera);
        TextView gallery = dialog.findViewById(R.id.dialog_gallery);
        camera.setOnClickListener(view -> {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "Image for MyLookPad");
            cameraUri = BaseActivity.this.getContentResolver().
                    insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
            try {
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
            } catch (ActivityNotFoundException exception) {
                Toast.makeText(BaseActivity.this, "No apps installed for capture image", Toast.LENGTH_SHORT).show();
            }

            dialog.dismiss();
        });
        gallery.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
//            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            try {
                startActivityForResult(intent, GALLERY_REQUEST_CODE);
            } catch (ActivityNotFoundException exception){
                Toast.makeText(getApplicationContext(),
                        " You don't have any browser to send email", Toast.LENGTH_LONG).show();
            }
            dialog.dismiss();
        });

        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void showProgress(String msg) {
        progressDialog.setMessage(msg);
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    public void hideProgress() {
        if(!BaseActivity.this.isDestroyed()) {
            if (progressDialog != null && progressDialog.isShowing())
                progressDialog.dismiss();
        }
    }

    public void showPermissionDialog(String mainContent) {
        android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(this).create();
        alertDialog.setTitle("Grant Access");
        alertDialog.setMessage(mainContent);
        alertDialog.setButton("Go to settings", (dialog, which) -> {
            openAppSetting();
        });
        alertDialog.show();
    }

    public void openAppSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", BaseActivity.this.getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

}
