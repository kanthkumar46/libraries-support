package edu.junitsupport;

import java.io.InputStream;

/**
 * Created by KanthKumar on 3/9/17.
 */
public class TestUtils {

    public static InputStream getResourceStream(String file){
        return TestUtils.class.getResourceAsStream(file);
    }
}
