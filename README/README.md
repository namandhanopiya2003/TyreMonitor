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

TyreMonitor/<br>
|<br>
|<br>
├──----- activity_about_app.xml<br>
|    ├── activity_analytics.xml<br>
|    ├── activity_feedback.xml<br>
|    ├── activity_help.xml<br>
|    ├── activity_login.xml<br>
|    ├── activity_main_menu.xml<br>
|    ├── activity_maintenance_guidelines.xml<br>
|    ├── activity_profile.xml<br>
|    ├── activity_usage_policy.xml<br>
|    ├── activity_vehicle_details.xml<br>
|    ├── activity_vehicles_list.xml<br>
|    └── activity_vehicles.xml<br>
|<br>
|<br>
├──----- AboutAppActivity.kt<br> 
|    ├── AnalyticsActivity.kt<br>
|    ├── FeedbackActivity.kt<br>
|    ├── HelpActivity.kt<br>
|    ├── LoginActivity.kt<br>
|    ├── MainMenuActivity.kt<br>
|    ├── MaintenanceGuidelinesActivity.kt<br>
|    ├── ProfileActivity.kt<br>
|    ├── UsagePolicyActivity.kt<br>
|    ├── Vehicle.kt<br>
|    ├── VehicleAdapter.kt<br>
|    ├── VehicleDetailsActivity.kt<br>
|    └── VehiclesListActivity.kt<br>
|<br>
|<br>
├──----- background_image<br>
|    ├── background_image_login<br>
|    └── background_image_profile<br>
|<br>
|<br>
├──----- vehicle_model.tflite<br>
│<br>
|<br>
└──----- vehicles.json<br>



TrainedModel/<br>
|<br>
|<br>
├── data/<br>
|    └── vehicles.json<br>
|<br>
|<br>
├── models/<br>
|    ├── vehicle_model.h5<br>
|    └── vehicle_model.tflite<br>
|<br>
|<br>
├── src/<br>
|    ├── predict.py<br>
|    ├── preprocess.py<br>
|    └── train_model.py<br>
|<br>
|<br>
├── venv/<br>
|<br>
|<br>
├── check_dataset.py<br>
|<br>
|<br>
├── convert_to_tflite<br>
|<br>
|<br>
├── generate_training_data.py<br>
|<br>
|<br>
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
