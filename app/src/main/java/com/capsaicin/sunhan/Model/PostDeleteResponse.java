package com.capsaicin.sunhan.Model;

import com.google.gson.annotations.SerializedName;

public class PostDeleteResponse {

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
