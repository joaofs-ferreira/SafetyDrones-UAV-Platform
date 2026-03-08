### DroneRepository.java 
  
`DroneRepository` is responsible for **retrieving drone data from Firebase Realtime Database** and providing it to other components of the application. 
It acts as a **data access layer** between the database and the rest of the system.

Key responsibilities:
- Initializes **Firebase Authentication (`FirebaseAuth`)** and a **Firebase Realtime Database reference** to the `"Drones"` node.
- Ensures that a **user is authenticated** before accessing drone data.
- Retrieves **all drones associated with the current user ID** from the database.
- Converts the retrieved Firebase data into **`Model_Drone` objects**.
- Handles potential **missing values** (e.g., weight or speed) by assigning default values.
- Stores the **Firebase database key as the drone ID** in the model.
- Supports optional drone attributes such as **category (`Categoria`)**.
- Returns the results asynchronously using a **callback interface (`DataCallback`)**:
  - `onSuccess(List<Model_Drone>)` when data is successfully retrieved.
  - `onError(String)` when an error occurs.

Overall, `DroneRepository` provides a **clean abstraction for database operations**, separating Firebase data management from the UI and controller logic.

package com.example.safetydrones.Model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;
import java.util.ArrayList;
import java.util.List;

public class DroneRepository {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    public DroneRepository() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Drones");
    }

    public void getDrones(DataCallback callback) {
        if (mAuth.getCurrentUser() == null) {
            callback.onError("Utilizador não autenticado.");
            return;
        }

        String userId = mAuth.getCurrentUser().getUid();

        mDatabase.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Model_Drone> droneList = new ArrayList<>();
                for (DataSnapshot droneSnapshot : dataSnapshot.getChildren()) {
                    String nome = droneSnapshot.child("Nome").getValue(String.class);
                    String modelo = droneSnapshot.child("Modelo").getValue(String.class);
                    Double peso = droneSnapshot.child("Peso").getValue(Double.class);
                    Integer autonomia = droneSnapshot.child("Autonomia").getValue(Integer.class);
                    Double velocidade = droneSnapshot.child("Velocidade").getValue(Double.class);

                    if (peso == null) peso = 0.0;
                    if (velocidade == null) velocidade = 0.0;

                    Model_Drone drone = new Model_Drone(nome, modelo, peso, autonomia, velocidade);
                    drone.setId(droneSnapshot.getKey());

                    if (droneSnapshot.hasChild("Categoria")) {
                        drone.setCategoria(droneSnapshot.child("Categoria").getValue(String.class));
                    }

                    droneList.add(drone);
                }
                callback.onSuccess(droneList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onError("Erro ao carregar dados do drone: " + databaseError.getMessage());
            }
        });
    }

    public interface DataCallback {
        void onSuccess(List<Model_Drone> droneList);
        void onError(String errorMessage);
    }
}
