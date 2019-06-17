package com.bulgarian.culture.factory;

import android.content.Context;

import com.bulgarian.culture.io.FileReader;
import com.bulgarian.culture.io.Reader;

public final class ReaderFactory {

    private static Reader reader;

    private ReaderFactory() {

    }

    public synchronized static Reader getDefaultFileReader(String filename, Context context) {
        if (reader == null) {
            reader = new FileReader(filename, context);
        }
        return reader;
    }

}
