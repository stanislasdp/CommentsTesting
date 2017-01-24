package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by stas on 1/23/17.
 */
public class PropertyLoader  {

    private static final String PROP_FILE = "/home/stas/IdeaProjects/CommentsTesting/src/main/resources/Data.properties";

    public static String getProperty(String property) {

        if (property == null) {
            throw new NullPointerException("property is null");
        }
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(new File(PROP_FILE))) {
            properties.load(fileInputStream);

        }
        catch (IOException io) {
            io.printStackTrace();
        }
        return properties.getProperty(property);
    }
}
