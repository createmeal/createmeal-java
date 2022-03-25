package createmeal;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
        List<Node> nodes = new ArrayList<>();
        if(isJSONArray(jsd)){
            nodes.addAll(this.nodeFactory.createMainNodes(toJSONArray(jsd)));
        }
        else if(isJSONObject(jsd)){
            nodes.addAll(this.nodeFactory.createMainNodes(toJSONObject(jsd)));
        }
        String html = getString(nodes);
        if (html != null) return html;
        return jsd;
    }

    private String getString(List<Node> nodes) {
        if(nodes.size()>0){
            List<String> html = new ArrayList<>();
            for(Object n: nodes){
                if(n instanceof Node){
                    Node node = (Node)n;
                    html.add(node.toHtml());
                }
                return String.join("", html);
            }
        }
        return null;
    }

    /**
     * Transforms a JSD object to serialized HTML
     * @param jsd
     * @return
     */
    public String toHtml(JSONObject jsd){
        List<Node> nodes = this.nodeFactory.createMainNodes(jsd);
        String html = getString(nodes);
        if (html != null) return html;
        return jsd.toString();
    }

    /**
     * Transforms a JSD array to serialized HTML
     * @param jsd
     * @return
     */
    public String toHtml(JSONArray jsd){
        List<Node> nodes = this.nodeFactory.createMainNodes(jsd);
        String html = getString(nodes);
        if (html != null) return html;
        return jsd.toString();
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