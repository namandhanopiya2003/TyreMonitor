import os
import numpy as np
from tensorflow.keras.models import load_model
from src.preprocess import normalize_features

# Defines directory and model path
BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
MODEL_PATH = os.path.join(BASE_DIR, 'models', 'vehicle_model.h5')

# It predicts tyre health confidence score for a given input feature array
def predict_tyre_health(features):

    # Loads pre-trained vehicle model
    model = load_model(MODEL_PATH)

    # Converts features to NumPy array and normalizes them
    X = np.array([features])
    X = normalize_features(X)

    # Predicts confidence score
    confidence = model.predict(X, verbose=0)[0][0]
    return confidence

if __name__ == "__main__":
    # Sample input sets for testing
    test_inputs = [
[30, 2.5, 1.0],
[25, 4.0, 1.2],
[15, 10.0, 2.0],
[32, 1.0, 0.9],
[28, 3.2, 1.1],
[22, 6.5, 1.8],
[35, 2.0, 1.0],
[18, 8.0, 2.5],
[26, 3.8, 1.3],
[33, 1.5, 1.0],
[20, 7.0, 2.2],
[31, 2.2, 1.0],
[27, 4.5, 1.4],
[16, 9.0, 2.8],
[29, 2.7, 1.1],
[34, 1.2, 0.95],
[21, 6.8, 2.0],
[23, 5.5, 1.6],
[19, 8.5, 2.6],
[36, 1.8, 1.0]

    ]

    # Loops through test inputs and prints predictions
    for sample in test_inputs:
        score = predict_tyre_health(sample)
        print(f"Input: {sample} → Tyre health confidence: {score:.2f}")

