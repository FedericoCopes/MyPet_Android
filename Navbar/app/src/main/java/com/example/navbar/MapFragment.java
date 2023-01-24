package com.example.navbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MapFragment extends Fragment {

    @Nullable
    @org.jetbrains.annotations.Nullable


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Map");
        View view = inflater.inflate(R.layout.map_fragment,null);

        //initialize map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        //Async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                //Utili per la ricerca della poszione
                ArrayList<Double> listaPosizioni = new ArrayList();
                ArrayList<String> listaCittà = new ArrayList();
                ArrayList<String> listaNomi = new ArrayList();
                ArrayList<Double> listaPos = new ArrayList();
                ArrayList<Double> listaLat = new ArrayList();
                ArrayList<Double> listaLon = new ArrayList();
                double myLat = 46.1997;//Verceia
                double myLon = 9.456;//Verceia
                //When map is loaded
                googleMap.setMapType(com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE);

                InputStream is = getResources().openRawResource(R.raw.veterinari);
                try {
                    readXMLFile(is, myLat, myLon, listaPosizioni, listaCittà, listaNomi, listaLat, listaLon);
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                for(int i = 0; i <listaLat.size(); i++){
                    LatLng marker = new LatLng(listaLat.get(i),listaLon.get(i));
                    googleMap.addMarker(new MarkerOptions().position(marker).title(listaNomi.get(i)));
                }


                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                    }
                });
            }
        });

        return view;
    }

    //Lettura file xml
    public void readXMLFile(InputStream filePath, double myLat, double myLong, ArrayList<Double> listaPosizioni, ArrayList<String> listaCittà, ArrayList<String> listaNomi, ArrayList<Double> listaLat, ArrayList<Double> listaLon) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(filePath);
        doc.getDocumentElement().normalize();
        NodeList veicoloList = doc.getElementsByTagName("veterinario");
        for (int i = 0; i < veicoloList.getLength(); i++) {
            Node veicoloNode = veicoloList.item(i);
            if (veicoloNode.getNodeType() == Node.ELEMENT_NODE) {
                Element veicoloElement = (Element) veicoloNode;
                String nome = veicoloElement.getElementsByTagName("nome").item(0).getTextContent();
                listaNomi.add(nome);
                String citta = veicoloElement.getElementsByTagName("citta").item(0).getTextContent();
                String latitudine = veicoloElement.getElementsByTagName("latitudine").item(0).getTextContent();
                listaLat.add(Double.parseDouble(latitudine));
                String longitudine = veicoloElement.getElementsByTagName("longitudine").item(0).getTextContent();
                listaLon.add(Double.parseDouble(longitudine));
                //System.out.println(nome + " con sede a " + città + " in via " + via + " con posizione : " + latitudine + " / " + longitudine);
                //double lat = Double.parseDouble(latitudine);
                //double lon = Double.parseDouble(longitudine);
                //distance(lat, lon, myLat, myLong, listaPosizioni, listaCittà, listaNomi, città, nome);
            }
        }
    }
}