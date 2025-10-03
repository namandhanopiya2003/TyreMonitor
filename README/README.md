## 🚜 TyreMonitor – Smart Tyre Health Monitoring App

## 🧠 ABOUT THIS PROJECT ==>

- TyreMonitor is a smart Android application designed to analyze and predict tyre health for heavy-duty dumpers using integrated machine learning models. It processes structured tyre data (stored as JSON files), analyzes it using a trained ML model, and displays health indicators in a clean, user-friendly interface.

- This tool empowers fleet operators, maintenance teams, and industrial vehicle operators with intelligent diagnostics, reducing the risk of tyre failures and enabling proactive maintenance scheduling.

---

## ⚙ TECHNOLOGIES USED ==>

📱 Android Development:

- **Kotlin**
- **Android Studio**

🤖 Machine Learning (Model Training & Integration):

- **Python**
- **pandas**
- **numpy**
- **scikit-learn**
- **tensorflow**

Model Deployment:

- **Exported as .tflite for Android**
- **Used for real-time predictions directly within the app**

📄 Data Handling:

- **JSON: Used for storing and feeding tyre data into the model**

---

## 📁 FILES INCLUDED ==>

TyreMonitor/
|
|
├──----- activity_about_app.xml
|    ├── activity_analytics.xml
|    ├── activity_feedback.xml
|    ├── activity_help.xml
|    ├── activity_login.xml
|    ├── activity_main_menu.xml
|    ├── activity_maintenance_guidelines.xml
|    ├── activity_profile.xml
|    ├── activity_usage_policy.xml
|    ├── activity_vehicle_details.xml
|    ├── activity_vehicles_list.xml
|    └── activity_vehicles.xml
|
|
├──----- AboutAppActivity.kt 
|    ├── AnalyticsActivity.kt
|    ├── FeedbackActivity.kt
|    ├── HelpActivity.kt
|    ├── LoginActivity.kt
|    ├── MainMenuActivity.kt
|    ├── MaintenanceGuidelinesActivity.kt
|    ├── ProfileActivity.kt
|    ├── UsagePolicyActivity.kt
|    ├── Vehicle.kt
|    ├── VehicleAdapter.kt
|    ├── VehicleDetailsActivity.kt
|    └── VehiclesListActivity.kt
|
|
├──----- background_image
|    ├── background_image_login
|    └── background_image_profile
|
|
├──----- vehicle_model.tflite
│
|
└──----- vehicles.json





TrainedModel/
|
|
├── data/
|    └── vehicles.json
| 
|
├── models/
|    ├── vehicle_model.h5
|    └── vehicle_model.tflite
|    
|    
├── src/
|    ├── predict.py
|    ├── preprocess.py
|    └── train_model.py
|
|
├── venv/
|
|
├── check_dataset.py
|
|
├── convert_to_tflite
|
|
├── generate_training_data.py
|
|
└── requirements.txt

---

## 📝 WHAT EACH FILE DOES ==>

**ActivityLogin.kt**:
- Launches the app, loads tyre data from JSON, runs ML predictions, and displays results.
- ml/TyreHealthPredictor.kt:
- Handles TFLite model loading and real-time prediction logic.

**assets/tyre_data.json**:
- Contains structured sample data about tyre conditions.
- train_model.py:
- Trains a Random Forest or TensorFlow model on preprocessed tyre data.

**tyre_model.tflite**:
- Optimized ML model for fast on-device predictions in Android.

---

## 🚀 HOW TO RUN ==>

# Step 1: Open Android Studio

# Step 2: Select device to run the app

# Step 3: Click on run button

---

## ✨ SAMPLE OUTPUT ==>

{
  "tyre_pressure": 46 PSI,
  "tyre_age": 7 years
  "load_weight": 2.1 tonne
}

🤖 Prediction: "Critical"
📊 Health Score: 42 / 100

---

## 📬 CONTACT ==>

For questions or feedback, feel free to reach out!

---