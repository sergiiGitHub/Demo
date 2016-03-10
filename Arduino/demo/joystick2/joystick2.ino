
#define BUTTON D2 // button
#define X_VALUE A0

int val;

void setup() {
 Serial.begin (9600);
 pinMode(BUTTON, INPUT);
}

void loop() {
/* The following trigPin/echoPin cycle is used to determine the
 distance of the nearest object by bouncing soundwaves off of it. */ 
 val = digitalRead(BUTTON);
 Serial.print( "button " );
 Serial.println( val );

 val = analogRead(X_VALUE);
 Serial.print( "x " );
 Serial.println( val );
 
 delay(1*1000);
}
