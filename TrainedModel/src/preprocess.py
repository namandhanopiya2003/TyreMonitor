import pandas as pd
import numpy as np

# Defines minimum and maximum values for feature normalization
feature_min = np.array([15.0, 1.0, 0.5]) 
feature_max = np.array([35.0, 12.0, 2.5])

# Loads dataset from a JSON file
def load_json_data(file_path):
    df = pd.read_json(file_path)
    return df

# Preprocesses raw features for model input
def preprocess_features(df):
    # Cleans 'tyreAge' column and converts to numeric
    df['tyreAge'] = (
        df['tyreAge']
        .astype(str)
        .str.replace(' years', '', regex=False) 
    )


    df['tyreAge'] = pd.to_numeric(df['tyreAge'], errors='coerce') 

    # Cleans 'loadCapacity' column and converts to numeric
    df['loadCapacity'] = (
        df['loadCapacity']
        .astype(str)
        .str.replace(' Tons', '', regex=False)
    )


    df['loadCapacity'] = pd.to_numeric(df['loadCapacity'], errors='coerce')

    # Extracts feature matrix
    X = df[['tyrePressure', 'tyreAge', 'loadCapacity']].values
    # Extracts target vector
    y = df['health'].values if 'health' in df.columns else None

    return X, y

# It normalizes features to [0,1] range based on predefined min/max
def normalize_features(X, epsilon=1e-8):
    X = np.array(X, dtype=float)
    return (X - feature_min) / (feature_max - feature_min + epsilon)

