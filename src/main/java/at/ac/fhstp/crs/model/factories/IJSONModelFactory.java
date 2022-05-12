package at.ac.fhstp.crs.model.factories;

import org.json.JSONObject;

import at.ac.fhstp.crs.model.AEntity;

public interface IJSONModelFactory<T extends AEntity<T>> {
    T createModel(JSONObject object);
}
