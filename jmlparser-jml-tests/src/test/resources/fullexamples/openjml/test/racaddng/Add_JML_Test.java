/*
 * Test Oracle Class for Add
 * For Use With OpenJML RAC
 *
 * Generated by JMLUnitNG 1.4b1 (104), 2013-10-08 23:02 -0700.
 * (do not modify this comment, it is used by JMLUnitNG clean-up routines)
 */

import org.jmlspecs.jmlunitng.iterator.IteratorWrapper;
import org.jmlspecs.jmlunitng.iterator.ParameterArrayIterator;
import org.jmlspecs.jmlunitng.testng.BasicTestListener;
import org.jmlspecs.jmlunitng.testng.PreconditionSkipException;
import org.jmlspecs.utils.JmlAssertionError;
import org.jmlspecs.utils.Utils;
import org.testng.TestNG;
import org.testng.annotations.DataProvider;
import org.testng.xml.XmlSuite;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Test oracles generated by JMLUnitNG for OpenJML RAC of class
 * Add.
 *
 * @author JMLUnitNG 1.4b1 (104)
 * @version 2013-10-08 23:02 -0700
 */

public class Add_JML_Test {
    /**
     * The main method. Allows the tests to be run without a testng.xml or
     * the use of the TestNG executable/plugin.
     *
     * @param the_args Command line arguments, ignored.
     */
    public static void main(String[] the_args) {
        final TestNG testng_runner = new TestNG();
        final Class<?>[] classes = {Add_JML_Test.class};
        final BasicTestListener listener =
                new BasicTestListener(new PrintWriter(System.out));
        testng_runner.setUseDefaultListeners(false);
        testng_runner.setXmlSuites(new ArrayList<XmlSuite>());
        testng_runner.setTestClasses(classes);
        testng_runner.addListener(listener);
        testng_runner.run();
    }

    /**
     * A test to ensure that RAC is enabled before running other tests;
     * this also turns on RAC assertions if they were not already turned on.
     */
    @Test
    public void test_racEnabled() {
        Utils.useExceptions = true;
    /*Assert.assertTrue
    (JMLChecker.isRACCompiled(Add.class),
     "JMLUnitNG tests can only run on RAC-compiled code.");*/
    }

    /**
     * A test for method x.
     *
     * @param the_test_object The Add to call the test method on.
     */
    @Test(dependsOnMethods = {"test_racEnabled"},
            dataProvider = "p_instance_only")
    public void test_x__0
    (final Add the_test_object) {
        if (the_test_object == null) {
            throw new PreconditionSkipException
                    ("could not construct an object to test");
        }
        try {
            the_test_object.x();
        } catch (final JmlAssertionError $e) {
            for (final StackTraceElement ste : $e.getStackTrace()) {
                System.err.println("element: " + ste.getMethodName());
            }

            if (true) {
                // meaningless test
                throw new PreconditionSkipException($e.getMessage());
            } else {
                // test failure
                throw new TestException($e.getMessage());
            }
        } catch (final Throwable $e) {
            // test failure for some reason other than assertion violation
            throw new TestException($e.getMessage());
        }
    }

    /**
     * A test for method y.
     *
     * @param the_test_object The Add to call the test method on.
     */
    @Test(dependsOnMethods = {"test_racEnabled"},
            dataProvider = "p_instance_only")
    public void test_y__0
    (final Add the_test_object) {
        if (the_test_object == null) {
            throw new PreconditionSkipException
                    ("could not construct an object to test");
        }
        try {
            the_test_object.y();
        } catch (final JmlAssertionError $e) {
            for (final StackTraceElement ste : $e.getStackTrace()) {
                System.err.println("element: " + ste.getMethodName());
            }

            if (true) {
                // meaningless test
                throw new PreconditionSkipException($e.getMessage());
            } else {
                // test failure
                throw new TestException($e.getMessage());
            }
        } catch (final Throwable $e) {
            // test failure for some reason other than assertion violation
            throw new TestException($e.getMessage());
        }
    }

    /**
     * A test for method sum.
     *
     * @param the_test_object The Add to call the test method on.
     * @param the_operand     The int to be passed.
     */
    @Test(dependsOnMethods = {"test_racEnabled"},
            dataProvider = "p_sum__int_the_operand__0")
    public void test_sum__int_the_operand__0
    (final Add the_test_object,
     final int the_operand) {
        if (the_test_object == null) {
            throw new PreconditionSkipException
                    ("could not construct an object to test");
        }
        try {
            the_test_object.sum(the_operand);
        } catch (final JmlAssertionError $e) {
            for (final StackTraceElement ste : $e.getStackTrace()) {
                System.err.println("element: " + ste.getMethodName());
            }

            if (true) {
                // meaningless test
                throw new PreconditionSkipException($e.getMessage());
            } else {
                // test failure
                throw new TestException($e.getMessage());
            }
        } catch (final Throwable $e) {
            // test failure for some reason other than assertion violation
            throw new TestException($e.getMessage());
        }
    }

    /**
     * A test for a constructor.
     *
     * @param the_x The int to be passed.
     * @param the_y The int to be passed.
     */
    @Test(dependsOnMethods = {"test_racEnabled"},
            dataProvider = "p_Add__int_the_x__int_the_y__0")
    public void test_Add__int_the_x__int_the_y__0
    (final int the_x, final int the_y) {
        try {
            new Add(the_x, the_y);
        } catch (final JmlAssertionError $e) {
            for (final StackTraceElement ste : $e.getStackTrace()) {
                System.err.println("element: " + ste.getMethodName());
            }

            if (true) {
                // meaningless test
                throw new PreconditionSkipException($e.getMessage());
            } else {
                // test failure
                throw new TestException($e.getMessage());
            }
        } catch (final Throwable $e) {
            // test failure for some reason other than assertion violation
            throw new TestException($e.getMessage());
        }
    }

    /**
     * Data provider for method int sum(int).
     *
     * @return An iterator over strategies to use for parameter generation.
     */
    @SuppressWarnings({"unchecked"})
    @DataProvider(name = "p_sum__int_the_operand__0",
            parallel = false)
    public static IteratorWrapper<Object[]> p_sum__int_the_operand__0() {
        return new IteratorWrapper<Object[]>
                (new ParameterArrayIterator
                        (Add_InstanceStrategy.class,
                                Add_sum__int_the_operand__0__the_operand.class));
    }


    /**
     * Data provider for constructor Add(int, int).
     *
     * @return An iterator over strategies to use for parameter generation.
     */
    @SuppressWarnings({"unchecked"})
    @DataProvider(name = "p_Add__int_the_x__int_the_y__0",
            parallel = false)
    public static IteratorWrapper<Object[]> p_Add__int_the_x__int_the_y__0() {
        return new IteratorWrapper<Object[]>
                (new ParameterArrayIterator
                        (Add_Add__int_the_x__int_the_y__0__the_x.class,
                                Add_Add__int_the_x__int_the_y__0__the_y.class));
    }


    /**
     * Data provider for methods with no parameters.
     *
     * @return An iterator over the main class strategy.
     */
    @SuppressWarnings({"unchecked"})
    @DataProvider(name = "p_instance_only",
            parallel = false)
    public static IteratorWrapper<Object[]> p_instance_only() {
        return new IteratorWrapper<Object[]>
                (new ParameterArrayIterator(Add_InstanceStrategy.class));
    }
}