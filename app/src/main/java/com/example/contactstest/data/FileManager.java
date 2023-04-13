package com.example.contactstest.data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.OutputStream;

public class FileManager {
    private static final String fileName = "file.txt";
    private static final String mimeType = "text/plain";

    public static void Write(Context context, String text) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
        values.put(MediaStore.MediaColumns.MIME_TYPE, mimeType);
        ContentResolver resolver = context.getContentResolver();
        Uri uri = resolver.insert(MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY), values);
        try (OutputStream stream = resolver.openOutputStream(uri)) {
            stream.write(text.getBytes());
        } catch (Exception e) {
            Log.d("FF", "Write() returned: " + e);
        }

    }

}
