package org.glassfish.jersey.server.spring;

import java.util.logging.Logger;

/**
 * @author SamH [Mar-28-2015]
 */
public class ContextLoadWaiter {

    private final static Logger logger = Logger.getLogger(ContextLoadWaiter.class.getName());
    private static boolean goodToGo = false;

    /**
     * When our Async Loader is done, give the go to Jersey to continue
     */
    public void setGoodToGo() {
        goodToGo = true;
    }

    public void doWait() {
        try {
            while (!goodToGo) {
               logger.info("Waiting for ContextLoader to start...");
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            logger.info("SpringComponentProvider was interrupted!");
        }
    }
}
