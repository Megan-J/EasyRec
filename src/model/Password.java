package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Password {
	
	private String passwordStr; //private instance variable that stores the User's password
	
	public Password() throws IOException //constructor for Password object that Controller will use to manipulate password
	{
		String FilePath = "src/resources/password.txt";
        File file = new File(FilePath);
        
        Scanner scanner = new Scanner(file);
        String password = scanner.nextLine();
        this.passwordStr = password;
        scanner.close();
	}
	
	public String getPassword() //getter method for password
	{
		return passwordStr;
	}
	
	public void setPassword(String p) throws IOException //sets the password to String param p
	{
		File f = new File("src/resources/password.txt");
		FileWriter writer = new FileWriter(f, false);
        writer.write("");
        writer.close();
        
        // Write new data to the file
        writer = new FileWriter(f, true);
        writer.write(p);
        writer.close();
	}
}
