#include <stdio.h>
#include <stdlib.h>

#define _GNU_SOURCE


ssize_t getline(char **lineptr, size_t *n, FILE *stream);
int main(int argc, char **argv){

  char *buffer[1]; 
  unsigned  status= 0; 
  int num; 

  int i=0;
  if ( argc == 2 ) 
    if (sscanf(argv[1], "%d", &num) ==1){
      while (getline(buffer, &status, stdin)> 0  && i < num ){
	printf("%s",*buffer); 
	i++;
      }
      	exit(0);
    }
  fprintf(stderr, "programa do caraças : argumentos inválidos!\n"); 
  exit(1);

}
