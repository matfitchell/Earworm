package com.earworm.spotifyAPI.requests.data.player;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import com.earworm.spotifyAPI.exceptions.SpotifyWebApiException;
import com.earworm.spotifyAPI.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Pause playback on the user’s account.
 */
@JsonDeserialize(builder = PauseUsersPlaybackRequest.Builder.class)
public class PauseUsersPlaybackRequest extends AbstractDataRequest<String> {

  /**
   * The private {@link PauseUsersPlaybackRequest} constructor.
   *
   * @param builder A {@link PauseUsersPlaybackRequest.Builder}.
   */
  private PauseUsersPlaybackRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Pause an user's playback.
   *
   * @return A string. <b>Note:</b> This endpoint doesn't return something in its response body.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public String execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return putJson();
  }

  /**
   * Builder class for building a {@link PauseUsersPlaybackRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<String, Builder> {

    /**
     * Create a new {@link PauseUsersPlaybackRequest.Builder}.
     * <p>
     * Your access token must have the {@code user-modify-playback-state} scope authorized in order to control playback.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The device ID setter.
     *
     * @param device_id Optional. The ID of the device this command is targeting. If not supplied, the
     *                  user's currently active device is the target.
     * @return A {@link PauseUsersPlaybackRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder device_id(final String device_id) {
      assert (device_id != null);
      assert (!device_id.equals(""));
      return setQueryParameter("device_id", device_id);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link PauseUsersPlaybackRequest}.
     */
    @Override
    public PauseUsersPlaybackRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/me/player/pause");
      return new PauseUsersPlaybackRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
