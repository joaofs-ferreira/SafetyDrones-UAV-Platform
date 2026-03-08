### Model_FirstPage.java – Home Navigation Model

`Model_FirstPage` is a **simple data model** used to store the current navigation state of the application's main page.

Key responsibilities:
- Maintains the **ID of the currently selected item** from the bottom navigation menu.
- Provides a **getter method (`getSelectedItemId`)** to retrieve the selected navigation item.
- Provides a **setter method (`setSelectedItemId`)** to update the selected navigation item.

Role in the architecture:
- Works as part of the **MVC pattern**, where:
  - **Model_FirstPage** stores the navigation state.
  - **FirstPageController** manages navigation logic.
  - **FirstPage (View)** updates the user interface.

Overall, `Model_FirstPage` acts as a **state holder for navigation selection**, allowing the controller and view to manage and update the displayed content consistently.

package com.example.safetydrones.Model;

public class Model_FirstPage {

    private int selectedItemId;

    public int getSelectedItemId() {
        return selectedItemId;
    }

    public void setSelectedItemId(int selectedItemId) {
        this.selectedItemId = selectedItemId;
    }

}
