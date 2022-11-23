package com.earworm.spotifyAPI.requests.data.tracks;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ParseException;
import com.earworm.spotifyAPI.exceptions.SpotifyWebApiException;
import com.earworm.spotifyAPI.model_objects.miscellaneous.AudioAnalysis;
import com.earworm.spotifyAPI.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get a detailed audio analysis for a single track identified by its unique Spotify ID.
 */
@JsonDeserialize(builder = GetAudioAnalysisForTrackRequest.Builder.class)
public class GetAudioAnalysisForTrackRequest extends AbstractDataRequest<AudioAnalysis> {

  /**
   * The private {@link GetAudioAnalysisForTrackRequest} constructor.
   *
   * @param builder A {@link GetAudioAnalysisForTrackRequest.Builder}.
   */
  private GetAudioAnalysisForTrackRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get an audio analysis for a track.
   *
   * @return An {@link AudioAnalysis}.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public AudioAnalysis execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new AudioAnalysis.JsonUtil().createModelObject(getJson());
  }

  /**
   * Builder class for building a {@link GetAudioAnalysisForTrackRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<AudioAnalysis, Builder> {

    /**
     * Create a new {@link GetAudioAnalysisForTrackRequest.Builder}.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The track ID setter.
     *
     * @param id Required. The Spotify ID for the track.
     * @return A {@link GetAudioAnalysisForTrackRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder id(final String id) {
      assert (id != null);
      assert (!id.equals(""));
      return setPathParameter("id", id);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetAudioAnalysisForTrackRequest}.
     */
    @Override
    public GetAudioAnalysisForTrackRequest build() {
      setPath("/v1/audio-analysis/{id}");
      return new GetAudioAnalysisForTrackRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
