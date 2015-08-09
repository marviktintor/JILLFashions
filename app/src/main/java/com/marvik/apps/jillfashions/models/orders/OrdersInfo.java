package com.marvik.apps.jillfashions.models.orders;

/**
 * Created by victor on 8/9/2015.
 */
public class OrdersInfo {

    String clientName, orderDescription, clientAvatarUri;
    int completed, collected;

    public OrdersInfo(String clientName, String orderDescription, String clientAvatarUri, int completed, int collected) {
        this.clientName = clientName;
        this.orderDescription = orderDescription;
        this.clientAvatarUri = clientAvatarUri;
        this.completed = completed;
        this.collected = collected;
    }

    public String getClientName() {
        return clientName;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public String getClientAvatarUri() {
        return clientAvatarUri;
    }

    public int getCompletedStatus() {
        return completed;
    }

    public int getCollectedStatus() {
        return collected;
    }
}
