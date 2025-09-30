import pandas as pd
import numpy as np


feature_min = np.array([15.0, 1.0, 0.5]) 
feature_max = np.array([35.0, 12.0, 2.5])


def load_json_data(file_path):
    df = pd.read_json(file_path)
    return df

def preprocess_features(df):
    df['tyreAge'] = (
        df['tyreAge']
        .astype(str)
        .str.replace(' years', '', regex=False) 
    )


    df['tyreAge'] = pd.to_numeric(df['tyreAge'], errors='coerce') 

    df['loadCapacity'] = (
        df['loadCapacity']
        .astype(str)
        .str.replace(' Tons', '', regex=False)
    )


    df['loadCapacity'] = pd.to_numeric(df['loadCapacity'], errors='coerce')

    X = df[['tyrePressure', 'tyreAge', 'loadCapacity']].values

    y = df['health'].values if 'health' in df.columns else None

    return X, y

def normalize_features(X, epsilon=1e-8):
    X = np.array(X, dtype=float)
    return (X - feature_min) / (feature_max - feature_min + epsilon)
