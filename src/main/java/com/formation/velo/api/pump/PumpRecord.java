package com.formation.velo.api.pump;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class PumpRecord {
    @SerializedName("recordid")
    private String recordId;
    @SerializedName("fields")
    private PumpField field;
}
