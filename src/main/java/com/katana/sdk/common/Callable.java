package com.katana.sdk.common;

import com.katana.api.commands.common.CommandArgument;

/**
 * Created by juan on 27/08/16.
 */
public interface Callable<T extends CommandArgument> {
    T run(T object);
}
