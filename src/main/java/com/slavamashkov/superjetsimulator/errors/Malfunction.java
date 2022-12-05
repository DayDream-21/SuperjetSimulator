package com.slavamashkov.superjetsimulator.errors;

public abstract class Malfunction {
    /*
    * This method should be executed after transition from landing-page to master-pane
    * */
    public abstract void executeMalfunction();

    /*
    * This method should be executed after pressing the "close" button
    * */
    public abstract void restoreSystem();
}
