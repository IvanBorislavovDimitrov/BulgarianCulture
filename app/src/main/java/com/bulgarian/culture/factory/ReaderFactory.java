package com.bulgarian.culture.factory;

import android.content.Context;

import com.bulgarian.culture.io.FileReader;
import com.bulgarian.culture.io.Reader;

public final class ReaderFactory {

    private ReaderFactory() {

    }

    public static Reader getDefaultFileReader(String filename, Context context) {
        return new FileReader(filename, context);
    }

}
