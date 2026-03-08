### MapsController.java – Zone Map Controller

This class manages **restricted zones** for the map and provides location-based utilities.  
It interacts with the **Model_Zona** to load, filter, and analyze zones on the map.

Key responsibilities:
- **Load zones from JSON** into the model.  
- **Retrieve all restricted zones** for display.  
- **Find the nearest zone** to a given location (`LatLng`).  
- **Calculate distances** between points using the Haversine formula.  
- Supports both **circular and polygonal zones** and updates their distance from the current location.

package com.example.safetydrones.Controller;

import android.util.Log;
import com.example.safetydrones.Model.Model_Zona;
import com.google.android.gms.maps.model.LatLng;
import java.util.List;

public class MapsController {
    private final Model_Zona zonaModel;

    public MapsController(Model_Zona zonaModel) {
        this.zonaModel = zonaModel;
    }

    public void carregarZonas(String json) {
        if (json == null || json.trim().isEmpty()) {
            Log.e("MAPS_CONTROLLER", "JSON vazio ou nulo.");
            return;
        }
        zonaModel.carregarZonas(json);
    }

    public List<Model_Zona> getZonas() {
        return zonaModel.getZonasRestritas();
    }

    public Model_Zona getZonaMaisProxima(LatLng posicaoAtual) {
        List<Model_Zona> zonas = getZonas();
        double menorDistancia = Double.MAX_VALUE;
        Model_Zona maisProxima = null;

        for (Model_Zona zona : zonas) {
            LatLng centro = null;
          
            if ("Circle".equals(zona.getTipoGeometria())) {
                centro = zona.getCentro();
            } else if ("Polygon".equals(zona.getTipoGeometria()) && zona.getCoordenadasPoligono() != null && !zona.getCoordenadasPoligono().isEmpty()) {
                centro = zona.getCoordenadasPoligono().get(0);
            }
            if (centro == null) continue;

            double d = calcularDistanciaKm(posicaoAtual, centro);
            zona.setDistancia(d);

            if (d < menorDistancia) {
                menorDistancia = d;
                maisProxima = zona;
            }
        }

        return maisProxima;
    }

    private double calcularDistanciaKm(LatLng p1, LatLng p2) {
        double R = 6371; // raio da Terra em km
        double dLat = Math.toRadians(p2.latitude - p1.latitude);
        double dLon = Math.toRadians(p2.longitude - p1.longitude);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2)
                + Math.cos(Math.toRadians(p1.latitude)) * Math.cos(Math.toRadians(p2.latitude))
                * Math.sin(dLon/2) * Math.sin(dLon/2);
        return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }

    public void carregarZonasNoMapa(String json) {
        carregarZonas(json);
    }
}
