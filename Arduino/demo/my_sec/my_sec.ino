
#define echoPin 7 // Echo Pin
#define trigPin 8 // Trigger Pin
#define ledPin 13 // Onboard LED

int connection; // Duration used to calculate distance
int serialData = 0;

void setup() {
 Serial.begin (9600);
 pinMode(trigPin, OUTPUT);
 pinMode(echoPin, INPUT);
 pinMode(ledPin, OUTPUT); // Use LED indicator (if required)
}

void loop() {
/* The following trigPin/echoPin cycle is used to determine the
 distance of the nearest object by bouncing soundwaves off of it. */ 
 digitalWrite(trigPin, HIGH);
 connection = digitalRead( echoPin );

 serialData = Serial.read();
 Serial.print( "serialData = " );
 Serial.println( serialData );
 
 Serial.print( "connection ");
 if ( connection ){
  Serial.println( "open" );
  digitalWrite(ledPin, HIGH);    
 } else {
  Serial.println( "close" );
  digitalWrite(ledPin, LOW); 
 }

 delay(1000);//Delay 2s before next reading.
}
