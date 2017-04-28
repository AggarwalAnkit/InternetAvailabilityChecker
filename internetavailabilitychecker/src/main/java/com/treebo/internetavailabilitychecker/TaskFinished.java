package com.treebo.internetavailabilitychecker;

/**
 * Created by aa on 29/04/17.
 */

interface TaskFinished<T> {
    void onTaskFinished(T data);
}
