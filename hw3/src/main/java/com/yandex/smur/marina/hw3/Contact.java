package com.yandex.smur.marina.hw3;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Objects;

public class Contact implements Serializable {
    private String name;
    private String telOrEmail;
    private int image;

    public Contact(String name, String telOrEmail, int image) {
        this.name = name;
        this.telOrEmail = telOrEmail;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelOrEmail() {
        return telOrEmail;
    }

    public void setTelOrEmail(String telOrEmail) {
        this.telOrEmail = telOrEmail;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Contact) {
            Contact contactObj = (Contact) obj;
            return Objects.equals(name, contactObj.name) &&
                    Objects.equals(telOrEmail, contactObj.telOrEmail)
                    && Objects.equals(image, contactObj.image)
                    ;
        }
        return false;
    }
}
