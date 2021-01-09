
#include <ESP8266WiFi.h>
#include <PubSubClient.h>
#include <ArduinoJson.h>
#include <DHT.h>
#define DHTPIN D3
#define DHTTYPE DHT11
 
const char* ssid = ""; // Enter your WiFi name
const char* password =  ""; // Enter WiFi password
const char* mqttServer = "192.168.1.5";
const int mqttPort = 1883;
String message = "";

int senseMode = 0;

size_t measureJsonPretty(const JsonDocument& doc); 

WiFiClient espClient;
PubSubClient client(espClient);
 
void setup() {
  Serial.begin(115200);
  dht3.begin();
  connect();
 
  client.subscribe("command");
}
 
void callback(char* topic, byte* payload, unsigned int length) {
  String message = "";
  Serial.print("Message arrived in topic: ");
  Serial.println(topic);
 
  for (int i = 0; i < length; i++) {
    Serial.print((char)payload[i]);
    message += (char)payload[i];
  }

}

 
void loop() {
  
  if (!client.connected()) {
    connect();
  }


  StaticJsonDocument<300> doc;
  doc["moduleID"] = 1;
  doc["temperature"] = (float)dht3.readTemperature();
  doc["humidity"] = (float)dht3.readHumidity();

  char buffer[256];
  serializeJson(doc, buffer);

  client.publish("esp1", buffer);
  delay(1000);

  
  client.loop();
}


void connect(){
  WiFi.begin(ssid, password);
  
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.println("Connecting to WiFi..");
  }
  Serial.println("Connected to the WiFi network");
 
  client.setServer(mqttServer, mqttPort);
  client.setCallback(callback);
 
  while (!client.connected()) {
    Serial.println("Connecting to MQTT...");
    if (client.connect("ESP8266Client")) {
      Serial.println("connected");  
    } else {
      Serial.print("failed with state ");
      Serial.print(client.state());
      delay(2000);
    }
  }
}
