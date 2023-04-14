package com.earworm.spotifyAPI.requests.data.users_profile;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ParseException;
import com.earworm.spotifyAPI.exceptions.SpotifyWebApiException;
import com.earworm.spotifyAPI.model_objects.specification.User;
import com.earworm.spotifyAPI.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get detailed profile information about the current user (including the current user’s username).
 */
@JsonDeserialize(builder = GetCurrentUsersProfileRequest.Builder.class)
public class GetCurrentUsersProfileRequest extends AbstractDataRequest<User> {

  /**
   * The private {@link GetCurrentUsersProfileRequest} constructor.
   *
   * @param builder A {@link GetCurrentUsersProfileRequest.Builder}.
   */
  private GetCurrentUsersProfileRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get the profile of the current user.
   *
   * @return A {@link User}.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public User execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new User.JsonUtil().createModelObject(getJson());
  }

  /**
   * Builder class for building a {@link GetCurrentUsersProfileRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<User, Builder> {

    /**
     * Create a new {@link GetCurrentUsersProfileRequest.Builder}.
     * <p>
     * Reading the user's email address requires the {@code user-read-email} scope; reading
     * country and product subscription level requires the {@code user-read-private} scope. Reading
     * the user's birthdate requires the {@code user-read-birthdate} scope.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetCurrentUsersProfileRequest}.
     */
    @Override
    public GetCurrentUsersProfileRequest build() {
      setPath("/v1/me");
      return new GetCurrentUsersProfileRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
