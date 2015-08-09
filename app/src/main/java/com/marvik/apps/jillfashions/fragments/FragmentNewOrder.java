package com.marvik.apps.jillfashions.fragments;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.marvik.apps.jillfashions.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by victor on 7/29/2015.
 */
public class FragmentNewOrder extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private Uri uriFabricAvatar, uriClientAvatar;
    private Button btSaveOrder;
    private CheckBox cbCompleted, cbCollected;
    private ImageView ivClientAvatar, ivFabricAvatar;
    private EditText etFirstname, etLastname, etEmail, etPhonenumber, etColor, etMaterial, etCost, etDiscount, etPaidAmount;


    private static final int ACTION_PICK_FABRIC_AVATAR = 0x0001;
    private static final int ACTION_PICK_CLIENT_AVATAR = 0x0002;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_new_orders, container, false);
        initViews(view);
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        toast("RQ : " + requestCode + " RT: " + resultCode);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ACTION_PICK_CLIENT_AVATAR) {
                uriClientAvatar = data.getData();
                File file = new File(getAvatarFile(getClientAvatarUri()));
                try {
                    FileInputStream fis = new FileInputStream(file);
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                    ivClientAvatar.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (requestCode == ACTION_PICK_FABRIC_AVATAR) {
                uriFabricAvatar = data.getData();
                File file = new File(getAvatarFile(getFabricAvatarUri()));
                try {
                    FileInputStream fis = new FileInputStream(file);
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                    ivFabricAvatar.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    private String getAvatarFile(Uri avatarUri) {
        String fileUri = null;
        Cursor cursor = getActivity().getContentResolver().query(avatarUri, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            fileUri = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        }
        return fileUri;
    }

    private void toast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private Uri getFabricAvatarUri() {
        return uriFabricAvatar;
    }

    private Uri getClientAvatarUri() {
        return uriClientAvatar;
    }

    private void initViews(View view) {

        ivClientAvatar = (ImageView) view.findViewById(R.id.new_orders_imageView_client_avatar);
        ivClientAvatar.setOnClickListener(this);

        etFirstname = (EditText) view.findViewById(R.id.new_orders_editText_firstname);
        etLastname = (EditText) view.findViewById(R.id.new_orders_editText_lastname);

        etEmail = (EditText) view.findViewById(R.id.new_orders_editText_email);
        etPhonenumber = (EditText) view.findViewById(R.id.new_orders_editText_phonenumber);

        ivFabricAvatar = (ImageView) view.findViewById(R.id.new_orders_imageView_fabric_avatar);
        ivFabricAvatar.setOnClickListener(this);


        etColor = (EditText) view.findViewById(R.id.new_orders_editText_color);
        etMaterial = (EditText) view.findViewById(R.id.new_orders_editText_material);

        etCost = (EditText) view.findViewById(R.id.new_orders_editText_cost);
        etDiscount = (EditText) view.findViewById(R.id.new_orders_editText_discount);
        etPaidAmount = (EditText) view.findViewById(R.id.new_orders_editText_paid_amount);

        cbCompleted = (CheckBox) view.findViewById(R.id.new_orders_checkBox_completed);
        cbCollected = (CheckBox) view.findViewById(R.id.new_orders_checkBox_collected);

        cbCompleted.setOnCheckedChangeListener(this);
        cbCollected.setOnCheckedChangeListener(this);

        btSaveOrder = (Button) view.findViewById(R.id.new_orders_button_save_order);
        btSaveOrder.setOnClickListener(this);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    @Override
    public void onClick(View v) {
        if (v == btSaveOrder) {
            saveClientOrder();
        }

        if (v == ivClientAvatar) {
            pickClientAvatar();
        }
        if (v == ivFabricAvatar) {
            pickFabricAvatar();
        }
    }

    private void pickClientAvatar() {

        getActivity().startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), ACTION_PICK_CLIENT_AVATAR);
    }

    private void pickFabricAvatar() {
        getActivity().startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), ACTION_PICK_FABRIC_AVATAR);
    }

    private void saveClientOrder() {
        toast("Hello");
    }


}
