## Animations

Our project uses **animations to enhance user experience**, providing visual feedback and interactive elements without increasing app size significantly. Animations are implemented using **Lottie**, which allows JSON-based vector animations to be rendered natively in Android.

### How Animations Work
- Animations are stored as **JSON files** exported from tools like Adobe After Effects using the **Bodymovin** plugin.
- JSON files describe shapes, layers, keyframes, and timing.
- The app uses `LottieAnimationView` to load and play animations dynamically.

### Benefits of Using Lottie Animations
- **Lightweight:** Vector animations are smaller than video or GIFs.
- **Scalable:** Animations scale without losing quality.
- **Interactive:** You can control playback (start, pause, loop, progress) programmatically.
- **Reusable:** The same JSON file can be used across multiple screens or apps.
