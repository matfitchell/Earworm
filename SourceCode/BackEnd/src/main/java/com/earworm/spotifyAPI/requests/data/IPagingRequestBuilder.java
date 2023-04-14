package com.earworm.spotifyAPI.requests.data;

import com.earworm.spotifyAPI.model_objects.specification.Paging;
import com.earworm.spotifyAPI.requests.IRequest;

public interface IPagingRequestBuilder<T, BT extends IRequest.Builder<Paging<T>, ?>>
  extends IRequest.Builder<Paging<T>, BT> {
  BT limit(final Integer limit);

  BT offset(final Integer offset);
}
