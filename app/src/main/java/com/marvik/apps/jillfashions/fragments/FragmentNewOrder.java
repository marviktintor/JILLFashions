package com.marvik.apps.jillfashions.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.marvik.apps.jillfashions.R;
import com.marvik.apps.jillfashions.database.transactions.TransactionManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by victor on 7/29/2015.
 */
public class FragmentNewOrder extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private Uri uriFabricAvatar, uriClientAvatar;
    private Button btSaveOrder;
    private CheckBox cbCompleted, cbCollected;
    private ImageView ivClientAvatar, ivFabricAvatar;
    private EditText etFirstname, etLastname, etEmail, etPhonenumber, etColor, etMaterial, etCost, etDiscount, etPaidAmount;


    private static final int ACTION_PICK_FABRIC_AVATAR = 0x0001;
    private static final int ACTION_PICK_CLIENT_AVATAR = 0x0002;

    private static final int COLLECTED_DATE = 0x0001;
    private static final int COMPLETED_DATE = 0x0002;

    private long collectedDate, completedDate;

    private int dateType;

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

      Toast.makeText(getActivity(),"Activity Result",Toast.LENGTH_SHORT).show();

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

        if (isChecked) {
            if (buttonView == cbCollected) {
                selectDate(COLLECTED_DATE);
            }
            if (buttonView == cbCompleted) {
                selectDate(COMPLETED_DATE);
            }
        } else {
            if (buttonView == cbCollected) {
                setCollectedDate(0);
            }
            if (buttonView == cbCompleted) {
                setCompletedDate(0);
            }
        }
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

    private String getString(EditText editText) {
        return editText.getText().toString();
    }

    private void saveClientOrder() {
        String sFirstname = getString(etFirstname),
                sLastname = getString(etLastname),
                sEmail = getString(etEmail),
                sPhonenumber = getString(etPhonenumber),
                sColor = getString(etColor),
                sMaterial = getString(etMaterial),
                sCost = getString(etCost),
                sDiscount = getString(etDiscount),
                sPaidAmount = getString(etPaidAmount);

        if (valid_order()) {

            TransactionManager transactionManager = new TransactionManager(getActivity());
            int collected = 0;
            int completed = 0;
            if (cbCollected.isChecked()) {
                collected = 1;
            }
            if (cbCompleted.isChecked()) {
                completed = 1;
            }

            /*transactionManager.commitNewOrder(sFirstname, sLastname, sEmail, sPhonenumber, sColor, sMaterial, sCost, sDiscount,
                    sPaidAmount, completed, getCompletedDate(), collected, getCollectedDate(), getClientAvatarUri(), getFabricAvatarUri()
           );*/
        }
    }

    private boolean valid_order() {
        boolean orderValid = true;
        String sFirstname = getString(etFirstname),
                sLastname = getString(etLastname),
                sEmail = getString(etEmail),
                sPhonenumber = getString(etPhonenumber),
                sColor = getString(etColor),
                sMaterial = getString(etMaterial),
                sCost = getString(etCost),
                sDiscount = getString(etDiscount),
                sPaidAmount = getString(etPaidAmount);

        if (sFirstname.equals("")) {
            orderValid = false;
            etFirstname.setHintTextColor(Color.RED);
        }
        if (sLastname.equals("")) {
            orderValid = false;
            etLastname.setHintTextColor(Color.RED);
        }
        if (sEmail.equals("")) {
            orderValid = false;
            etEmail.setHintTextColor(Color.RED);
        }
        if (sPhonenumber.equals("")) {
            orderValid = false;
            etPhonenumber.setHintTextColor(Color.RED);
        }
        if (sColor.equals("")) {
            orderValid = false;
            etColor.setHintTextColor(Color.RED);
        }
        if (sMaterial.equals("")) {
            orderValid = false;
            etMaterial.setHintTextColor(Color.RED);
        }
        if (sCost.equals("")) {
            orderValid = false;
            etCost.setHintTextColor(Color.RED);
        }
        if (sDiscount.equals("")) {
            orderValid = false;
            etDiscount.setHintTextColor(Color.RED);
        }
        if (sPaidAmount.equals("")) {
            orderValid = false;
            etPaidAmount.setHintTextColor(Color.RED);
        }
        return orderValid;
    }

    private void setCollectedDate(long date) {
        collectedDate = date;
    }

    private long getCompletedDate() {
        return collectedDate;
    }


    private void setCompletedDate(long date) {
        completedDate = date;
    }

    private long getCollectedDate() {
        return collectedDate;
    }

    public int getDateType() {
        return dateType;
    }

    private void selectDate(int type) {
        Date today = new Date(System.currentTimeMillis());
        dateType = type;
        int year = today.getYear();
        int month = today.getMonth();
        int day = today.getDay();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        if (getDateType() == COLLECTED_DATE) {
            try {
                setCollectedDate(calculateDate(year, monthOfYear, dayOfMonth));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (getDateType() == COMPLETED_DATE) {
            try {
                setCompletedDate(calculateDate(year, monthOfYear, dayOfMonth));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private long calculateDate(int year, int monthOfYear, int dayOfMonth) throws ParseException {
        return new SimpleDateFormat("yyyy/MM/dd").parse("" + year + "/" + monthOfYear + "/" + dayOfMonth).getTime();
    }
}
