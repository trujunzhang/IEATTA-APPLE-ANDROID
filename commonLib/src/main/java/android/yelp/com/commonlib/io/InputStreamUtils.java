package android.yelp.com.commonlib.io;

import java.io.*;
import java.util.*;

import org.apache.commons.io.IOUtils;

/**
 * Created by djzhang on 12/30/15.
 */
public class InputStreamUtils {

    private InputStreamUtils ()
    {
        //to enforce immutability and non-instantiation
    }

    /**
     * @return the contents of the file if present, new byte [] {} if empty, null if missing
     * You should Validate.notEmpty the return value if you require contents
     */
    public static byte [] toByteArray ( final InputStream is ) throws IOException
    {
        if ( is == null ) return null;

        return IOUtils.toByteArray( is );
    }

    /**
     * @return the contents of the file if present, "" if empty, null if missing
     * You should Validate.notEmpty the return value if you require contents
     */
    public static String toString ( final InputStream is ) throws IOException
    {
        if ( is == null ) return null;

        return IOUtils.toString( is );
    }

    /**
     * @return the contents of the file if present, new Properties() if empty, null if missing
     * You should Validate.notEmpty the return value if you require contents
     */
    public static Properties toProperties ( final InputStream is ) throws IOException
    {
        if ( is == null ) return null;

        final Properties p = new Properties();
        p.load( is );

        return p;
    }

    /**
     * @return the contents of the file if present, new HashMap() if empty, null if missing
     * You should Validate.notEmpty the return value if you require contents
     */
    public static Map<String,String> toMap ( final InputStream is ) throws IOException
    {
        if ( is == null ) return null;

        final Map<String,String> m = new HashMap<String,String>();

        for ( final Map.Entry<Object,Object> me : toProperties(is).entrySet() )
        {
            final Object key = me.getKey();
            final Object value = me.getValue();
            m.put( key.toString(), value.toString() );
        }

        return m;
    }

    /**
     * @return the contents of the file if present, new List() if empty, null if missing
     * You should Validate.notEmpty the return value if you require contents
     */
    public static List<String> toList ( final InputStream is, final String delimiter ) throws IOException
    {
        if ( is == null ) return null;

        final String [] values = IOUtils.toString( is ).split( delimiter );

        return Arrays.asList( values );
    }
}
