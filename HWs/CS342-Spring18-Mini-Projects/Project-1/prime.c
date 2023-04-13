#include <sys/types.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/wait.h>

#define READ_END (0)
#define WRITE_END (1)

struct node
{
    int value;
    struct node *next;
};

typedef struct node mynode;
mynode *first = NULL, *last = NULL;

mynode* create_node( int val ) {
	mynode *new_node;
	new_node = (mynode *) malloc( sizeof( mynode ) );
	if( new_node == NULL ) {
		printf( "Ooops... Memory not allocated\n" );
		return 0;
	}
	else {
		new_node -> value = val;
		new_node -> next = NULL;
		return new_node;
	}
}

void insert_node_last( int val ) {
	mynode *new_node;
	new_node = create_node( val );
	if( first == NULL ) {
		first = last = new_node;
		first -> next = NULL;
		last -> next = NULL;
	}
	else {
		last -> next = new_node;
		last = new_node;
		last -> next = NULL;
	}
}

void delete_node_first() {
	mynode *first_node;
	first_node = first;
	first = first_node -> next;
	free(first_node);
}

int main( int argc , char *argv[] ) {
	
	int n = atoi( argv[1] );
	int m = atoi( argv[2] );
	
	int fd[m+2][2];
	
	int process_no;
	
	pid_t child_pid;
	
	for( int i = 0 ; i < m + 2 ; i++ )
		if( pipe( fd[i] ) == -1 ) {
			printf( "Fork Failed" );
			return 1;
		}
	
	fcntl( fd[m][READ_END] , F_SETFL , O_NONBLOCK );
	
	int fork_counter = 0;
	do {
		child_pid = fork();
		fork_counter++;
		if( child_pid > 0 ) {
			process_no = 0;
		}
		else if( child_pid == 0 ) {
			process_no = fork_counter;
		}
	}while( child_pid > 0 && fork_counter <= m );
	
	int read_msg;
	int write_msg;
	
	int finish = 0;
	int my_prime = -1;
	int must_read = 0;
	int list_size = 0;
	// int total_prime = 0;
	
	int last_read_msg = -3;
	
	while( finish == 0 ) {
		
		// Parent
		if( process_no == 0 ) {
			
			// read
			if( must_read == 0 ) {
				for( int i = 2 ; i <= n ; i++ )
					insert_node_last( i );
				insert_node_last( 0 );
				list_size = n;
				must_read = 1;
			}
			else {
				read_msg = -2;
				if( read( fd[m][READ_END] , &read_msg , sizeof(read_msg) ) > 0 ) {
					insert_node_last( read_msg );
					list_size++;
				}
			}
			
			// write
			if( read_msg == 0 && last_read_msg == 0 ) {
				finish = 1;
				delete_node_first();
				write_msg = -1;
				write( fd[0][WRITE_END] , &write_msg , sizeof(write_msg) );
			}
			else {
				if( list_size > 0 ) {
					write_msg = first -> value;
					delete_node_first();
					list_size--;
					write( fd[0][WRITE_END] , &write_msg , sizeof(write_msg) );
				}
			}
			
			last_read_msg = read_msg;
			
		}
		
		// Prime
		else if( process_no == m + 1 ) {
			if( read( fd[m+1][READ_END] , &read_msg , sizeof(read_msg) ) > 0 ) {
				if( read_msg == -1 ) {
					finish = 1;
				}
				else {
					printf( "%d\n" , read_msg );
					// total_prime++;
				}
			}
		}
		
		// Child
		else {
			if( read( fd[ process_no-1 ][READ_END] , &read_msg , sizeof(read_msg) ) > 0 ) {
				if( read_msg == 0 ) {
					my_prime = -1;
					write_msg = 0;
					write( fd[process_no][WRITE_END] , &write_msg , sizeof(write_msg) );
				}
				else if( read_msg == -1 ) {
					finish = 1;
					if( process_no == m ) {
						write_msg = -1;
						write( fd[m+1][WRITE_END] , &write_msg , sizeof(write_msg) );
					}
					else {
						write_msg = -1;
						write( fd[process_no][WRITE_END] , &write_msg , sizeof(write_msg) );
					}
				}
				else if( my_prime == -1 ) {
					my_prime = write_msg = read_msg;
					write( fd[m+1][WRITE_END] , &write_msg , sizeof(write_msg) );
				}
				else if( read_msg % my_prime != 0 ) {
					write_msg = read_msg;
					write( fd[process_no][WRITE_END] , &write_msg , sizeof(write_msg) );
				}
			}
		}
		
	}
	
	if( process_no == 0 )
		for( int i = 0 ; i <= m ; i++ )
			wait(NULL);
	
	// if( process_no == m+1 )
		// printf( "total: %d\n" , total_prime );
	
}

