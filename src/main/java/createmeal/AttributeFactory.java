package createmeal;

import org.json.JSONObject;

public class AttributeFactory {
    JSONObject attributes;
    JSONObject tags;

    public AttributeFactory() {
        this.attributes = File.readJson("/attributes.json");
        this.tags = File.readJson("/tags.json");
    }

    /**
     * Check if the key is a JSD attribute field
     *
     * @param key
     * @return
     */
    public boolean isFieldRepresentingAttributes(String key) {
        if (key.startsWith("_")) {
            return true;
        }
        if (this.isAttribute(key)) {
            return true;
        }
        return key == "attributes";
    }

    /**
     * Check if the key is an HTML attribute and not HTML tag
     *
     * @param key
     * @return
     */
    public boolean isAttribute(String key) {
        return this.attributes.has(key) && !this.tags.has(key);
    }
    public void setAttributes(Node node){

    }
}
