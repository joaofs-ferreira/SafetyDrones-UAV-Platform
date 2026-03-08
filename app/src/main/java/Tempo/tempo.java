### Tempo.java – Weather Fragment

`Tempo` is a **fragment responsible for displaying weather information** in the Safety Drones application.  
It follows the **MVC pattern**, where the `TempoController` handles data retrieval and business logic, while this fragment is the **view layer**.

Key responsibilities:
- **Display current weather:**
  - Temperature and wind speed
  - Weather conditions (e.g., sunny, cloudy)
  - Weather icon (loaded via Glide)
  - Current date and time in localized format (Portuguese, Brazil)
  
- **Display weekly forecast:**
  - Uses a `RecyclerView` with a `PrevisaoAdapter` to show daily forecasts.

- **Interface with the controller:**
  - Calls `tempoController.carregarDados()` to fetch weather data.
  - Implements the `TempoView` interface to update UI when data is ready:
    - `atualizarTemperatura(...)` – updates the current weather view.
    - `atualizarData(...)` – updates the weekly forecast.
    - `mostrarErro(...)` – shows error messages if data retrieval fails.

- **UI enhancements:**
  - Formats date/time to `"Agora: dia DD, EEEE - HH:mm"` in Portuguese.
  - Capitalizes the first letter of the day of the week.
  - Dynamically loads weather icons from URLs using Glide.

This fragment is fully responsible for **presenting the weather information** to the user, while delegating data fetching and processing to the controller.

package com.example.safetydrones.Tempo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.example.safetydrones.Controller.TempoController;
import com.example.safetydrones.R;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Tempo extends Fragment implements TempoView {

    private TextView tempTextView, conditionsTextView, dataHojeTextView;
    private ImageView iconImageView;
    private TempoController tempoController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tempo, container, false);

        tempTextView = rootView.findViewById(R.id.tempTextView);
        conditionsTextView = rootView.findViewById(R.id.conditionsTextView);
        dataHojeTextView = rootView.findViewById(R.id.dataHojeTextView);
        iconImageView = rootView.findViewById(R.id.iconImageView);

        RecyclerView previsaoRecyclerView = rootView.findViewById(R.id.previsaoRecyclerView);
        previsaoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        tempoController = new TempoController(this);
        tempoController.carregarDados();

        return rootView;
    }

    @Override
    public void atualizarTemperatura(double temp, double windspeed, String conditions, String condicao) {
        tempTextView.setText("Sensação Térmica: " + temp + "°C\nVento: " + windspeed + " km/h");
        conditionsTextView.setText("Condições: " + conditions);
        String iconUrl = tempoController.obterIconUrl(condicao);
        Log.d("condição", condicao);
      
        Date agora = new Date();
        SimpleDateFormat diaFormat = new SimpleDateFormat("dd", new Locale("pt", "BR"));
        SimpleDateFormat diaSemanaHoraFormat = new SimpleDateFormat("EEEE - HH:mm", new Locale("pt", "BR"));

        String dia = diaFormat.format(agora);
        String diaSemanaHora = diaSemanaHoraFormat.format(agora);
      
        diaSemanaHora = Character.toUpperCase(diaSemanaHora.charAt(0)) + diaSemanaHora.substring(1);

        dataHojeTextView.setText("Agora: dia " + dia + ", " + diaSemanaHora);

        Glide.with(getContext())
                .load(iconUrl)
                .into(iconImageView);
    }

    @Override
    public void atualizarData(List<TempoResponde.Day> previsaoSemana) {
        PrevisaoAdapter adapter = new PrevisaoAdapter(previsaoSemana);
        RecyclerView previsaoRecyclerView = getView().findViewById(R.id.previsaoRecyclerView);
        previsaoRecyclerView.setAdapter(adapter);
    }

    @Override
    public void mostrarErro(String mensagem) {
        tempTextView.setText(mensagem);
    }
}

