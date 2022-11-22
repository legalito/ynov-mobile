package com.formation.velo.api.velo;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Record {
    @SerializedName("recordid")
    private String recordId;
    @SerializedName("fields")
    private Field field;
}
