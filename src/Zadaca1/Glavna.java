package Zadaca1;
import java.io.File;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
class Vraboten{
    private String ime;
    private String prezime;
    private int plata;
    public Vraboten(){

    }
    public String getIme() {
        return ime;
    }
    public String getPrezime() {
        return prezime;
    }
    public int getPlata() {
        return plata;
    }
    public void setIme(String ime) {
        this.ime = ime;
    }
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    public void setPlata(int plata) {
        this.plata = plata;
    }
}
class XML{
    public void createXTMDoc(String name, Vraboten vraboten){
        try{
            DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder doc=docFactory.newDocumentBuilder();
            Document document=doc.newDocument();

            Element root=(Element) document.createElement("Vraboten");
            document.appendChild(root);
            Element ime=(Element) document.createElement("ime");
            Element prezime=(Element) document.createElement("prezime");
            Element plata=(Element) document.createElement("plata");

            root.appendChild(ime);
            root.appendChild(prezime);
            root.appendChild(plata);

            ime.appendChild(document.createTextNode(vraboten.getIme()));
            prezime.appendChild(document.createTextNode(vraboten.getPrezime()));
            plata.appendChild(document.createTextNode(String.valueOf(vraboten.getPlata())));

            document.appendChild(root);

            TransformerFactory transformerFactory=TransformerFactory.newInstance();
            Transformer transformer=transformerFactory.newTransformer();
            DOMSource source=new DOMSource(document);
            StreamResult result=new StreamResult(new File(name));
            transformer.transform(source,result);
            System.out.println("Kreiran e dokumentot so ime: "+name);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
public class Glavna {
    public static void main(String[] args){
        Vraboten v=new Vraboten();
        v.setIme("Stefan");
        v.setPrezime("Stefanovski");
        v.setPlata(10000);

        XML xml=new XML();
        xml.createXTMDoc("Vraboten.xml",v);
    }
}
