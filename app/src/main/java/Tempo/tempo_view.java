##TempoView.java
Interface defining the view for weather updates in the app.

- `atualizarTemperatura(double temp, double windspeed, String conditions, String condicao)`  
  Updates the current temperature, wind speed, and weather conditions on the UI.

- `atualizarData(List<TempoResponde.Day> previsaoSemana)`  
  Updates the UI with the weekly weather forecast.

- `mostrarErro(String mensagem)`  
  Displays an error message when weather data cannot be loaded.

package com.example.safetydrones.Tempo;

import java.util.List;

public interface TempoView {
    void atualizarTemperatura(double temp, double windspeed, String conditions, String condicao);
    void atualizarData(List<TempoResponde.Day> previsaoSemana);
    void mostrarErro(String mensagem);
}
