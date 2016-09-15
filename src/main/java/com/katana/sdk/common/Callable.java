package com.katana.sdk.common;

/**
 * Created by juan on 27/08/16.
 */
public interface Callable<T> {
    T run(T object);
}
