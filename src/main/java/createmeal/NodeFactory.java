package createmeal;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class NodeFactory {
    List<Node> nodes;
    AttributeFactory attributeFactory;
    public NodeFactory(){
        this.nodes = new ArrayList<>();
        this.attributeFactory = new AttributeFactory();
    }

    /**
     * Create nodes by field of the object
     * @param jsd
     * @return
     */
    public String createMainNodes(JSONObject jsd){
        return jsd.toString();
    }

    /**
     * Create nodes by element of the array
     * @param jsd
     * @return
     */
    public String createMainNodes(JSONArray jsd){
        return jsd.toString();
    }

    /**
     * Transforms nodes recursively and append to main nodes
     * @param jsd
     */
    private void createNodes(JSONObject jsd){
        this.nodes.addAll(this.getNodes(jsd));
    }
    /**
     * Transforms nodes recursively and append to main nodes
     * @param jsd
     */
    private void createNodes(JSONArray jsd){
        for (Object obj: jsd) {
            if(obj instanceof String){
                this.nodes.addAll(this.getNodes((String)obj));
                continue;
            }
            if(obj instanceof JSONObject){
                this.nodes.addAll(this.getNodes((JSONObject)obj));
                continue;
            }
            if(obj instanceof JSONArray){
                this.nodes.addAll(this.getNodes((JSONArray)obj));
                continue;
            }
        }
    }
    public List<Node> getNodes(String jsd){
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(jsd));
        return nodes;
    }
    public List<Node> getNodes(JSONObject jsd){
        List<Node> nodes = new ArrayList<>();
        if(jsd == null){
            return nodes;
        }
        JSONArray keys = jsd.names();
        for(Object k: keys){
            String key = (String)k;
            String value = jsd.getString(key);

            if(this.attributeFactory.isFieldRepresentingAttributes(key)){
                continue;
            }

            Node node = new Node(key);
            node.addChildren(this.getNodes(value));
            this.attributeFactory.setAttributes(node,value);
            nodes.add(node);
        }
        return new ArrayList<>();
    }
    public List<Node> getNodes(JSONArray jsd){
        List<Node> nodes = new ArrayList<>();
        for (Object obj: jsd) {
            if(obj instanceof JSONObject){
                nodes.addAll(this.getNodes((JSONObject)obj));
            }
            if(obj instanceof JSONArray){
                nodes.addAll(this.getNodes((JSONArray)obj));
            }
        }
        return nodes;
    }
}
