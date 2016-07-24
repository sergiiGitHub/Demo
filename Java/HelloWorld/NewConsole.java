import java.io.Console;

class NewConsole{
	public static void main(String [] args){
		System.out.println("Hello world");
		Console c = System.console();
		//char array use only for password , because if use String , it could save in heap before carbige collector come
		// and could read by hacker
		char[] pw;
		pw = c.readPassword("%s", "pw: ");
		for ( char ch : pw ){
			c.format("%c", ch);
		}
		c.format( "\n" );

		String name = c.readLine( "%s", "input:?" );
		System.out.println(name);
	}

}
