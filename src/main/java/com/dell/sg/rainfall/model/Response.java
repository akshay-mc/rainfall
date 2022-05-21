package com.dell.sg.rainfall.model;

import java.util.ArrayList;

public class Response{
    public Metadata metadata;
    public ArrayList<Item> items;
    public ApiInfo api_info;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ApiInfo getApi_info() {
        return api_info;
    }

    public void setApi_info(ApiInfo api_info) {
        this.api_info = api_info;
    }

}
