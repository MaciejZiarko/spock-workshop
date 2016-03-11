package spockworkshop.basics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SomeClass {

    private static final Logger LOG = LoggerFactory.getLogger(SomeClass.class);

    public SomeClass() {
        LOG.info("Created new instance of SomeClass");
    }
}
