
#define echoPin 7 // Echo Pin
#define trigPin 8 // Trigger Pin
#define ledPin 13 // Onboard LED

#define OPEN_STATE  1 // open circle not close to ground
#define CLOSE_STATE 0 // close, circle close to ground

int state = CLOSE_STATE; 

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
 int currentState = digitalRead( echoPin );
 if ( currentState != state ) {
    state = currentState;
    if ( state == OPEN_STATE ){
      Serial.println( "o" );
      digitalWrite(ledPin, HIGH);    
    } else {
      Serial.println( "c");
      digitalWrite(ledPin, LOW); 
    }
 }

 delay(1000);//Delay 1s before next reading.
}
