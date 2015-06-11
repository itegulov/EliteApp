package ru.ifmo.ctddev.elite.core;

import java.rmi.Remote;

/**
 * @author Daniyar Itegulov
 */
@FunctionalInterface
public interface RefreshListener extends Remote {
    void onRefresh();
}
