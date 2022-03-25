package createmeal;

public class App {
    public static void main(String[] args){
        String jsd = "{html: { head: {},body: {p: 'hello world!'}}}";

        Document doc = new Document();
        String htmlString = doc.toHtml(jsd);
        System.out.println(jsd);
        System.out.println(htmlString);
    }
}
