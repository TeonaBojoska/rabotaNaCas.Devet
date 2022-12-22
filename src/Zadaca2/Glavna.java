package Zadaca2;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
class Vraboten{
    private String ime;
    private String prezime;
    private int plata;
    public Vraboten(String stefan, String stefanovski, int i){

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
class XML {
    public void createXMLDoc(String name, ArrayList<Vraboten> vraboten) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder doc = docFactory.newDocumentBuilder();
            org.w3c.dom.Document document = doc.newDocument();

            Element root = document.createElement("Vraboteni");
            document.appendChild(root);

            for (Vraboten v : vraboten) {
                Element vraboteni = document.createElement("vraboteni");
                root.appendChild(vraboteni);

                Element ime = document.createElement("ime");
                vraboteni.appendChild(ime);
                ime.appendChild(document.createTextNode(v.getIme()));

                Element prezime = document.createElement("prezime");
                vraboteni.appendChild(prezime);
                prezime.appendChild(document.createTextNode(v.getPrezime()));

                Element plata = (Element) document.createElement("plata");
                vraboteni.appendChild(plata);
                plata.appendChild(document.createTextNode(String.valueOf(v.getPlata())));
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(name));
            transformer.transform(source, result);
            System.out.println("Kreran file so ime " + name);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
public class Glavna {
        public static void main(String[] args){
            ArrayList<Vraboten> listaNaVraboteni=new ArrayList<Vraboten>();
            listaNaVraboteni.add(new Vraboten("Stefan","Stefanovski",10000));
            listaNaVraboteni.add(new Vraboten("Aleksandar","Ristevski",15000));

            XML xml=new XML();
            xml.createXMLDoc("VrabotenXML.xml",listaNaVraboteni);
        }
    }
