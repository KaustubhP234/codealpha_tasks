import nltk
import random
import string
import numpy as np
import tensorflow as tf
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.preprocessing import LabelEncoder
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, Dropout
import json
import pickle

# Load training data
with open("intents.json", "r") as file:
    data = json.load(file)

# Preprocess data
sentences = []
labels = []
responses = {}

for intent in data["intents"]:
    for pattern in intent["patterns"]:
        sentences.append(pattern.lower())
        labels.append(intent["tag"])
    responses[intent["tag"]] = intent["responses"]

vectorizer = CountVectorizer(token_pattern='\w+')
X = vectorizer.fit_transform(sentences).toarray()
label_encoder = LabelEncoder()
y = label_encoder.fit_transform(labels)

# Build model
model = Sequential([
    Dense(128, input_shape=(X.shape[1],), activation="relu"),
    Dropout(0.5),
    Dense(64, activation="relu"),
    Dropout(0.5),
    Dense(len(set(labels)), activation="softmax")
])

model.compile(loss="sparse_categorical_crossentropy", optimizer="adam", metrics=["accuracy"])

# Train model
model.fit(X, y, epochs=200, batch_size=5, verbose=1)

# Save model
model.save("chatbot_model.h5")
pickle.dump(vectorizer, open("vectorizer.pkl", "wb"))
pickle.dump(label_encoder, open("label_encoder.pkl", "wb"))

# Chat function
def chatbot_response(text):
    text = text.lower()
    X_input = vectorizer.transform([text]).toarray()
    prediction = model.predict(X_input)
    tag = label_encoder.inverse_transform([np.argmax(prediction)])[0]
    return random.choice(responses[tag])

# Example usage
print("Chatbot ready! Type 'quit' to exit.")
while True:
    user_input = input("You: ")
    if user_input.lower() == "quit":
        break
    print("Bot:", chatbot_response(user_input))
