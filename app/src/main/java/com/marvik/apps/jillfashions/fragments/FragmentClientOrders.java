package com.marvik.apps.jillfashions.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.marvik.apps.jillfashions.R;
import com.marvik.apps.jillfashions.activities.ActivityNewOrder;
import com.marvik.apps.jillfashions.database.transactions.TransactionManager;
import com.marvik.apps.jillfashions.models.orders.OrdersInfo;
import com.marvik.apps.jillfashions.views.adapters.OrdersAdapter;

import java.util.List;

/**
 * Created by victor on 7/29/2015.
 */
public class FragmentClientOrders extends Fragment implements AdapterView.OnItemClickListener {

    ListView lvClientOrders;
    TransactionManager transactionManager;
    private List<OrdersInfo> clientOrders;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        transactionManager = new TransactionManager(getActivity());
        clientOrders = getTransactionManager().getOrdersAll();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_client_orders, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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

    private void initViews(View view) {
        lvClientOrders = (ListView) view.findViewById(R.id.activity_client_orders_listView_orders);
        lvClientOrders.setAdapter(new OrdersAdapter(getActivity(), R.layout.list_client_orders, clientOrders));
        lvClientOrders.setOnItemClickListener(this);
    }

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == clientOrders.size()) {
            getActivity().startActivity(new Intent(getActivity(), ActivityNewOrder.class));
        }
    }
}
