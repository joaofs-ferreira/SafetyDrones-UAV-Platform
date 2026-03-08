### Model_Drone.java 

`Model_Drone` represents the **data structure for a drone** in the Safety Drones application. 
It is responsible for storing drone information, validating input data, and interacting with **Firebase Realtime Database** for drone-related operations.

Key responsibilities:
- Defines the **main attributes of a drone**:
  - Name (`Nome`)
  - Model (`Modelo`)
  - Weight (`Peso`)
  - Autonomy (`Autonomia`)
  - Maximum speed (`Velocidade`)
  - Category (`Categoria`)
  - Unique identifier (`id`)
- Uses **Firebase annotations (`@PropertyName`)** to correctly map object fields to database attributes.
- Automatically determines the **drone category (C0–C4)** based on weight and speed according to drone classification rules.
- Provides **getters and setters** for all drone properties.
- Implements **data validation** through `validarDrone()`:
  - Ensures all required fields are filled.
  - Verifies that numeric values are valid.
  - Ensures weight, autonomy, and speed are positive numbers.
- Includes helper methods to verify **numeric and integer values**.

Firebase database operations supported:
- **Add Drone (`adicionaDrone`)**
  - Generates a unique drone ID.
  - Stores drone data in the user's Firebase database node.

- **Update Drone (`atualizarDrone`)**
  - Updates existing drone information in Firebase.

- **Delete Drone (`eliminarDrone`)**
  - Removes a drone entry from Firebase using its unique ID.

- Uses a **callback interface (`FirebaseCallback`)** to return operation results:
  - `onSuccess()` for successful database operations.
  - `onFailure()` when an error occurs.

Overall, `Model_Drone` acts as both a **data model and Firebase interaction layer**, ensuring drone information is validated, categorized, and correctly stored in the database.

package com.example.safetydrones.Model;

import android.text.TextUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;
import java.util.Map;
import java.util.HashMap;

@IgnoreExtraProperties
public class Model_Drone {

    @PropertyName("Nome")
    private String nome;

    @PropertyName("Modelo")
    private String modelo;

    @PropertyName("Peso")
    private double peso;

    @PropertyName("Autonomia")
    private int autonomia;

    @PropertyName("Velocidade")
    private double velocidade;

    private String categoria;

    private String id;

    public Model_Drone() {
    }

    public Model_Drone(String nome, String modelo, double peso, int autonomia, double velocidade) {
        this.nome = nome;
        this.modelo = modelo;
        this.peso = peso;
        this.autonomia = autonomia;
        this.velocidade = velocidade;
        this.categoria = determinarCategoria();
    }

    private String determinarCategoria() {
        if (peso < 0.25 && velocidade < 19) {
            return "C0";
        } else if (peso < 0.9) {
            return "C1";
        } else if (peso < 4) {
            return "C2";
        } else if (peso <= 25) {
            return "C3 & C4";
        } else {
            return "Desconhecida";
        }
    }

    @PropertyName("Nome")
    public String getNome() {
        return nome;
    }

    @PropertyName("Nome")
    public void setNome(String nome) {
        this.nome = nome;
    }

    @PropertyName("Modelo")
    public String getModelo() {
        return modelo;
    }

    @PropertyName("Modelo")
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @PropertyName("Peso")
    public double getPeso() {
        return peso;
    }

    @PropertyName("Peso")
    public void setPeso(double peso) {
        this.peso = peso;
    }

    @PropertyName("Autonomia")
    public int getAutonomia() {
        return autonomia;
    }

    @PropertyName("Autonomia")
    public void setAutonomia(int autonomia) {
        this.autonomia = autonomia;
    }

    @PropertyName("Velocidade")
    public double getVelocidade() {
        return velocidade;
    }

    @PropertyName("Velocidade")
    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    @Override
    public String toString() {
        return "Model_Drone{" +
                "nome='" + nome + '\'' +
                ", modelo='" + modelo + '\'' +
                ", peso=" + peso +
                ", autonomia=" + autonomia +
                ", velocidade=" + velocidade +
                ", categoria='" + categoria + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public static String validarDrone(String nome, String modelo, String pesoStr, String autonomiaStr, String velocidadeStr) {
        if (TextUtils.isEmpty(nome) || TextUtils.isEmpty(modelo) || TextUtils.isEmpty(pesoStr)
                || TextUtils.isEmpty(autonomiaStr) || TextUtils.isEmpty(velocidadeStr)) {
            return "Por favor, preencha todos os campos!";
        }

        if (!isNumeric(pesoStr) || !isNumeric(velocidadeStr) || !isInteger(autonomiaStr)) {
            return "Peso e velocidade devem ser números válidos!\nAutonomia deve ser um número inteiro.";
        }

        double peso = Double.parseDouble(pesoStr);
        double velocidade = Double.parseDouble(velocidadeStr);
        int autonomia = Integer.parseInt(autonomiaStr);

        if (peso <= 0 || velocidade <= 0 || autonomia <= 0) {
            return "Peso, velocidade e autonomia devem ser valores positivos!";
        }

        return null; 
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private static boolean isInteger(String str) {
        return str.matches("-?\\d+");
    }


    public static void adicionaDrone(String userId, String nome, String modelo, double peso, int autonomia, double velocidade, DatabaseReference mDatabase, FirebaseCallback callback) {
        String droneId = mDatabase.child(userId).push().getKey();
        Map<String, Object> droneData = new HashMap<>();
        droneData.put("Nome", nome);
        droneData.put("Modelo", modelo);
        droneData.put("Peso", peso);
        droneData.put("Autonomia", autonomia);
        droneData.put("Velocidade", velocidade);

        assert droneId != null;
        mDatabase.child(userId).child(droneId).setValue(droneData)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(droneId);
                    } else {
                        callback.onFailure(task.getException().getMessage());
                    }
                });
    }

    public static void atualizarDrone(String userId, String droneId, String nome, String modelo, double peso, int autonomia, double velocidade, DatabaseReference mDatabase, FirebaseCallback callback) {
        Map<String, Object> droneData = new HashMap<>();
        droneData.put("Nome", nome);
        droneData.put("Modelo", modelo);
        droneData.put("Peso", peso);
        droneData.put("Autonomia", autonomia);
        droneData.put("Velocidade", velocidade);

        mDatabase.child(userId).child(droneId).updateChildren(droneData)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess("Drone atualizado com sucesso!");
                    } else {
                        callback.onFailure("Erro ao atualizar drone!");
                    }
                });
    }


    public void eliminarDrone(DatabaseReference mDatabase, FirebaseAuth mAuth, FirebaseCallback callback) {
        if (this.id != null) {
           
            mDatabase.child(mAuth.getCurrentUser().getUid()).child(this.id).removeValue()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            callback.onSuccess("Drone eliminado com sucesso");
                        } else {
                            callback.onFailure("Erro ao eliminar o drone");
                        }
                    });
        } else {
            callback.onFailure("Erro: ID do drone não encontrado.");
        }
    }
    public interface FirebaseCallback {
        void onSuccess(String droneId);
        void onFailure(String error);
    }

}
