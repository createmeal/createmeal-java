package createmeal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Node {
    List<String> attributes;
    List<Node> children;
    String name;
    String type;

    public Node(String name) {
        this.name = name;
        this.children = new ArrayList<>();
        this.attributes = new ArrayList<>();
    }
    public Node(String name,String type){
        this.name = name;
        this.type = type;
        this.children = new ArrayList<>();
        this.attributes = new ArrayList<>();
    }

    public void addChildren(List<Node> children) {
        if(children != null){
            this.children.addAll(children);
        }
    }

    public void setAttribute(String key, String value) {
        if (value == null) {
            this.attributes.add(key);
        }
        this.attributes.add(String.format("%s='%s'", key, value));
    }

    public String getAttributes() {
        return String.join(",", this.attributes);
    }

    public String getOpenTag() {
        if(this.type == "string"){
            return this.name;
        }
        if (this.attributes.size() > 0) {
            return String.format("<%s %s>", this.name, this.getAttributes());
        }
        return String.format("<%s>", this.name);
    }

    public String getCloseTag() {
        if(this.isSelfClosingTag() || this.type == "string"){
            return "";
        }
        return String.format("</%s>", this.name);
    }

    public String toHtml() {
        List<String> htmlChildren = this.children.stream().map(child -> child.toHtml()).collect(Collectors.toList());
        return this.getOpenTag() + String.join("", htmlChildren) + this.getCloseTag();
    }
    public boolean isSelfClosingTag(){
        return false;
    }
}
