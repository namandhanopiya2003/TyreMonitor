import json
import random

def generate_positive_data(n_samples=4000, save_to_file=True):
    data = []

    for i in range(1, n_samples + 1):
        name = f"Dumper {i}"
        regNo = f"DUMMY-{random.randint(1000, 9999)}"

        tyrePressure = random.randint(28, 34) 
        tyreAge = round(random.uniform(0.0, 3.0), 1) 
        loadCapacity = round(random.uniform(0.7, 1.0), 2) 

        health = 1

        data.append({
            "name": name,
            "regNo": regNo,
            "tyrePressure": tyrePressure,
            "tyreAge": tyreAge,
            "loadCapacity": loadCapacity,
            "health": health
        })

    if save_to_file:
        with open('data/vehicles_positive.json', 'w') as f:
            json.dump(data, f, indent=4)
        print(f"Generated 'data/vehicles_positive.json' with {n_samples} healthy sample entries!")

    X = [[d["tyrePressure"], d["tyreAge"], d["loadCapacity"]] for d in data]
    y = [d["health"] for d in data]

    return X, y

if __name__ == "__main__":
    generate_positive_data()
