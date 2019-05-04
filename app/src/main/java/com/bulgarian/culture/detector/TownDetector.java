package com.bulgarian.culture.detector;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class TownDetector {

    private final Geocoder geocoder;
    private double lat;
    private double lon;

    public TownDetector(Context context, double lat, double lon) {
        geocoder = new Geocoder(context, Locale.getDefault());
        this.lat = lat;
        this.lon = lon;
    }

    public String getCurrentTownName() throws IOException {
        List<Address> addresses = geocoder.getFromLocation(lat, lon, 1);
        if (addresses == null || addresses.isEmpty()) {
            throw new IllegalArgumentException("Town not found! -> Setup emulated coordinates (emulator settings)");
        }
        return addresses.get(0).getLocality();
    }
}
