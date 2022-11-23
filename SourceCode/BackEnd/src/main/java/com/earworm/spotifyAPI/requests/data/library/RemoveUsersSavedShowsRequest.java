package com.earworm.spotifyAPI.requests.data.library;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonArray;
import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import com.earworm.spotifyAPI.exceptions.SpotifyWebApiException;
import com.earworm.spotifyAPI.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Delete one or more shows from current Spotify user’s library.
 */
@JsonDeserialize(builder = RemoveUsersSavedShowsRequest.Builder.class)
public class RemoveUsersSavedShowsRequest extends AbstractDataRequest<String> {

  /**
   * The private {@link RemoveUsersSavedShowsRequest} constructor.
   *
   * @param builder A {@link RemoveUsersSavedShowsRequest.Builder}.
   */
  private RemoveUsersSavedShowsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Delete one or more shows from current Spotify user’s library.
   *
   * @return A string. <b>Note:</b> This endpoint doesn't return something in its response body.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @Override
  public String execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return deleteJson();
  }

  /**
   * Builder class for building a {@link RemoveUsersSavedShowsRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<String, Builder> {

    /**
     * Create a new {@link RemoveUsersSavedShowsRequest.Builder} instance.
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
     * The show IDs setter.
     *
     * @param ids Optional. A comma-separated list of Spotify IDs for the shows to be deleted from the user’s library. Maximum: 50 IDs.
     * @return A {@link RemoveUsersSavedShowsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setQueryParameter("ids", ids);
    }

    /**
     * The market country code setter.<p>
     * If a country code is specified, only shows that are available in that market will be removed.
     * If a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter.
     * <i>Note: If neither market or user country are provided, the content is considered unavailable for the client.</i><p>
     * Users can view the country that is associated with their account in the account settings.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code.
     * @return A {@link RemoveUsersSavedShowsRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    /**
     * The show IDs setter.
     * <p>
     * <b>Note:</b> If the ids have already been set with {@link #ids(String)}, any ids added here will be ignored.
     * @param ids Optional. A JSON array of Spotify IDs for the shows to be deleted from the user’s library. Maximum: 50 IDs.
     * @return A {@link RemoveUsersSavedShowsRequest.Builder}.
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
     * @return A custom {@link RemoveUsersSavedShowsRequest}.
     */
    @Override
    public RemoveUsersSavedShowsRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/me/shows");
      return new RemoveUsersSavedShowsRequest(this);
    }

    @Override
    protected RemoveUsersSavedShowsRequest.Builder self() {
      return this;
    }
  }
}
