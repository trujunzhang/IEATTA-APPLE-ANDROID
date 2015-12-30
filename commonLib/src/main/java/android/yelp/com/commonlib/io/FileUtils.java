package android.yelp.com.commonlib.io;

import java.io.*;
import java.util.*;

import org.apache.commons.io.IOUtils;

/**
 * Created by djzhang on 12/30/15.
 */
public class FileUtils {

    private FileUtils() {
    }

    /**
     * @return the contents of the file if present, new byte [] {} if empty, null if missing
     * You should Validate.notEmpty the return value if you require contents
     */
    public static byte[] toByteArray(final String fileName) throws IOException {
        final InputStream is = new FileInputStream(fileName);
        try {
            return InputStreamUtils.toByteArray(is);
        } finally {
            is.close();
        }
    }

}
