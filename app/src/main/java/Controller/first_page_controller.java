### FirstPageController.java

This class manages the **navigation logic** for the home screen of the app.  
It connects the **FirstPage view** with the **Model_FirstPage** to handle user interactions.

Key responsibilities:
- Tracks the **currently selected navigation item** in the model.  
- Updates the **view** based on the user's selection.  
- Keeps the **UI state and model in sync** for the home screen navigation.

package com.example.safetydrones.Controller;
import com.example.safetydrones.Home.FirstPage;
import com.example.safetydrones.Model.Model_FirstPage;

public class FirstPageController {

        private FirstPage view;
        private Model_FirstPage model;

        public FirstPageController(FirstPage view, Model_FirstPage model) {
            this.view = view;
            this.model = model;
        }

        public void handleNavigation(int itemId) {
            model.setSelectedItemId(itemId);
            view.updateViewBasedOnSelection(itemId);
        }
}
