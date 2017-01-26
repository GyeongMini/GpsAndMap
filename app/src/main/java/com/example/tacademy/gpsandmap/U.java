package com.example.tacademy.gpsandmap;

import com.squareup.otto.Bus;

/**
 * Created by Tacademy on 2017-01-26.
 */
public class U {
    private static U ourInstance = new U();

    public static U getInstance() {
        return ourInstance;
    }

    private U() {
    }

    Bus bus = new Bus();

    public Bus getBus() {
        return bus;
    }
}