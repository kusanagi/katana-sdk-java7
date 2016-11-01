package com.katana.sdk.sdk.components;

import com.katana.sdk.common.Logger;
import com.katana.sdk.components.Component;
import com.katana.sdk.components.Service;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by juan on 26/08/16.
 */

@RunWith(JUnit4.class)
public class ComponentTest {

    public static final String IS_REQUIRED = "is required";
    public static final String IS_NOT_VALID = "is not valid";
    public static final String HAS_MORE_THAN_ONE_VALUE = "has been set more than once";

    @Test
    public void main_withRequiredArgs_dontThrowException() {
        String[][] valid_cases = new String[][]{
                {"-c component -a action -n name -v version -p platform", null},
                {"-c component -a action --name name --version version --platform-version platform", null},
                {"-c component -a action -n name --version version --platform-version platform", null},
                {"-c component -a action --name name -v version --platform-version platform", null},
                {"-c component -a action --name name --version version -p platform", null},
        };

        assertOptions(valid_cases);
    }

    @Test
    public void main_withOptionalArgs_dontThrowException() {
        String[][] cases = new String[][]{
                {"-c component -a action -n name -v version -p platform -s socket", null},
                {"-c component -a action -n name -v version -p platform -D", null},
                {"-c component -a action -n name -v version -p platform -V var", null},
                {"-c component -a action -n name -v version -p platform --socket socket", null},
                {"-c component -a action -n name -v version -p platform --debug", null},
                {"-c component -a action -n name -v version -p platform --var var", null},
                {"-c component -a action -n name -v version -p platform -s socket --debug -V var", null},
                {"-c component -a action -n name -v version -p platform -s socket --debug -V var1 -V var2 --var var3", null},
        };

        assertOptions(cases);
    }

    @Test
    public void main_withoutRequiredArgs_throwException() {
        String[][] cases = new String[][]{
                {"-c component -a action -n name -v version", "-p or --platform-version " + IS_REQUIRED},
                {"-c component -a action -n name -p platform", "-v or --version " + IS_REQUIRED},
                {"-c component -a action -v version -p platform", "-n or --name " + IS_REQUIRED},
                {"-c component -a action -v version -p platform -l invalid option", "-l " + IS_NOT_VALID},
                {"-c component -a action -V var -s socket -D", "-n or --name " + IS_REQUIRED}
        };

        assertOptions(cases);
    }

    @Test
    public void main_withInvalidArgs_throwException() {
        String[][] cases = new String[][]{
                {"--name name -i invalid_option --version version -p platform", "-i " + IS_NOT_VALID},
                {"-c component -a action -n name --name name --version version -p platform", "-n or --name " + HAS_MORE_THAN_ONE_VALUE},
                {"--name name --version version -p platform version", "version " + IS_NOT_VALID},
                {"-c component -a action -n name -v version -p platform -s socket --socket socket2 --debug -V var", "-s or --socket " + HAS_MORE_THAN_ONE_VALUE},
                {"-c component -a action -n name -v version -p platform -s socket --debug -D -V var", "-D or --debug " + HAS_MORE_THAN_ONE_VALUE},
        };

        assertOptions(cases);
    }

    private void assertOptions(String[][] cases) {
        for (String[] aCase : cases) {
            try {
                new Service(aCase[0].split(" "));
                Assert.assertTrue(aCase[1] == null);
            } catch (IllegalArgumentException e) {
                Logger.log(e);
                Assert.assertEquals(aCase[1], e.getMessage());
            }
        }
    }

    @Test
    public void main_withValidArguments_setClassMembers() {
        String args = "-c component -a action -n name -v version -p platform -s socket --debug -V var1 -V var2 --var var3";
        Component component = new Service(args.split(" "));

        Assert.assertEquals("component", component.getComponentName());
        Assert.assertEquals("action", component.getAction());
        Assert.assertEquals("name", component.getName());
        Assert.assertEquals("version", component.getVersion());
        Assert.assertEquals("platform", component.getPlatformVersion());
        Assert.assertEquals(true, component.isDebug());
        Assert.assertEquals("socket", component.getSocket());
        Assert.assertEquals(3, component.getVar().size());
        Assert.assertEquals("var1", component.getVar().get(0));
        Assert.assertEquals("var2", component.getVar().get(1));
        Assert.assertEquals("var3", component.getVar().get(2));

    }

}
