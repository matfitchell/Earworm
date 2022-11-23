package com.earworm.spotifyAPI.requests.data;

import com.earworm.spotifyAPI.model_objects.specification.Paging;

public abstract class AbstractDataPagingRequest<T> extends AbstractDataRequest<T> {
  protected AbstractDataPagingRequest(final AbstractDataRequest.Builder<T, ?> builder) {
    super(builder);
  }

  public static abstract class Builder<T, BT extends Builder<T, ?>>
    extends AbstractDataRequest.Builder<Paging<T>, BT>
    implements IPagingRequestBuilder<T, BT> {
    protected Builder(String accessToken) {
      super(accessToken);

      assert (!accessToken.equals(""));

      setHeader("Authorization", "Bearer " + accessToken);
    }
  }
}
