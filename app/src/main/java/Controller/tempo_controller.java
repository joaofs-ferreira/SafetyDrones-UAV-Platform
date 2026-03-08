### TempoController.java

This class handles **weather data retrieval and processing** for the app.  
It communicates with a weather API via **Retrofit** and updates the **TempoView** accordingly.

Key responsibilities:
- **Fetch current weather and forecast data** from the Visual Crossing API.  
- **Update the view** with temperature, wind speed, and translated weather conditions.  
- Handle **API errors** gracefully with error messages.  
- **Generate URLs for weather icons** based on the condition and time of day.  
- Provides a clean separation between **network logic** and the **UI display**.

package com.example.safetydrones.Controller;

import com.example.safetydrones.Tempo.TempoResponde;
import com.example.safetydrones.Tempo.TempoService;
import com.example.safetydrones.Tempo.TempoView;
import com.example.safetydrones.Tempo.TraduzTempo;
import java.util.Calendar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TempoController {

        private TempoView tempoView;
        private TempoService tempoService;

        public TempoController(TempoView tempoView) {
            this.tempoView = tempoView;
            this.tempoService = new Retrofit.Builder()
                    .baseUrl("https://weather.visualcrossing.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(TempoService.class);
        }

        public void carregarDados() {
            tempoService.getWeather().enqueue(new Callback<TempoResponde>() {
                @Override
                public void onResponse(Call<TempoResponde> call, Response<TempoResponde> response) {
                    if (response.isSuccessful() && response.body() != null && !response.body().days.isEmpty()) {
                        TempoResponde.CurrentConditions agora = response.body().currentConditions;
                        tempoView.atualizarTemperatura(agora.temp, agora.windspeed, TraduzTempo.traduzir(agora.conditions), agora.conditions);
                        tempoView.atualizarData(response.body().days);
                    } else {
                        tempoView.mostrarErro("Dados indisponíveis");
                    }
                }

                @Override
                public void onFailure(Call<TempoResponde> call, Throwable t) {
                    tempoView.mostrarErro("Erro ao carregar dados");
                }
            });
        }

    public String obterIconUrl(String condicao) {

        int horaAtual = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        boolean isDia = (horaAtual >= 6 && horaAtual < 20);
        String iconName = TraduzTempo.getIconFileName(condicao, isDia);
        return "https://raw.githubusercontent.com/basmilius/weather-icons/dev/production/fill/png/64/" + iconName + ".png";
    }
}
