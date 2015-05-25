package edu.chl.proximity.Models.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Linda Evaldsson
 * @date 2015-05-25
 */
public class DisposableCollector {

    private static List<ProximityDisposable> disposableList = new ArrayList<ProximityDisposable>();

    public static void add(ProximityDisposable disposable) {
        disposableList.add(disposable);
    }

    public static void disposeAll() {
        for(ProximityDisposable disposable : disposableList) {
            disposable.dispose();
        }
        disposableList = new ArrayList<ProximityDisposable>();
    }
}
