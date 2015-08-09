package com.marvik.apps.jillfashions.views.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marvik.apps.jillfashions.R;
import com.marvik.apps.jillfashions.models.orders.OrdersInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by victor on 8/9/2015.
 */
public class OrdersAdapter extends BaseAdapter {

    private List<OrdersInfo> ordersInfos;
    private int layout;
    private Context context;

    public OrdersAdapter(Context context, int layout, List<OrdersInfo> ordersInfos) {
        this.ordersInfos = ordersInfos;
        this.layout = layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return getOrdersInfos().size()+1;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(getLayout(), parent, false);
            holder = new Holder(view, position);
            view.setTag(holder);
        }

        if (position < getOrdersInfos().size()) {
            holder = (Holder) view.getTag();
            holder.setClientName(getOrdersInfos().get(position).getClientName());
            holder.setCollectedStatus(getOrdersInfos().get(position).getCollectedStatus());
            holder.setCompletedStatus(getOrdersInfos().get(position).getCompletedStatus());
            holder.setOrderDesc(getOrdersInfos().get(position).getOrderDescription());
            try {
                holder.setClientAvatar(getOrdersInfos().get(position).getClientAvatarUri());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return view;
    }

    public List<OrdersInfo> getOrdersInfos() {
        return ordersInfos;
    }

    public int getLayout() {
        return layout;
    }

    public Context getContext() {
        return context;
    }


    class Holder {


        private TextView clientName, orderDesc, completedStatus, collectedStatus, addNewOrder;
        private ImageView clientAvatar;
        private LinearLayout llOrderIfos;

        Holder(View view, int position) {
            clientAvatar = (ImageView) view.findViewById(R.id.list_client_orders_imageView_client_avatar);
            clientName = (TextView) view.findViewById(R.id.list_client_orders_textView_client_name);
            orderDesc = (TextView) view.findViewById(R.id.list_client_orders_textView_order_desc);
            completedStatus = (TextView) view.findViewById(R.id.list_client_orders_textView_completed_status);
            collectedStatus = (TextView) view.findViewById(R.id.list_client_orders_textView_collected_status);
            addNewOrder = (TextView) view.findViewById(R.id.list_client_orders_textView_add_new);

            llOrderIfos = (LinearLayout) view.findViewById(R.id.list_client_orders_linearLayout_order_infos);

            if (position == getOrdersInfos().size()) {
                addNewOrder.setVisibility(TextView.VISIBLE);
                llOrderIfos.setVisibility(LinearLayout.GONE);
            } else {
                addNewOrder.setVisibility(TextView.GONE);
                llOrderIfos.setVisibility(LinearLayout.VISIBLE);
            }
        }

        public void setClientAvatar(String avatorUri) throws IOException {
            File file = new File(avatorUri);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                Bitmap bitmap = BitmapFactory.decodeStream(fis);
                clientAvatar.setImageBitmap(bitmap);
            } else {
                Log.d("FILE_NOT_EXISTS", file.getName());
            }
        }

        public void setClientName(String client_name) {
            clientName.setText(client_name);
        }

        public void setOrderDesc(String order_desc) {
            orderDesc.setText(order_desc);
        }

        public void setCompletedStatus(int completed) {
            if (completed == 1) {
                completedStatus.setText("Completed");
                completedStatus.setTextColor(Color.parseColor("#4db6ac"));
            }
            if (completed == 0) {
                completedStatus.setText("Not completed");
                completedStatus.setTextColor(Color.parseColor("#FF0000"));
            }
        }

        public void setCollectedStatus(int collected) {
            if (collected == 1) {
                collectedStatus.setText("Collected");
                collectedStatus.setTextColor(Color.parseColor("#4db6ac"));
            }
            if (collected == 0) {
                collectedStatus.setText("Not Collected");
                collectedStatus.setTextColor(Color.parseColor("#FF0000"));
            }
        }


    }
}
