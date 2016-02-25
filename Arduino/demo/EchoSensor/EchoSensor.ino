#define trigPin D2                                    // Pin 12 trigger output
#define echoPin D0                                    // Pin 2 Echo input
#define echoInt echoPin                              // Interrupt id for echo pulse
//#define onBoardLED 13

volatile long echo_start = 0;                         // Records start of echo pulse 
volatile long echo_end = 0;                           // Records end of echo pulse
volatile long echo_duration = 0;                      // Duration - difference between end and start

void setup() 
{
  pinMode(trigPin, OUTPUT);                           // Trigger pin set to output
  pinMode(echoPin, INPUT);                            // Echo pin set to input
  
  attachInterrupt(echoInt, echo_interrupt, CHANGE);  // Attach interrupt to the sensor echo input
  Serial.begin (9600);                                // Initialise the serial monitor output
}

void loop()
{
    echo_duration = 0;
    // ping
    digitalWrite(trigPin, HIGH);
    delayMicroseconds(10);
    digitalWrite(trigPin, LOW);
    //my
    echo_duration = pulseIn(echoPin, HIGH, 200*1000);
    
    // wait pong
    delay(200);                                       // every 100 mS
    if (echo_duration) {
      Serial.println(echo_duration / 58);               // Print the distance in centimeters
    } else {
      Serial.println("No pong");
    }
}

void echo_interrupt()
{
  Serial.println("echo_interrupt");
  echo_end = digitalRead(echoPin);
  Serial.println("echo_interrupt: " + echo_end );
  switch (digitalRead(echoPin))                     // Test to see if the signal is high or low
  {
    case HIGH:                                      // High so must be the start of the echo pulse
      echo_end = 0;                                 // Clear the end time
      echo_start = micros();                        // Save the start time
     // attachInterrupt(echoInt, echo_interrupt, FALLING);  // Attach interrupt to the sensor echo input
      break;
      
    case LOW:                                       // Low so must be the end of hte echo pulse
      echo_end = micros();                          // Save the end time
      echo_duration = echo_end - echo_start;        // Calculate the pulse duration
      //attachInterrupt(echoInt, echo_interrupt, RISING);  // Attach interrupt to the sensor echo input
      break;
  }
}

