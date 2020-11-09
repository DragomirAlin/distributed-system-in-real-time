#include <ESP8266WiFi.h>
#include <PubSubClient.h>
 
const char* ssid = "DIGI_9c8588"; // Enter your WiFi name
const char* password =  "1769140d"; // Enter WiFi password
const char* mqttServer = "192.168.1.5";
const int mqttPort = 1883;
String message = "";
#define MODE_OFF    0  // not sensing light, LED off
#define MODE_ON     1  // not sensing light, LED on
#define MODE_SENSE  2  // sensing light, LED controlled by software
int senseMode = 0;

WiFiClient espClient;
PubSubClient client(espClient);
 
void setup() {
 
  Serial.begin(115200);
 
  connect();
 
//  client.publish("esp/test", "hello"); //Topic name
//  client.subscribe("esp");
  client.subscribe("command");
//  while(true){
//     client.publish("esp1", "hello"); //Topic name
//     client.publish("esp2", "hello from 2"); //Topic name
//     client.publish("esp3", "hello from 3"); //Topic name
//     client.publish("esp4", "hello from 4"); //Topic name
//
//     
//
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
    if (message.equals("onn")){
      senseMode = MODE_ON;

    }else if(message.equals("off")){
     senseMode = MODE_OFF;

    }
  

//  if(message == "12fd4s!23()*&^TG"){
//    while(message != "stop"){
//      for (int i = 0; i < length; i++) {
//    Serial.print((char)payload[i]);
//    message += (char)payload[i];
//  }
//     client.subscribe("command");
//     client.publish("esp1", "hello"); //Topic name
//     client.publish("esp2", "hello from 2"); //Topic name
//     client.publish("esp3", "hello from 3"); //Topic name
//     client.publish("esp4", "hello from 4"); //Topic name
//     delay(2000);
//    }
//     
//  }else if(message = "stop"){
//    client.publish("command","stop with succes");
//  }
//  
  Serial.println();
}

 
void loop() {
  if (!client.connected()) {
    connect();
  }

     switch (senseMode) {
    case MODE_OFF:
      break;
    case MODE_ON:
      // light should be on
     client.publish("esp1", "hello"); //Topic name
     client.publish("esp2", "hello from 2"); //Topic name
     client.publish("esp3", "hello from 3"); //Topic name
     client.publish("esp4", "hello from 4"); //Topic name
     delay(2000);
      break;
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
