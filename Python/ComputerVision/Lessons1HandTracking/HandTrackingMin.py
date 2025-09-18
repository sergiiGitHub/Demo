import cv2
import mediapipe as mp
import time

cap = cv2.VideoCapture(0)

mpHand = mp.solutions.hands
hands = mpHand.Hands()
mpDraw = mp.solutions.drawing_utils

cTime = 0
pTime = 0

while True:
    success, imp = cap.read()
    imgRGB = cv2.cvtColor(imp, cv2.COLOR_RGBA2RGB)
    results = hands.process(imgRGB)

    #print(results.multi_hand_landmarks)
    if results.multi_hand_landmarks:
        for handsLms in results.multi_hand_landmarks:
            for id, lm in enumerate(handsLms.landmark):
                h, w, c = imgRGB.shape
            mpDraw.draw_landmarks(imp, handsLms, mpHand.HAND_CONNECTIONS)

    cTime = time.time()
    fps = 1/(cTime-pTime)
    pTime = cTime
    cv2.putText(imp, str(int(fps)), (10,70), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 0, 255), 2)
    cv2.imshow("Image", imp)
    cv2.waitKey(1)
