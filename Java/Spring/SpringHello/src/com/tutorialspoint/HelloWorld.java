package com.tutorialspoint;

public class HelloWorld {
   private String message;

   public void setMessage(String message){
      this.message  = message;
   }

   public void getMessage(){
      System.out.println("Your Message : " + message);
   }
   
   public void onInit(){
	   System.out.println("onInit()");
   }
   
   public void onDestroy(){
	   System.out.println("onDestroy()");
   }
}
