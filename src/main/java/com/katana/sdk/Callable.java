package com.katana.sdk;

import com.katana.api.Api;

/**
 * Created by juan on 27/08/16.
 */

/**
 * @param <T>
 */
public interface Callable<T extends Api> {
    /**
     * @param object
     * @return
     */
    T run(T object);
}
