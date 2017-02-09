package com.katana.common.utils;

import java.util.List;

/**
 * Created by juane on 2/9/17.
 */
public class OptionManager {

    private static final String HAS_BEEN_SET_MORE_THAN_ONCE = "has been set more than once";

    private static final String IS_REQUIRED = "is required";

    private static final String IS_NOT_VALID = "is not valid";
    
    private List<Option> options;

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void extractOptions(String[] args) {
        int[] optionCounts = new int[options.size()];

        for (int i = 0; i < args.length; i++) {
            if (args[i].charAt(0) == '-') {
                boolean exist = false;
                for (int j = 0; j < options.size() && !exist; j++) {
                    if (Utils.contain(options.get(j).getNames(), args[i])) {
                        if (options.get(j).isHasValue()) {
                            i++;
                            options.get(j).setValue(args[i]);
                        }
                        optionCounts[j]++;
                        exist = true;
                    }
                }
                if (!exist) {
                    throw new IllegalArgumentException(args[i] + " " + IS_NOT_VALID);
                }
            } else {
                throw new IllegalArgumentException(args[i] + " " + IS_NOT_VALID);
            }
        }

        validateConstrains(optionCounts);
    }

    public void validateConstrains(int[] optionCounts) {
        for (int j = 0; j < options.size(); j++) {
            if (options.get(j).isRequired() && optionCounts[j] == 0) {
                throw new IllegalArgumentException(getOptionName(options.get(j)) + " " + IS_REQUIRED);
            }

            if (options.get(j).isUnique() && optionCounts[j] > 1) {
                throw new IllegalArgumentException(getOptionName(options.get(j)) + " " + HAS_BEEN_SET_MORE_THAN_ONCE);
            }
        }
    }

    private String getOptionName(Option appOption) {
        return appOption.getNames()[0] + " or " + appOption.getNames()[1];
    }
}
