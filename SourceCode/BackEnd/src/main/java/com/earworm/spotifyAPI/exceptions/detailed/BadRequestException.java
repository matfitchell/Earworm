package com.earworm.spotifyAPI.exceptions.detailed;

import com.earworm.spotifyAPI.exceptions.SpotifyWebApiException;

/**
 * The request could not be understood by the server due to malformed syntax.
 */
public class BadRequestException extends SpotifyWebApiException {

  public BadRequestException() {
    super();
  }

  public BadRequestException(String message) {
    super(message);
  }

  public BadRequestException(String message, Throwable cause) {
    super(message, cause);
  }

}
