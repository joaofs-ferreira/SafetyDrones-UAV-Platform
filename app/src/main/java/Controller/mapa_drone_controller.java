### MapaDroneController.java – Drone Map Controller

This class handles the **logic for managing and displaying drone zones** on the map.  
It connects the **MapaDrone view** with the **Model_Zona** to apply filters and update the map.

Key responsibilities:
- **Apply filters** to the loaded zones and update the map view.  
- **Load zones from JSON** and immediately apply the current filter.  
- Ensures the **map always reflects the filtered set of restricted zones**.  
- Handles invalid JSON inputs gracefully with logging.

package com.example.safetydrones.Controller;

import android.util.Log;
import com.example.safetydrones.Drones.MapaDrone;
import com.example.safetydrones.Model.Model_Zona;

public class MapaDroneController {

    private final MapaDrone view;
    private final Model_Zona zonaModel;

    public MapaDroneController(MapaDrone view, Model_Zona zonaModel) {
        this.view = view;
        this.zonaModel = zonaModel;
    }

    public void aplicarFiltro(String filtro) {
        zonaModel.aplicarFiltro(filtro);
        view.atualizarMapa(zonaModel.getZonasRestritas());
    }

    public void carregarZonasEAtualizarMapa(String filtroAtual, String json) {
        if (json == null || json.trim().isEmpty()) {
            Log.e("CONTROLLER", "JSON inválido ao tentar carregar e filtrar zonas.");
            return;
        }
        zonaModel.carregarZonas(json);
        aplicarFiltro(filtroAtual);
    }
}
