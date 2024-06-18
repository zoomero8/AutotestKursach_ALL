package config;

import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract public class LoadAPI_Tests {
    protected static final Logger logger = LoggerFactory.getLogger(LoadAPI_Tests.class);

    @BeforeEach
    public void setUp() {
        logger.info("Setting up the environment for API tests");
    }
}