package com.formation.velo.api.pump;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OpenDataNantesPumpClient {

    @GET("/api/records/1.0/search/?dataset=prix-carburants-fichier-instantane-test-ods-copie&q=nantes&facet=id&facet=adresse&facet=ville&facet=prix_maj&facet=prix_nom&facet=com_arm_name&facet=epci_name&facet=dep_name&facet=reg_name&facet=services_service&facet=horaires_automate_24_24&rows=200")
    Call<OpenDataPumpNantes> getRecords();
}
