import json

with open("data/vehicles.json", "r") as f:
    data = json.load(f)

count_0 = sum(1 for item in data if item["health"] == 0)
count_1 = sum(1 for item in data if item["health"] == 1)

print(f"Total entries: {len(data)}")
print(f"Healthy (1): {count_1}")
print(f"Unhealthy (0): {count_0}")
