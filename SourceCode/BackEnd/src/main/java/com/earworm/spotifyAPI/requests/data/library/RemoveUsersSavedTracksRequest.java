package com.earworm.spotifyAPI.requests.data.library;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonArray;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import com.earworm.spotifyAPI.exceptions.SpotifyWebApiException;
import com.earworm.spotifyAPI.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Remove one or more tracks from the current user’s "Your Music" library.
 */
@JsonDeserialize(builder = RemoveUsersSavedTracksRequest.Builder.class)
public class RemoveUsersSavedTracksRequest extends AbstractDataRequest<String> {

  /**
   * The private {@link RemoveUsersSavedTracksRequest} constructor.
   *
   * @param builder A {@link RemoveUsersSavedTracksRequest.Builder}.
   */
  private RemoveUsersSavedTracksRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Remove one or more tracks from the current user’s ‘Your Music’ library.
   *
   * @return A string. <b>Note:</b> This endpoint doesn't return something in its response body.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public String execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return deleteJson();
  }

  /**
   * Builder class for building a {@link RemoveUsersSavedTracksRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<String, Builder> {

    /**
     * Create a new {@link RemoveUsersSavedTracksRequest.Builder} instance.
     * <p>
     * Modification of the current user's "Your Music" collection requires authorization of the
     * {@code user-library-modify} scope.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The track IDs setter.
     *
     * @param ids Optional. A comma-separated list of the Spotify IDs. Maximum: 50 IDs.
     * @return A {@link RemoveUsersSavedTracksRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setQueryParameter("ids", ids);
    }

    /**
     * The track IDs setter.
     * <p>
     * <b>Note:</b> If the ids have already been set with {@link #ids(String)}, any ids added here will be ignored.
     * @param ids Optional. A JSON array of the Spotify IDs. Maximum: 50 IDs.
     * @return A {@link RemoveUsersSavedTracksRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder ids(final JsonArray ids) {
      assert (ids != null);
      assert (!ids.isJsonNull());
      assert (ids.size() <= 50);
      return setBodyParameter("ids", ids);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link RemoveUsersSavedTracksRequest}.
     */
    @Override
    public RemoveUsersSavedTracksRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/me/tracks");
      return new RemoveUsersSavedTracksRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
