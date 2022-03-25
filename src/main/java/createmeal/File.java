package createmeal;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;

public class File {
    public static JSONObject readJson(String fileName){
        InputStream content = File.class.getResourceAsStream(fileName);
        if (content == null) {
            throw new NullPointerException("Cannot find resource file " + fileName);
        }
        return new JSONObject(new JSONTokener(content));
    }
}