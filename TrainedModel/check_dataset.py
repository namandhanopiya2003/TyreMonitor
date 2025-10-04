import json

# Loads dataset from JSON file
with open("data/vehicles.json", "r") as f:
    data = json.load(f)

# Counts entries with health = 0 (unhealthy) and health = 1 (healthy)
count_0 = sum(1 for item in data if item["health"] == 0)
count_1 = sum(1 for item in data if item["health"] == 1)

# Prints dataset statistics
print(f"Total entries: {len(data)}")
print(f"Healthy (1): {count_1}")
print(f"Unhealthy (0): {count_0}")

