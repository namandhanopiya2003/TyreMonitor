import json
import random

# Generates positive vehicle data
def generate_positive_data(n_samples=4000, save_to_file=True):
    data = []

    # Creates n-samples entries with random feature values
    for i in range(1, n_samples + 1):
        name = f"Dumper {i}"
        regNo = f"DUMMY-{random.randint(1000, 9999)}"

        tyrePressure = random.randint(28, 34)                            # Typical healthy tyre pressure
        tyreAge = round(random.uniform(0.0, 3.0), 1)                     # Young tyres
        loadCapacity = round(random.uniform(0.7, 1.0), 2)                # Normal load

        # Healthy label
        health = 1

        # It appends generated entry to dataset
        data.append({
            "name": name,
            "regNo": regNo,
            "tyrePressure": tyrePressure,
            "tyreAge": tyreAge,
            "loadCapacity": loadCapacity,
            "health": health
        })

    # Saves generated data to JSON file
    if save_to_file:
        with open('data/vehicles_positive.json', 'w') as f:
            json.dump(data, f, indent=4)
        print(f"Generated 'data/vehicles_positive.json' with {n_samples} healthy sample entries!")

    # Prepares feature matrix X and target vector y
    X = [[d["tyrePressure"], d["tyreAge"], d["loadCapacity"]] for d in data]
    y = [d["health"] for d in data]

    return X, y

if __name__ == "__main__":
    generate_positive_data()

