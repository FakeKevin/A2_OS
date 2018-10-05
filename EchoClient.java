/*
Assignment #2 - Question 1
Date Due: Oct. 8th, 2018
By: Jason Han & Kevin Jee
Course: SYST 44288

Description:

An echo server echoes back whatever it receives from a client. For example, if a client
sends the server the string Hello there!, the server will respond with Hello there!
Write an echo server using the Java networking API described in Section 3.6.1.

Class Description: EchoClient

The client will connect to the server and send input to it and receive echo output.
To terminate the client, the user must enter "."

Notes:
Code was taken from the book and modified for the question.

References:

Silberschatz, A. et. al. (2013) Operating System Concepts Ninth Edition.
John Wiley & Sons, Inc. pp. 138-139

*/

//package a2_q1;
import java.net.*;
import java.io.*;

public class EchoClient {

    public static void main(String[] args)
    {
        try
            {
                //Create the Socket that will connect to the server
                Socket sock = new Socket("127.0.0.1", 6013);
                int done = 0;
                while(done == 0)
                    {
                        System.out.println("Connected to localhost.");
                        //Get the clients input stream
                        InputStream in = sock.getInputStream();
                        //Create a buffer that will read the input stream
                        BufferedReader buffIn = new BufferedReader(new InputStreamReader(in));
                        PrintWriter pout = new PrintWriter(sock.getOutputStream(), true);
                        BufferedReader in2 = new BufferedReader(new InputStreamReader(System.in));
                        String line = in2.readLine();
						//While user has not entered in ".", read line, send input
						//to server, print out output from server.
                        while ((line.equals(".")==false))
                            {
                                pout.println(line);
                                System.out.println(buffIn.readLine());
                                line = in2.readLine();
                            }
						//If user is done, close the connection
                        done = 1;
                        sock.close();
                    }
                System.out.println("Terminated connection to server.");
            }
        catch(IOException ioe)
            {
                System.err.println(ioe);
            }
    }
}
