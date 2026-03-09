## Drawable

The `res/drawable` folder contains **graphical resources used by the Android application interface**.  
These resources are used to display visual elements such as icons, images, and graphical components throughout the application.

### Purpose

The files stored in this folder are responsible for:

- Displaying icons and images in the user interface
- Providing graphical assets for UI components
- Defining backgrounds and visual styling
- Supporting scalable graphics through vector drawables

This folder helps separate **visual resources from the application logic**, improving project organization and maintainability.

---

### Types of Resources

The `drawable` folder may contain different types of graphical files, such as:

- **PNG / JPG images**  
  Used for icons, illustrations, or interface graphics.

- **Vector Drawables (`.xml`)**  
  Scalable graphics defined using XML. These are resolution-independent and commonly used for icons.

- **Shape Drawables (`.xml`)**  
  XML files used to create simple shapes such as rectangles, circles, or borders for UI components.

---

### Example Usage

Drawable resources are typically referenced inside layout files.

Example:

```xml
<ImageView
    android:layout_width="48dp"
    android:layout_height="48dp"
    android:src="@drawable/drone_icon"/>
