package com.earworm.spotifyAPI.requests.data;

import com.earworm.spotifyAPI.requests.AbstractRequest;

public abstract class AbstractDataRequest<T> extends AbstractRequest<T> {
  protected AbstractDataRequest(final Builder<T, ?> builder) {
    super(builder);
  }

  public static abstract class Builder<T, BT extends Builder<T, ?>> extends AbstractRequest.Builder<T, BT> {
    protected Builder(String accessToken) {
      super();

      assert (accessToken != null);
      assert (!accessToken.equals(""));

      setHeader("Authorization", "Bearer " + accessToken);
    }
  }
}
