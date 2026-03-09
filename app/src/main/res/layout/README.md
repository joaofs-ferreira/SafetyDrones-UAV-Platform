## Layout

The `res/layout` folder contains **XML files that define the structure and appearance of the application's user interface (UI)**.

Each file in this folder represents the layout of a screen or a specific interface component used within the Android application.

---

### Purpose

The files stored in this folder are responsible for:

- Defining the structure of the application's user interface
- Organizing UI components on the screen
- Specifying the position, size, and behavior of visual elements
- Connecting interface elements with the application logic implemented in Java or Kotlin

By separating the UI structure from the program logic, Android applications become easier to maintain, update, and scale.

---

### Layout Components

Layout files can include different types of interface elements, such as:

- **TextView** – used to display text
- **Button** – interactive elements that trigger actions
- **ImageView** – used to display images
- **EditText** – input fields where users can type information

Layouts also use **containers (ViewGroups)** to organize elements, such as:

- **LinearLayout** – arranges elements vertically or horizontally
- **ConstraintLayout** – flexible layout used to position elements relative to each other
- **RelativeLayout** – positions elements relative to other views

---

### Example Layout

Example of a simple layout structure:

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Drone Status"/>

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Start Monitoring"/>

</LinearLayout>
