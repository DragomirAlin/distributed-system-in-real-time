#include <ESP8266WiFi.h>
#include <PubSubClient.h>
 
const char* ssid = ""; // Enter your WiFi name
const char* password =  ""; // Enter WiFi password
const char* mqttServer = "";
const int mqttPort = 1883;

WiFiClient espClient;
PubSubClient client(espClient);
 
void setup() {
 
  Serial.begin(115200);
 
  connect();
 
  client.publish("esp/test", "hello"); //Topic name
  client.subscribe("esp/test");
  client.subscribe("esp/room");
//  while(true){
//     client.publish("esp/test", "hello"); //Topic name
//     client.publish("temperature", "22.3");
//     client.publish("umid", "21");
//     Serial.println(client.subscribe("light"));
//     delay(1000);
//  }
 
}
 
void callback(char* topic, byte* payload, unsigned int length) {
  String message = "";
  Serial.print("Message arrived in topic: ");
  Serial.println(topic);
 
  Serial.print("Message:");
  for (int i = 0; i < length; i++) {
    Serial.print((char)payload[i]);
    message += (char)payload[i];
  }
  Serial.println();
}

 
void loop() {
  if (!client.connected()) {
    connect();
  }
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

