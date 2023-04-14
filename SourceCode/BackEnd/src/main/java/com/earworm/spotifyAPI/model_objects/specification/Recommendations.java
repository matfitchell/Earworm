package com.earworm.spotifyAPI.model_objects.specification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonObject;
import com.earworm.spotifyAPI.model_objects.AbstractModelObject;

import java.util.Arrays;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#recommendations-object">
 * Recommendation objects</a> by building instances from this class.
 */
@JsonDeserialize(builder = Recommendations.Builder.class)
public class Recommendations extends AbstractModelObject {
  private final RecommendationsSeed[] seeds;
  private final TrackSimplified[] tracks;

  private Recommendations(final Builder builder) {
    super(builder);

    this.seeds = builder.seeds;
    this.tracks = builder.tracks;
  }

  /**
   * Get the recommendation seeds from the recommendations object.
   *
   * @return An array of recommendation seed objects.
   */
  public RecommendationsSeed[] getSeeds() {
    return seeds;
  }

  /**
   * Get the (simplified) tracks from the recommendations object.
   *
   * @return An array of track object (simplified) ordered according to the parameters supplied.
   */
  public TrackSimplified[] getTracks() {
    return tracks;
  }

  @Override
  public String toString() {
    return "Recommendations(seeds=" + Arrays.toString(seeds) + ", tracks=" + Arrays.toString(tracks) + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link Recommendations} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private RecommendationsSeed[] seeds;
    private TrackSimplified[] tracks;

    /**
     * The recommendation seeds setter.
     *
     * @param seeds An array of recommendation seed objects.
     * @return A {@link Recommendations.Builder}.
     */
    public Builder setSeeds(RecommendationsSeed... seeds) {
      this.seeds = seeds;
      return this;
    }

    /**
     * The recommended tracks setter.
     *
     * @param tracks An array of track objects (simplified).
     * @return A {@link Recommendations.Builder}.
     */
    public Builder setTracks(TrackSimplified... tracks) {
      this.tracks = tracks;
      return this;
    }

    @Override
    public Recommendations build() {
      return new Recommendations(this);
    }
  }

  /**
   * JsonUtil class for building {@link Recommendations} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Recommendations> {
    public Recommendations createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Recommendations.Builder()
        .setSeeds(
          hasAndNotNull(jsonObject, "seeds")
            ? new RecommendationsSeed.JsonUtil().createModelObjectArray(
            jsonObject.getAsJsonArray("seeds"))
            : null)
        .setTracks(
          hasAndNotNull(jsonObject, "tracks")
            ? new TrackSimplified.JsonUtil().createModelObjectArray(
            jsonObject.getAsJsonArray("tracks"))
            : null)
        .build();
    }
  }
}
