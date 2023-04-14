package com.earworm.spotifyAPI.requests.data.playlists;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import com.earworm.spotifyAPI.exceptions.SpotifyWebApiException;
import com.earworm.spotifyAPI.model_objects.specification.Paging;
import com.earworm.spotifyAPI.model_objects.specification.PlaylistTrack;
import com.earworm.spotifyAPI.requests.data.AbstractDataPagingRequest;
import com.earworm.spotifyAPI.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get full details of the tracks or episodes of a playlist owned by a Spotify user.
 */
@JsonDeserialize(builder = GetPlaylistsItemsRequest.Builder.class)
public class GetPlaylistsItemsRequest extends AbstractDataRequest<Paging<PlaylistTrack>> {

  /**
   * The private {@link GetPlaylistsItemsRequest} constructor.
   *
   * @param builder A {@link GetPlaylistsItemsRequest.Builder}.
   */
  private GetPlaylistsItemsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get a playlist's items.
   *
   * @return A playlist's items.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Paging<PlaylistTrack> execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new PlaylistTrack.JsonUtil().createModelObjectPaging(getJson());
  }

  /**
   * Builder class for building a {@link GetPlaylistsItemsRequest}.
   */
  public static final class Builder extends AbstractDataPagingRequest.Builder<PlaylistTrack, Builder> {

    /**
     * Create a new {@link GetPlaylistsItemsRequest.Builder}.
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
     * @return A {@link GetPlaylistsItemsRequest.Builder}.
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
     * @return A {@link GetPlaylistsItemsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/get-playlists-tracks/#tablepress-107">
     * Spotify: More Details on Playlist Fields</a>
     */
    public Builder fields(final String fields) {
      assert (fields != null);
      assert (!fields.equals(""));
      return setQueryParameter("fields", fields);
    }

    /**
     * The limit setter.
     *
     * @param limit Optional. The maximum number of items to return. Default: 100. Minimum: 1. Maximum: 100.
     * @return A {@link GetPlaylistsItemsRequest.Builder}.
     */
    @Override
    public Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 100);
      return setQueryParameter("limit", limit);
    }

    /**
     * The offset setter.
     *
     * @param offset Optional. The index of the first item to return. Default: 0 (the first object).
     * @return A {@link GetPlaylistsItemsRequest.Builder}.
     */
    @Override
    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    /**
     * The market country code setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code. Provide this
     *               parameter if you want to apply Track Relinking.
     * @return A {@link GetPlaylistsItemsRequest.Builder}.
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
     * @return A {@link GetPlaylistsItemsRequest.Builder}.
     */
    public Builder additionalTypes(final String additionalTypes) {
      assert (additionalTypes != null);
      assert (additionalTypes.matches("((^|,)(episode|track))+$"));
      return setQueryParameter("additional_types", additionalTypes);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetPlaylistsItemsRequest}.
     */
    @Override
    public GetPlaylistsItemsRequest build() {
      setPath("/v1/playlists/{playlist_id}/tracks");
      return new GetPlaylistsItemsRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
