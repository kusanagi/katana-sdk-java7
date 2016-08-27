package com.katana.sdk.callables;

import com.katana.api.Request;

/**
 * Created by juan on 27/08/16.
 */
public interface RequestCallable extends Callable{
    void run(Request request);
}
