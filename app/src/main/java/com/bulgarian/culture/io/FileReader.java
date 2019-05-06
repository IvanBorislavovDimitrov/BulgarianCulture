package com.bulgarian.culture.io;

import android.content.Context;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;

import static com.bulgarian.culture.constants.Constants.QUESTIONS_FILENAME;

public class FileReader implements Reader {

    private String filename;
    private Context context;

    public FileReader(String filename, Context context) {
        this.filename = filename;
        this.context = context;
    }

    @Override
    public String readAll() {
        try (InputStream inputStream = context.getAssets().open(QUESTIONS_FILENAME)) {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getCause());
        }
    }

}
