package com.formation.velo.api.pump;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OpenDataNantesPumpClient {

    @GET("/explore/dataset/prix-carburants-fichier-instantane-test-ods-copie/api/?q=nantes")
    Call<OpenDataPumpNantes> getRecords();
}
