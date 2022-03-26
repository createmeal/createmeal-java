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
    public List<Node> createMainNodes(JSONObject jsd){
        this.createNodes(jsd);
        return this.nodes;
    }

    /**
     * Create nodes by element of the array
     * @param jsd
     * @return
     */
    public List<Node> createMainNodes(JSONArray jsd){
        this.createNodes(jsd);
        return this.nodes;
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
        nodes.add(new Node(jsd,"string"));
        return nodes;
    }
    public List<Node> getNodes(JSONObject jsd){
        List<Node> nodes = new ArrayList<>();
        JSONArray keys = jsd.names();
        if(jsd == null || keys == null){
            return nodes;
        }

        for(Object k: keys){
            String key = (String)k;
            Object value = jsd.get(key);

            if(this.attributeFactory.isFieldRepresentingAttributes(key)){
                continue;
            }

            Node node = new Node(key);
            if(value instanceof String){
                node.addChildren(this.getNodes((String)value));
            }
            else if(value instanceof JSONObject){
                node.addChildren(this.getNodes((JSONObject)value));
            }
            else if(value instanceof JSONArray){
                node.addChildren(this.getNodes((JSONArray)value));
            }

            this.attributeFactory.setAttributes(node);
            nodes.add(node);
        }
        return nodes;
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
