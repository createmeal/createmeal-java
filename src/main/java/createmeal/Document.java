package createmeal;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Document {
    private NodeFactory nodeFactory;
    public Document (){
        this.nodeFactory = new NodeFactory();
    }

    /**
     * Transforms a serialized JSD to serialized HTML
     * @param jsd
     * @return
     */
    public String toHtml(String jsd){
        if(isJSONArray(jsd)){
            return this.nodeFactory.createMainNodes(toJSONArray(jsd));
        }
        else if(isJSONObject(jsd)){
            return this.nodeFactory.createMainNodes(toJSONObject(jsd));
        }
        return jsd;
    }

    /**
     * Transforms a JSD object to serialized HTML
     * @param jsd
     * @return
     */
    public String toHtml(JSONObject jsd){
        return this.nodeFactory.createMainNodes(jsd);
    }

    /**
     * Transforms a JSD array to serialized HTML
     * @param jsd
     * @return
     */
    public String toHtml(JSONArray jsd){
        return this.nodeFactory.createMainNodes(jsd);
    }
    private static JSONObject toJSONObject(String jsd){
        try {
            return new JSONObject(jsd);
        }
        catch(JSONException ex){
            throw ex;
        }
    }
    private static JSONArray toJSONArray(String jsd){
        try {
            return new JSONArray(jsd);
        }
        catch(JSONException ex){
            throw ex;
        }
    }
    private static boolean isJSONObject(String value){
        try{
            new JSONObject(value);
        }
        catch(JSONException ex){
            return false;
        }
        return true;
    }
    private static boolean isJSONArray(String value){
        try{
            new JSONArray(value);
        }
        catch(JSONException ex){
            return false;
        }
        return true;
    }
}