import os
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler
import joblib
from src.preprocess import load_json_data, preprocess_features

# Defines base paths for data, model
BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
DATA_PATH = os.path.join(BASE_DIR, 'data', 'vehicles.json')
MODEL_PATH = os.path.join(BASE_DIR, 'models', 'vehicle_model.h5')
SCALER_PATH = os.path.join(BASE_DIR, 'models', 'scaler.save')

# Builds a simple neural network for tyre health prediction
def build_model(input_dim):
    model = Sequential()
    model.add(Dense(16, input_dim=input_dim, activation='relu'))
    model.add(Dense(8, activation='relu'))
    model.add(Dense(1, activation='sigmoid'))

    model.compile(optimizer='adam', loss='binary_crossentropy', metrics=['accuracy'])
    return model

# Main training workflow
def main():
    # Loads and preprocesses dataset
    df = load_json_data(DATA_PATH)
    X, y = preprocess_features(df)

    # Scales features to [0,1] range
    scaler = MinMaxScaler()
    X_scaled = scaler.fit_transform(X)

    # Saves for future use
    os.makedirs(os.path.dirname(SCALER_PATH), exist_ok=True)
    joblib.dump(scaler, SCALER_PATH)

    # Splits dataset into training and testing sets
    X_train, X_test, y_train, y_test = train_test_split(X_scaled, y, test_size=0.2, random_state=42)

    # Builds and trains the neural network
    model = build_model(X_train.shape[1])
    model.fit(X_train, y_train, epochs=50, batch_size=8, verbose=2)

    # Evaluates model performance on test set
    loss, accuracy = model.evaluate(X_test, y_test, verbose=0)
    print(f"Test Accuracy: {accuracy*100:.2f}%")

    # Saves trained model
    os.makedirs(os.path.dirname(MODEL_PATH), exist_ok=True)
    model.save(MODEL_PATH)
    print(f"Model saved at {MODEL_PATH}")
    print(f"Scaler saved at {SCALER_PATH}")

if __name__ == "__main__":
    main()

