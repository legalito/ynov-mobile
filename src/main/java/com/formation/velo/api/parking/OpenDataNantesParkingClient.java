package com.formation.velo.api.parking;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OpenDataNantesParkingClient {
    @GET("/api/records/1.0/search/?dataset=244400404_parkings-publics-nantes&q=&facet=libcategorie&facet=libtype&facet=acces_pmr&facet=service_velo&facet=stationnement_velo&facet=stationnement_velo_securise&facet=moyen_paiement")
    Call<OpenDataParkingNantes> getRecords();
}
