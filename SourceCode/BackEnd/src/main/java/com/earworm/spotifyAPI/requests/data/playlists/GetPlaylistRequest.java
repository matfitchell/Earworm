package com.earworm.spotifyAPI.requests.data.playlists;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import com.earworm.spotifyAPI.exceptions.SpotifyWebApiException;
import com.earworm.spotifyAPI.model_objects.specification.Playlist;
import com.earworm.spotifyAPI.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get a playlist owned by a Spotify user.
 */
@JsonDeserialize(builder = GetPlaylistRequest.Builder.class)
public class GetPlaylistRequest extends AbstractDataRequest<Playlist> {

  /**
   * The private {@link GetPlaylistRequest} constructor.
   *
   * @param builder A {@link GetPlaylistRequest.Builder}.
   */
  private GetPlaylistRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get a playlist.
   *
   * @return A {@link Playlist}.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Playlist execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new Playlist.JsonUtil().createModelObject(getJson());
  }

  /**
   * Builder class for building a {@link GetPlaylistRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Playlist, Builder> {

    /**
     * Create a new {@link GetPlaylistRequest.Builder}.
     * <p>
     * Both Public and Private playlists belonging to any user are retrievable on provision of a valid access token.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The playlist ID setter.
     *
     * @param playlist_id The Spotify ID for the playlist.
     * @return A {@link GetPlaylistRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder playlist_id(final String playlist_id) {
      assert (playlist_id != null);
      assert (!playlist_id.equals(""));
      return setPathParameter("playlist_id", playlist_id);
    }

    /**
     * The fields filter setter.
     *
     * @param fields Optional. Filters for the query: a comma-separated list of the fields to return.
     *               If omitted, all fields are returned.
     * @return A {@link GetPlaylistRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/get-playlist/#tablepress-101">
     * Spotify: More Details on Playlist Fields</a>
     */
    public Builder fields(final String fields) {
      assert (fields != null);
      assert (!fields.equals(""));
      return setQueryParameter("fields", fields);
    }

    /**
     * The market country code setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code. Provide this
     *               parameter if you want to apply Track Relinking.
     * @return A {@link GetPlaylistRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     * @see <a href="https://developer.spotify.com/web-api/track-relinking-guide/">Spotify: Track Relinking Guide</a>
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    /**
     * The additional types setter.
     *
     * @param additionalTypes Optional. A comma-separated list of item types that your client supports
     *                        besides the default track type. Valid types are: {@code track} and {@code episode}.
     *                        An unsupported type in the response is expected to be represented as {@code null} value in the {@code item} field.
     * @return A {@link GetPlaylistRequest.Builder}.
     */
    public Builder additionalTypes(final String additionalTypes) {
      assert (additionalTypes != null);
      assert (additionalTypes.matches("((^|,)(episode|track))+$"));
      return setQueryParameter("additional_types", additionalTypes);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetPlaylistRequest}.
     */
    @Override
    public GetPlaylistRequest build() {
      setPath("/v1/playlists/{playlist_id}");
      return new GetPlaylistRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
