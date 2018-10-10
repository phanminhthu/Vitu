package danazone.com.vitu.ui.main.account.update;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.ImageLoader;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import danazone.com.vitu.BaseActivity;
import danazone.com.vitu.BaseApp;
import danazone.com.vitu.R;
import danazone.com.vitu.common.Common;
import danazone.com.vitu.sqlite.DBManager;
import danazone.com.vitu.ui.dialog.CameraDialog;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

@SuppressLint("Registered")
@EActivity(R.layout.activity_update_account)
public class UpdateAccountActivity extends BaseActivity {
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static final int REQUEST_GET_SINGLE_FILE = 101;

    @ViewById
    TextView mTvName;
    @ViewById
    TextView mTvPhone;
    @ViewById
    TextView mTvGender;
    @ViewById
    TextView mTvBirthday;
    @ViewById
    TextView mTvSchool;
    @ViewById
    TextView mTvHomeTown;
    @ViewById
    TextView mTvDegree;
    @ViewById
    TextView mTvCaseClass;
    @ViewById
    TextView mTvExperience;
    @ViewById
    ImageView mImgAvatar;

    private ContentValues values;
    private Uri imageUri;
    private Bitmap bitmap;
    private String sl;
    private String uri;

    private DBManager dbManager;
    private Socket mSocket;

    @Override
    protected void afterView() {
        getSupportActionBar().hide();
        BaseApp app = (BaseApp) getApplication().getApplicationContext();
        mSocket = app.getSocket();

        dbManager = new DBManager(this);
        Cursor cursor = dbManager.getData();
        while (cursor.moveToNext()) {
            mTvName.setText(cursor.getString(1));
            mTvPhone.setText(cursor.getString(2));
            if (cursor.getString(4).equals("M1")) {
                mTvGender.setText("Nam");
            } else {
                mTvGender.setText("Nữ");
            }
            mTvBirthday.setText(cursor.getString(6));
            mTvSchool.setText(cursor.getString(5));
            mTvHomeTown.setText(cursor.getString(7));
            mTvDegree.setText(cursor.getString(12));
            // mTvCaseClass.setText(cursor.getString(6));
            mTvExperience.setText(cursor.getString(8));
        }
    }

    @Click({R.id.mTvSubmit, R.id.mImgAvatar})
    void onlick(View v) {
        switch (v.getId()) {
            case R.id.mImgAvatar:
                new CameraDialog(this, new CameraDialog.OnDialogClickListener() {
                    @Override
                    public void onTake() {
                        dispatchTakenPictureIntent();
                    }

                    @Override
                    public void onChoose() {
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        // Start the Intent
                        startActivityForResult(galleryIntent, REQUEST_GET_SINGLE_FILE);
                    }
                }).show();
                break;

            case R.id.mTvSubmit:
                try {
                    bitmap = ImageLoader.init().from(uri).requestSize(1280, 1280).getBitmap();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                final String encodeIamge = ImageBase64.encode(bitmap);
                JSONObject object = new JSONObject();
                try {

                    object.put("name", mTvName.getText().toString());
                    if (mTvGender.getText().toString().equalsIgnoreCase("Nam")) {
                        object.put("sex", "M1");
                    } else {

                        object.put("sex", "M2");
                    }
                    object.put("school", mTvSchool.getText().toString());
                    object.put("homeTown", mTvHomeTown.getText().toString());

                    object.put("mTvExperience", mTvExperience.getText().toString());
                    object.put("mTvBirthday", Integer.valueOf(mTvBirthday.getText().toString()));

                    object.put("type", "");
                    object.put("status", "");
                    object.put("pass", "");
                   // object.put("degree", Common.URL_SERVER + "images/tutor/degree/"+ encodeIamge);
                    // object.put("identityCard", Common.URL_SERVER  + "images/tutor/identityCard/"+ encodeIamge);
                     object.put("avatar",  encodeImage(uri));

                    System.out.println("333333333333333333333333333333" + encodeIamge);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mSocket.emit("updateInfomation", object);
                mSocket.on("getInfomation", new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        if (args[0].toString().matches("true")) {
                            Toast.makeText(UpdateAccountActivity.this, "Cap nhat thanh congggggggg", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UpdateAccountActivity.this, "hhhhhhhh", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                mSocket.on("updateInfomation", new Emitter.Listener() {
                    @Override
                    public void call(final Object... args) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (args[0].toString().matches("true")) {
                                    Toast.makeText(UpdateAccountActivity.this, "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(UpdateAccountActivity.this, "hhhhhhhh", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                break;
        }
    }

    private String encodeImage(String path) {
        File imagefile = new File(path);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(imagefile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap bm = BitmapFactory.decodeStream(fis);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
        //Base64.de
        return encImage;
    }


    private void dispatchTakenPictureIntent() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                ) {
            return;
        }
        values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        imageUri = getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        sl = getRealPathFromURI(imageUri);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, MY_CAMERA_REQUEST_CODE);

    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MY_CAMERA_REQUEST_CODE:

                if (requestCode == MY_CAMERA_REQUEST_CODE)
                    if (resultCode == Activity.RESULT_OK) {
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(
                                    getContentResolver(), imageUri);

                            mImgAvatar.setImageBitmap(bitmap);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {

                    }
                break;
            case REQUEST_GET_SINGLE_FILE:

                if (requestCode == REQUEST_GET_SINGLE_FILE && resultCode == Activity.RESULT_OK

                        && null != data) {

                    Uri selectedImage = data.getData();

                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage,

                            filePathColumn, null, null, null);

                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

                    uri = cursor.getString(columnIndex);
                    cursor.close();

                    mImgAvatar.setImageBitmap(BitmapFactory.decodeFile(uri));

                } else {

                }
                break;
        }
    }

}
