# OS - a2

Operating Systems Design and Systems Programming
Assignment #2 - SYST 44288

Git bash was used to push the code to this repository.

## Question 1
An echo server echoes back whatever it receives from a client. For example, if a client sends the server the 
string Hello there!, the server will respond with Hello there!
Write an echo server using the Java networking API described in Section 3.6.1. This server will wait for a 
client connection using the accept() method. When a client connection is received, the server will loop, 
performing the following steps:

- Read data from the socket into a buffer.
- Write the contents of the buffer back to the client.

The server will break out of the loop only when it has determined that the client has
closed the connection.

Apply appropriate error handling, see the General Notes Section below.

Input sanitizing - While it is always recommended to sanitize input, the fact that this program will compile 
and execute any code it is sent over the Internet basically makes it impossible to secure properly. Just do 
basic sanitizing and don’t worry too much.

## Question 2
Design a file-copying program, in the C programming language, named filecopy using ordinary pipes. This 
program will be passed two parameters: the name of the file to be copied and the name of the copied file. The 
program will then create an ordinary pipe and write the contents of the file to be copied to the pipe. The 
child process will read this file from the pipe and write it to the destination file. For example, if we 
invoke the program as follows:

filecopy input.txt copy.txt

the file input.txt will be written to the pipe. The child process will read the contents of this file and 
write it to the destination file copy.txt. Write this program using UNIX pipes.
