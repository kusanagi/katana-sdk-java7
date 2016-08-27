package com.katana.sdk.callables;

import com.katana.api.Action;

/**
 * Created by juan on 27/08/16.
 */
public interface ActionCallable extends Callable {
    void run(Action action);
}
