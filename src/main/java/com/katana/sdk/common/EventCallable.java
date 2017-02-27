package com.katana.sdk.common;

/**
 * Created by juane on 2/8/17.
 */
public interface EventCallable<T extends Component> {
    /**
     * @param object
     * @return
     */
    T run(T object);
}
