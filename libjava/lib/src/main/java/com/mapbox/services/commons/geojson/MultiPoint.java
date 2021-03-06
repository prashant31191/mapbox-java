package com.mapbox.services.commons.geojson;

import com.google.gson.GsonBuilder;
import com.mapbox.services.commons.geojson.custom.PositionDeserializer;
import com.mapbox.services.commons.geojson.custom.PositionSerializer;
import com.mapbox.services.commons.models.Position;

import java.util.List;

/**
 * A MultiPoint is a type of {@link Geometry}.
 *
 * @see <a href='http://geojson.org/geojson-spec.html#multipoint'>Official GeoJSON MultiPoint Specifications</a>
 * @since 1.0.0
 */
public class MultiPoint implements Geometry<List<Position>> {

    private final String type = "MultiPoint";
    private List<Position> coordinates;

    /**
     * Private constructor.
     *
     * @param coordinates List of {@link Position} making up the MultiPoint.
     * @since 1.0.0
     */
    private MultiPoint(List<Position> coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Should always be "MultiPoint".
     *
     * @return String "MultiPoint".
     * @since 1.0.0
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Get the list of {@link Position} making up the MultiPoint.
     *
     * @return List of {@link Position}.
     * @since 1.0.0
     */
    @Override
    public List<Position> getCoordinates() {
        return coordinates;
    }

    @Override
    public void setCoordinates(List<Position> coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Creates a {@link MultiPoint} from a list of coordinates.
     *
     * @param coordinates List of {@link Position} coordinates.
     * @return {@link MultiPoint}.
     * @since 1.0.0
     */
    public static MultiPoint fromCoordinates(List<Position> coordinates) {
        return new MultiPoint(coordinates);
    }

    /**
     * Create a GeoJSON MultiPoint object from JSON.
     *
     * @param json String of JSON making up a MultiPoint.
     * @return {@link MultiPoint} GeoJSON object.
     * @since 1.0.0
     */
    public static MultiPoint fromJson(String json) {
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(Position.class, new PositionDeserializer());
        return gson.create().fromJson(json, MultiPoint.class);
    }

    /**
     * Convert feature into JSON.
     *
     * @return String containing MultiPoint JSON.
     * @since 1.0.0
     */
    @Override
    public String toJson() {
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(Position.class, new PositionSerializer());
        return gson.create().toJson(this);
    }
}
