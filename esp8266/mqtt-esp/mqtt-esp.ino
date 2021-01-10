
#include <ESP8266WiFi.h>
#include <PubSubClient.h>
#include <ArduinoJson.h>
#include <DHT.h>
#include "freertos/FreeRTOS.h"
#include "freertos/task.h"
#include "freertos/event_groups.h"
#define DHTPIN D3
#define DHTTYPE DHT11
 
const char* ssid = "DIGI_9c8588"; // Enter your WiFi name
const char* password =  "1769140d"; // Enter WiFi password
const char* mqttServer = "192.168.1.4";
const int mqttPort = 1883;
String message = "";

int senseMode = 0;

size_t measureJsonPretty(const JsonDocument& doc); 

WiFiClient wifiClient;
PubSubClient MQTTclient(wifiClient);
DHT dht3 = DHT(DHTPIN, DHTTYPE);
SemaphoreHandle_t sema_MQTT_KeepAlive;

void setup() {
  
  sema_MQTT_KeepAlive   = xSemaphoreCreateBinary();
  xSemaphoreGive( sema_MQTT_KeepAlive ); // found keep alive can mess with a publish, stop keep alive during publish

  Serial.begin(115200);
  dht3.begin(); 
  xTaskCreatePinnedToCore( MQTTkeepalive, "MQTTkeepalive", 20000, NULL, 3, NULL, 1 );
  xTaskCreatePinnedToCore( Acquisition, "Acquisition", 20000, NULL, 4, NULL, 1);
}
 
void Acquisition( void *pvParameters )
{
  float temperature = 0.0f;
  float humidity    = 0.0f;
  
  while ( !MQTTclient.connected() )
  {
    vTaskDelay( 250 );
  }
  for (;;)
  {
    StaticJsonDocument<300> doc;
    doc["moduleID"] = 1;
    doc["temperature"] = (float)dht3.readTemperature();
    doc["humidity"] = (float)dht3.readHumidity();
    char buffer[256];
    serializeJson(doc, buffer);
    xSemaphoreTake( sema_MQTT_KeepAlive, portMAX_DELAY );
    if ( client.connected() )
    {
      client.publish("esp1", buffer);

    }
    xSemaphoreGive( sema_MQTT_KeepAlive );
    vTaskDelay( 1000 * 15 );
  }
  vTaskDelete ( NULL );
}

*/
void MQTTkeepalive( void *pvParameters )
{
  MQTTclient.setKeepAlive( 90 ); 
  for (;;)
  {
    if ((wifiClient.connected()) && (WiFi.status() == WL_CONNECTED))
    {
      xSemaphoreTake( sema_MQTT_KeepAlive, portMAX_DELAY );
      MQTTclient.loop();
      xSemaphoreGive( sema_MQTT_KeepAlive );
    }
    else {
      if ( !(WiFi.status() == WL_CONNECTED) )
      {
        connectToWiFi();
      }
      connectToMQTT();
    }
    vTaskDelay( 250 ); 
  }
  vTaskDelete ( NULL );
}

void connectToMQTT()
{
  while ( !MQTTclient.connected() )
  {
    MQTTclient.setServer(mqttServer, mqttPort);
    vTaskDelay( 250 );
  }
}

void connectToWiFi()
{
  while ( WiFi.status() != WL_CONNECTED )
  {
    WiFi.begin(ssid, password);
    vTaskDelay( 4000 );
  }
}

void loop() { }
