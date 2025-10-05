import tensorflow as tf

# Loads pre-trained model
model = tf.keras.models.load_model("models/vehicle_model.h5")

# Creates TensorFlow Lite converter from the model
converter = tf.lite.TFLiteConverter.from_keras_model(model)

# It sets optimization for smaller and faster TFLite model
converter.optimizations = [tf.lite.Optimize.DEFAULT]

# Converts model to TensorFlow Lite format
tflite_model = converter.convert()

# It saves converted TFLite model to file
with open("vehicle_model.tflite", "wb") as f:
    f.write(tflite_model)

# Prints confirmation message
print("Model successfully converted to vehicle_model.tflite!")
