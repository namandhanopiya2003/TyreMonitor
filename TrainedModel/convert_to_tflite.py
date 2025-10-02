import tensorflow as tf

model = tf.keras.models.load_model("models/vehicle_model.h5")

converter = tf.lite.TFLiteConverter.from_keras_model(model)

converter.optimizations = [tf.lite.Optimize.DEFAULT]

tflite_model = converter.convert()

with open("vehicle_model.tflite", "wb") as f:
    f.write(tflite_model)

print("Model successfully converted to vehicle_model.tflite!")
