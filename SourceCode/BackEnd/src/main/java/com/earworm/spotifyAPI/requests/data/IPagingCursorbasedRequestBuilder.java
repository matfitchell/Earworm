package com.earworm.spotifyAPI.requests.data;

import com.earworm.spotifyAPI.model_objects.specification.PagingCursorbased;
import com.earworm.spotifyAPI.requests.IRequest;

public interface IPagingCursorbasedRequestBuilder<T, A, BT extends IRequest.Builder<PagingCursorbased<T>, ?>>
  extends IRequest.Builder<PagingCursorbased<T>, BT> {
  BT limit(final Integer limit);

  BT after(final A after);
}
