/*
Assignment #2 - Question 2
Date Due: Oct. 8th, 2018
By: Jason Han & Kevin Jee
Course: SYST 44288

Description:
Design a file-copying program, in the C programming language, named filecopy using
ordinary pipes. This program will be passed two parameters: the name of the file to be
copied and the name of the copied file. The program will then create an ordinary pipe
and write the contents of the file to be copied to the pipe. The child process will read this
file from the pipe and write it to the destination file. For example, if we invoke the
program as follows:

filecopy input.txt copy.txt

the file input.txt will be written to the pipe. The child process will read the contents of
this file and write it to the destination file copy.txt.
Write this program using UNIX pipes.

References:

Patel, K. (n.d.) "pipe() System call" Retrieved from: https://www.geeksforgeeks.org/pipe-system-call/

Silberschatz, A. et. al. (2013) "Operating System Concepts Ninth Edition."
John Wiley & Sons, Inc. pp. 138-139
*/

#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>

#define BUFFERSIZE 1

int main(int ac, char *av[])
{
  char buf[BUFFERSIZE];
  int filePointerSrc;
  int filePointerDst;
  int p[2];
  int pid;

  //Check if we have received the correct number of arguments
  if(ac != 3)
    {
      fprintf(stderr, "Please provide correct usage: ./myprog src dst \n");
      exit(1);
    }

  //See if a pipe can be created, exit if not
  if (pipe(p) < 0)
    {
      fprintf(stderr, "Failed to create a pipe.");
      perror("Error");
      exit(1);
    }

  //Attempt to open the given files. If either open operation return a -1,
  //then there is some problem. Print out a statement.
  filePointerSrc = open(av[1],O_RDONLY);
  filePointerDst = open(av[2],O_WRONLY);

  if ( filePointerSrc == -1 || filePointerDst == -1)
    {
      fprintf(stderr, "One or both of the files don't exist. Please check existence of files.\n");
      perror("Error");
      exit(1);
    }

  //Fork a new process.
  pid = fork();
  if ( pid < 0 )
    {
      fprintf(stderr, "Failed to create a child process. \n");
      perror("Error");
      exit(1);
    }

  if (pid > 0) //Parent process will read from file source and write to pipe end
    {
      //Read one character at a time and write to the write end of the pipe
      while ((read(filePointerSrc, buf, BUFFERSIZE))>0)
        {
          write(p[1],buf,BUFFERSIZE);
        }
    }
  else //Child process will read from pipe end and write to file destination.
    {
      wait(NULL); //Wait until parent is done.

      //Read one character at a time from the pipe and write to file dest.
      while ((read(p[0], buf, BUFFERSIZE))>0)
        {
          write(filePointerDst,buf,BUFFERSIZE);
        }
    }
  printf("Done.\n");

  return 0;
}
