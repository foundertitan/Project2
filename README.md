# 👟 Shoes Store — Android App (Project 2)

A modern Android app for browsing shoes, authenticating with Firebase, and managing product images via Firebase Storage. Built with clean architecture patterns and modular components (domain, data, UI), plus adapters, helpers, and fragments for a smooth shopping experience.

---

## 📸 Demo & Walkthrough

* **YouTube tutorial:** [![YouTube Demo](https://img.shields.io/badge/Watch-Video-red?logo=youtube)](https://youtu.be/MQxgyRCpQSQ?si=NXylbCQpCdvhN0HT)


---

## 🧱 Project Structure

```
app/
├─ java/
│  └─ com.yourpackage.shoesstore/
│     ├─ domain/                # Core models & business logic
│     │  ├─ model/              # Kotlin data classes (e.g., Shoe.kt, User.kt, CartItem.kt)
│     │  └─ usecase/            # Optional: Use-cases (e.g., GetShoesUseCase)
│     │
│     ├─ data/                  # Data sources & repositories
│     │  ├─ repository/         # Interfaces & implementations
│     │  └─ remote/             # Firebase APIs (Auth/Storage/Firestore or RTDB)
│     │
│     ├─ ui/
│     │  ├─ main/               # MainActivity, navigation host
│     │  ├─ fragments/          # HomeFragment, DetailFragment, CartFragment, AuthFragment
│     │  ├─ adapter/            # RecyclerView adapters & DiffUtil
│     │  └─ helper/             # UI utils, extensions, validators, ImageLoader
│     │
│     ├─ utils/                 # App-wide helpers (Result, Resource, constants)
│     └─ di/                    # (Optional) Dependency injection setup
│
├─ res/                         # Layouts, drawables, strings, themes
└─ build.gradle
```

---

## 🔑 Key Modules

### MainActivity

* Hosts the `NavHostFragment` and app-wide `Toolbar`/`BottomNavigation`.
* Sets up the navigation graph and handles global actions (e.g., sign out).
* Observes auth state to route users to Home or Auth screens.

**Files to check:** `ui/main/MainActivity.kt`, `navigation/nav_graph.xml`

### Fragments

* **HomeFragment:** Lists shoes (RecyclerView) pulled from Firebase.
* **DetailFragment:** Shows shoe details, price, and Add to Cart.
* **CartFragment:** Displays cart items and totals.
* **AuthFragment:** Email/Password login & registration.

**Files to check:** `ui/fragments/*`

### Adapter

* **ShoeAdapter:** `RecyclerView.Adapter` with `DiffUtil` for efficient lists.
* Binds shoe images (from Firebase Storage URLs) and metadata (name, brand, price).

**Files to check:** `ui/adapter/ShoeAdapter.kt`

### Domain

* **Models:** `Shoe.kt`, `User.kt`, `CartItem.kt` with serialization for Firebase.
* **Mappers/Use-Cases (optional):** For clean separation of concerns.

**Files to check:** `domain/model/*`

### Helpers

* **ImageLoader:** Glide/Coil wrapper to load Firebase Storage URLs.
* **Validators:** Email, password, and form validators for auth.
* **Extensions:** View and LiveData extensions.

**Files to check:** `ui/helper/*` and `utils/*`

---

## 🧰 Tech Stack

* **Language:** Kotlin
* **UI:** AndroidX, Material Components, RecyclerView, ViewBinding/ViewModel
* **Navigation:** Jetpack Navigation Component
* **Data:** Firebase Authentication, Firebase Storage (images)
* **Image Loading:** Coil or Glide
* **Async:** Kotlin Coroutines + Flow/LiveData
* **(Optional) DI:** Hilt/Koin

---

## ⚙️ Getting Started

### 1) Prerequisites

* Android Studio **Giraffe+** (or newer)
* JDK 17 (match your Gradle settings)
* A Firebase project with Authentication and Storage enabled

### 2) Clone the Repo

```bash
git clone https://github.com/<your-username>/<your-repo>.git
cd <your-repo>
```

### 3) Open in Android Studio

* **File → Open →** select the project folder
* Let Gradle sync finish

### 4) Add Firebase Configuration (Point #2)

1. Create a Firebase project in the Firebase Console.
2. **Download** `google-services.json` and place it in `app/`.
3. Enable **Email/Password** sign-in in **Authentication → Sign-in method**.
4. Set up **Firebase Storage** for hosting product images.

### 5) Run the App (Point #3)

* Build and run on an Android emulator or a physical device.

### 6) (Optional) Configure Other Sign-in Providers (Point #4)

* Add Google/Phone/etc. in Firebase → Authentication → Sign-in method and include the corresponding SDKs.

> These match your original numbered steps (2, 3, and 4) and the Future Enhancements from your notes are included below.

---

## 📦 Dependencies

**Project `build.gradle`**

```gradle
buildscript {
  dependencies {
    classpath 'com.google.gms:google-services:4.4.2'
  }
}
```

**Module `app/build.gradle`**

```gradle
plugins {
  id 'com.android.application'
  id 'org.jetbrains.kotlin.android'
  id 'com.google.gms.google-services'
}

dependencies {
  implementation platform('com.google.firebase:firebase-bom:33.2.0')
  implementation 'com.google.firebase:firebase-auth'
  implementation 'com.google.firebase:firebase-storage'
  implementation 'io.coil-kt:coil:2.7.0' // or Glide
  // Jetpack + UI
  implementation 'androidx.core:core-ktx:1.13.1'
  implementation 'androidx.appcompat:appcompat:1.7.0'
  implementation 'com.google.android.material:material:1.12.0'
  implementation 'androidx.recyclerview:recyclerview:1.3.2'
  implementation 'androidx.navigation:navigation-fragment-ktx:2.7.7'
  implementation 'androidx.navigation:navigation-ui-ktx:2.7.7'
  implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4'
  implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.8.4'
}
```

---

## 🔐 Firebase Storage Rules (Recommended)

```javascript
rules_version = '2';
service firebase.storage {
  match /b/{bucket}/o {
    // Require auth for all reads/writes
    match /{allPaths=**} {
      allow read, write: if request.auth != null;
    }
  }
}
```

> Use lenient rules **only for local testing**. Never ship with `allow read, write: if true;`.

---

## ✨ Code Snippets

### MainActivity (Nav Host)

```kotlin
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val navController = findNavController(R.id.nav_host_fragment)
    setupActionBarWithNavController(navController)
  }

  override fun onSupportNavigateUp(): Boolean {
    return findNavController(R.id.nav_host_fragment).navigateUp() || super.onSupportNavigateUp()
  }
}
```

### RecyclerView Adapter (DiffUtil)

```kotlin
data class Shoe(val id: String = "", val name: String = "", val brand: String = "", val price: Double = 0.0, val imageUrl: String = "")

class ShoeDiff : DiffUtil.ItemCallback<Shoe>() {
  override fun areItemsTheSame(old: Shoe, new: Shoe) = old.id == new.id
  override fun areContentsTheSame(old: Shoe, new: Shoe) = old == new
}

class ShoeAdapter(private val onClick: (Shoe) -> Unit) : ListAdapter<Shoe, ShoeAdapter.VH>(ShoeDiff()) {
  inner class VH(private val binding: ItemShoeBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Shoe) {
      binding.name.text = item.name
      binding.brand.text = item.brand
      binding.price.text = "$${item.price}"
      ImageLoader.load(binding.image, item.imageUrl)
      binding.root.setOnClickListener { onClick(item) }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
    val inflater = LayoutInflater.from(parent.context)
    return VH(ItemShoeBinding.inflate(inflater, parent, false))
  }

  override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))
}
```

### Helper – ImageLoader (Coil)

```kotlin
object ImageLoader {
  fun load(view: ImageView, url: String) {
    view.load(url) {
      crossfade(true)
    }
  }
}
```

### Navigation Graph (Example)

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:app="http://schemas.android.com/apk/res-auto"
  app:startDestination="@id/homeFragment">

  <fragment android:id="@+id/homeFragment" android:name=".ui.fragments.HomeFragment">
    <action android:id="@+id/action_home_to_detail" app:destination="@id/detailFragment" />
  </fragment>

  <fragment android:id="@+id/detailFragment" android:name=".ui.fragments.DetailFragment" />
  <fragment android:id="@+id/authFragment" android:name=".ui.fragments.AuthFragment" />
</navigation>
```

---

## ▶️ Run the App

1. Build & run on an emulator or device (**Android 8+** recommended).
2. Log in or register a new user.
3. Browse shoes, open details, add to cart.

---

## 🧪 Testing

* Create a test user via **Firebase Authentication → Users** or through **AuthFragment**.
* Upload a few sample images to **Firebase Storage** under `products/shoes/`.
* Verify list rendering in **HomeFragment** and detail navigation.
* Toggle airplane mode to validate error states and empty UI.

---

## 🔐 Security & Access (FAQ)

**Can anyone access my Firebase Console link?**
No. The Firebase Console is only accessible to collaborators you explicitly add to the project (via IAM/Project Settings). Public viewers cannot open your console link.

**Can anyone access my Storage images?**
Only if your **Storage Rules** allow public reads, or if you share a file's public download URL. Use the rules above to require authentication. Avoid posting long‑lived download URLs publicly.

**Should `google-services.json` be in Git?**
It's commonly committed for Android apps, but **do not** commit service account keys or any private keys. Add any sensitive files to `.gitignore`.

---

## 🧩 Roadmap / Future Enhancements

* Integrate a real product inventory system and admin dashboard.
* Add a payment gateway (Stripe/PayPal/Google Pay).
* Implement push notifications for promotions or order status.
* Introduce user order history and wishlist features.
* Offline cache with Room + Paging 3.
* Analytics events for key actions.
* Unit tests for use-cases & repositories; UI tests with Espresso.

---

## 🤝 Contributing

1. Fork the repo & create a feature branch.
2. Commit with conventional messages (e.g., `feat:`, `fix:`).
3. Open a PR with a clear description & screenshots.

---

## 📄 License

Choose a license (e.g., MIT) and place it in `LICENSE`.

---

## ✅ Quick Setup Checklist

* [ ] Add `google-services.json` to `app/`
* [ ] Enable Auth (Email/Password)
* [ ] Enable Storage & set secure rules
* [ ] Update app package name & `applicationId`
* [ ] Verify adapters, helpers, domain models, and fragments compile
* [ ] Add screenshots to `art/` and link in README
