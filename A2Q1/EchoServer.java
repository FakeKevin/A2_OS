/*
Assignment #2 - Question 1
Date Due: Oct. 8th, 2018
By: Jason Han & Keun Young Jee
Course: SYST 44288

Description:

An echo server echoes back whatever it receives from a client. For example, if a client
sends the server the string Hello there!, the server will respond with Hello there!
Write an echo server using the Java networking API described in Section 3.6.1.

Class Description: EchoServer

The server will always run and accept subsequent connections from EchoClient.
The only way to terminate the server is by CTRL+C.

Notes:
Code was taken from the book and modified for the question.

References:

Silberschatz, A. et. al. (2013) Operating System Concepts Ninth Edition.
John Wiley & Sons, Inc. pp. 138-139

*/

//package a2_q1;
import java.net.*;
import java.io.*;

public class EchoServer {

    public static void main(String[] args)
    {
        try
            {
                //Create the Server Socket
		private int port = 6013;
                ServerSocket sock = new ServerSocket(port);
		System.out.println("Starting server connection on port: " + port);
                while(true)
                    {
                        //Create a client that will accept socket connections
                        Socket client = sock.accept();
                        //Get the clients input stream
                        InputStream in = client.getInputStream();
                        //Create a buffer that will read the input stream
                        BufferedReader buffIn = new BufferedReader(new InputStreamReader(in));
                        String line = buffIn.readLine();
                        //While the client has not entered in nothing:
                        while (line != null)
                            {
                                //Sanitize input
                               line = sanitizeInput(line);
                               //Print back out at the client exactly what they entered in
                               PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
                               pout.println("Server echoes back: "+line);
                               line = buffIn.readLine();
                            }
                    }
            }
        catch(IOException ioe)
            {
                System.err.println(ioe);
            }
    }

    //This function sanitizes input. It will only allow A-Z & a-z,
    //numbers and spaces to be echoed back to the user.
    private static String sanitizeInput(String original)
    {
        String newStr = "";
        String characters = " 1234567890aqwertyuiopsdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";

        //Loop through every character in the original string
        for(int n = 0; n < original.length(); n++)
            {
                String tempChar = original.substring(n, n+1);
                int good = 0;
                //Loop through the good character list. Set a value if we
                //have found a good character
                for (int m = 0; m < characters.length();m++)
                    {
                        if(tempChar.equals(characters.substring(m, m+1)))
                            {
                                good = 1;
                                break;
                            }
                    }
                //Only if that character is good do we add it to the return string
                if(good == 1)
                    {
                        newStr += tempChar;
                    }
            }
        return newStr;
    }
}
