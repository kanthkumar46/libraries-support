package edu.junitsupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

/**
 * Created by KanthKumar on 2/24/17.
 */
public class TestSetup {

    private static final Logger LOGGER = LogManager.getLogger(TestSetup.class);
    private long startTime;

    @Rule
    public TestName name = new TestName();

    @Before
    public void beforeMethod() {
        startTime = System.currentTimeMillis();
        LOGGER.info("Stating test: "+name.getMethodName());
    }

    @After
    public void afterMethod() {
        LOGGER.info("Finished, time elapsed: "+(System.currentTimeMillis() - startTime)+"ms\n");
    }
}
