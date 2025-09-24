# 👟 ShoeStore – Android App

ShoeStore is a native Android application for browsing and purchasing shoes.  
It provides a smooth shopping experience—from browsing products to secure sign-in and checkout.

---

## ✨ Features
1. **Home Screen (`MainActivity`)**  
   Browse a curated list of shoes with images and prices.
2. **Product Listing (`ProductActivity`)**  
   View all available shoes with easy navigation.
3. **Product Details (`DetailActivity`)**  
   See full details such as size, description, and price.
4. **Order Confirmation (`ThankYouActivity`)**  
   Displays a thank-you page after successful purchase.
5. **Secure Authentication**  
   Uses **Firebase Authentication** for user sign-up and sign-in.
6. **Cloud Storage for Images**  
   Shoe images are stored and served via **Firebase Storage**, ensuring fast and reliable delivery.
7. **Realtime Database / Firestore** *(if applicable)*  
   Product data can be managed through Firebase’s database services for easy updates.

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
