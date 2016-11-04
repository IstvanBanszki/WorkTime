// pin mapping for SPI(2) on the Tiva TM4C1294XL
// SDA  -  CS(2) -> PB_4
// SCK  -  SCK(2) -> PB_5
// Mosi -  MOSI(2) -> PE_4
// Miso -  MISO(2) -> PE_5
// IRQ  - Not connected
// RST  - reset -> PD_2

#include <Mfrc522.h>
#include <SPI.h>
#include <Ethernet.h>


int chipSelectPin = PB_4;
int resetPin = PD_2;
Mfrc522 Mfrc522(chipSelectPin, resetPin);
unsigned char sectorKey[6] = {0xff, 0xff, 0xff, 0xff, 0xff, 0xff};
unsigned char readData[16];
unsigned char cardSerNum01[5] = {0x66, 0xf7, 0x39, 0x9E, 0x36};
int inOutNum01 = 0;

EthernetClient client;
byte serverIp[] = { 192, 168, 1, 181 };
int portNumber = 8080;

void setup() {
	Serial.begin(9600);
  SPI.setModule(1);

  pinMode(chipSelectPin, OUTPUT);
	digitalWrite(chipSelectPin, LOW);
  pinMode(resetPin, OUTPUT);
  digitalWrite(resetPin, HIGH);
	pinMode(GREEN_LED, OUTPUT);
  pinMode(RED_LED, OUTPUT);
	Mfrc522.Init();
  Serial.println("Mfrc522 OK");
 
  byte mac[] = {0x00, 0x1A, 0xB6, 0x02, 0xAB, 0x6B};
  byte ip[] = {192, 1, 1, 177};

  int response = Ethernet.begin(mac);
  delay(1000);
  Serial.println(response);
  Serial.println(Ethernet.localIP());
  Serial.println("Ethernet OK");
}

void loop(){

  unsigned long lastConnectionTime = 0;
  boolean lastConnected = false;
  unsigned char i;
  unsigned char status;
  unsigned char str[MAX_LEN];
  unsigned char serNum[5];
    
  status = Mfrc522.Request(PICC_REQIDL, str); 
  if (status == MI_OK) {
    Serial.println("Card detected");
    Serial.print(str[0], BIN);
    Serial.print(" , ");
    Serial.print(str[1], BIN);
    Serial.println(" ");
  }

  status = Mfrc522.Anticoll(str);
  memcpy(serNum, str, 5);
  if (status == MI_OK) {
    Serial.println("The card's number is  : ");
    Serial.print(serNum[0], HEX);
    Serial.print(" , ");
    Serial.print(serNum[1], HEX);
    Serial.print(" , ");
    Serial.print(serNum[2], HEX);
    Serial.print(" , ");
    Serial.print(serNum[3], HEX);
    Serial.print(" , ");
    Serial.print(serNum[4], HEX);
    Serial.println(" ");
    Mfrc522.SelectTag(serNum);
    status = Mfrc522.Auth(PICC_AUTHENT1A,1,sectorKey,serNum);
    if (status == MI_OK) {
      Serial.println("Authenticated...\r\n");
    } else {
      Serial.println("Error authenticating...\r\n");
    }
    delay(2000);
    if (memcmp(serNum, cardSerNum01, 5) == 0) {
      Serial.println("card ok");
      inOutNum01 = !inOutNum01;
      sendPacket(1, inOutNum01);
    }
    Mfrc522.Init();
  }
  Mfrc522.Halt();
}

void sendPacket(int workerId, int inOut) {

  char outBuf[64];
  char requestUri[64];
  char data[] = "hello";

  if (client.connect(serverIp, portNumber)) {
    // send the header
    sprintf(requestUri, "/api/entrance/v1/entry/workerIds/%i/inOuts/%i", workerId, inOut);
    sprintf(outBuf,"GET %s HTTP/1.1", requestUri);
    client.println(outBuf);
    sprintf(outBuf,"Host: %i.%i.%i.%i", serverIp[0], serverIp[1], serverIp[2], serverIp[3]);
    client.println(outBuf);
    client.println("Connection: close\r\nContent-Type: text/html;charset=ISO-8859-1");
    sprintf(outBuf,"Content-Length: %u\r\n",strlen(data));
    client.println(outBuf);

    // send the body (variables)
    client.println(data);
    client.println();
  }

}
