package com.slavamashkov.superjetsimulator.malfunctions;

/**
 *
 */
public abstract class Malfunction {
    /**
     * This method should be executed after transition from landing-page to master-pane
     */
    public abstract void executeMalfunction();

    /**
     * This method should be executed after pressing the "close" button
     */
    public abstract void restoreSystem();

    protected void waitStep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
