#include <sys/types.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <mqueue.h>
#include <unistd.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/wait.h>

void f( int x ) {
	if( x % 10000 == 0 ) {
		printf( "----------------------\n" );
		printf( "Recursive step: %d\n" , x );
		printf( "Print 0 to return from func\n" );
		printf( "Print 1 to continue to func\n" );
		printf( "----------------------\n" );
		printf( "Your choice: " );
		int y;
		scanf( "%d" , &y );
		if( y == 0 )
			return;
	}
	f(x + 1);
}

int main( int argc , char *argv[] ) {
	
	char str[5];
	
	int y;
	int choice;
	int pid = getpid();
	
	printf( "Process id = %d\n" , pid );
	
	while(1) {
		
		printf( "----------------------\n" );
		printf( "Menu\n" );
		printf( "Print 0 to exit\n" );
		printf( "Print 1 to use stack\n" );
		printf( "Print 2 to use heap\n" );
		printf( "----------------------\n" );
		printf( "Your choice: " );
		
		scanf( "%d" , &choice );
		
		if( choice == 1 ) {
			printf( "Stack\n" );
			f(1);
		}
		else if( choice == 2 ) {
			printf( "Initial Heap End: %10p\n" , sbrk(0) );
			for( int i = 1 ; 1 ; i++ ) {
				malloc(10000 * sizeof(int));
				printf( "----------------------\n" );
				printf( "Allocated ints: %d\n" , i*10000 );
				printf( "Current Heap End: %10p\n" , sbrk(0) );
				printf( "Print 0 to break the loop\n" );
				printf( "Print 1 to continue to loop\n" );
				printf( "----------------------\n" );
				printf( "Your choice: " );
				scanf( "%d" , &y );
				if( y == 0 )
					break;
			}
			printf( "Final Heap End: %10p\n" , sbrk(0) );
		}
		else if( choice == 0 ) {
			break;
		}
		else {
			printf( "Invalid input !!!\n" );
		}
		
		printf( "Enter sth to continue: " );
		scanf( "%s" , str );
		
	}
	
	return 0;
	
}
