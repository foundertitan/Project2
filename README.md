# 👟 ShoeStore – Android App

ShoeStore is a native Android application for browsing and purchasing shoes.  
It provides a smooth shopping experience—from browsing products to secure sign-in and checkout.

---

## ✨ Features

- **Modular Architecture with Activities**  
  - `BaseActivity.java` centralizes common UI elements (toolbar, navigation) and reduces duplicate code across activities.
  - `MainActivity.java` loads the home screen and retrieves product data/images from Firebase.

- **Dynamic Product Loading**
  - `ProductActivity.java` fetches shoe listings and images stored in **Firebase Storage**.
  - Uses RecyclerView/Adapter pattern for efficient scrolling and updates.

- **Detailed Product View**
  - `DetailActivity.java` receives product data through Intent extras and displays details such as price, description, and size.
  - Implements click listeners for adding to cart/checkout.

- **Secure User Authentication**
  - Firebase Authentication code handles sign-up, sign-in, and session management.
  - Email/Password provider enabled; logic included to manage user state and error handling.

- **Order Confirmation Workflow**
  - `ThankYouActivity.java` confirms orders and displays a “Thank You” screen after purchase.
  - Uses Intent flags to clear previous activities and prevent navigating back to checkout.

- **Firebase Integration**
  - **Firebase Storage** for shoe images.
  - **Firebase Realtime Database / Firestore** (if configured) for product details.
  - `google-services.json` integrated with Gradle for automatic Firebase initialization.

- **Material Design UI**
  - XML layouts use Material components for a clean, modern look.
  - Consistent theming and responsive layouts.

- **Clean Code Practices**
  - Separation of concerns via Activity classes.
  - Reusable helper methods and clear lifecycle management.
  - Proper null-checks and error handling when loading images/data.


---

## 🛠 Tech Stack
- **Language:** Java  
- **Framework:** Native Android (Android Studio)  
- **Backend Services:**  
  - **Firebase Authentication** – user sign-up/sign-in  
  - **Firebase Storage** – hosts and delivers shoe images  
  - (Optional) **Cloud Firestore** or Realtime Database for product data  
- **UI:** XML Layouts with Material Design

---

## ▶️ Demo Video
Watch the step-by-step demo on YouTube:  
[![YouTube Demo](https://img.shields.io/badge/Watch-Video-red?logo=youtube)](https://youtu.be/MQxgyRCpQSQ?si=NXylbCQpCdvhN0HT)

---

## 🚀 Getting Started

1. **Open in Android Studio**  
   File → Open → select the project folder.

2. **Add Firebase Configuration**
   - Create a Firebase project.
   - Download `google-services.json` and place it in the `app/` folder.
   - Enable Email/Password (or other) sign-in methods.
   - Set up **Firebase Storage** for hosting product images.

3. **Run the App**  
   Build and run on an Android emulator or a physical device.

---

## 💡 Future Enhancements
- Integrate a real product inventory system and admin dashboard.
- Add a payment gateway (Stripe/PayPal/Google Pay).
- Implement push notifications for promotions or order status.
- Introduce user order history and wishlist features.
