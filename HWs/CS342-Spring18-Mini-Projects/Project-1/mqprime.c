#include <sys/types.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <mqueue.h>
#include <unistd.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/wait.h>

#define READ_END (0)
#define WRITE_END (1)

struct item
{
	int key;
};

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
	
	mqd_t mq[m+2];
	
	struct item send_item;
	
	struct mq_attr mq_attr;
	struct item *itemptr;
	char *bufptr;
	int buflen;
	
	int process_no;
	
	pid_t child_pid;
	
	for( int i = 0 ; i < m+2 ; i++ ) {
		char str[15];
		sprintf( str , "/Queue%d" , i );
		if( i != m )
			mq[i] = mq_open( str , O_RDWR | O_CREAT , 0666 , NULL );
		else
			mq[i] = mq_open( str , O_RDWR | O_CREAT | O_NONBLOCK , 0666 , NULL );
		if( mq[i] < 0 ) {
			printf( "Queue failed" );
			return 1;
		}
 	}
	
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
	
	int finish = 0;
	int my_prime = -1;
	int must_read = 0;
	int list_size = 0;
	// int total_prime = 0;
	
	int read_msg = -2;
	int last_read_msg = -3;
	
	while( finish == 0 ) {
		
		// Parent
		if( process_no == 0 ) {
			
			// read
			if( must_read == 0 ) {
				for( int i = 2 ; i <= n ; i++ )
					insert_node_last( i );
				insert_node_last( 0 );
				must_read = 1;
				list_size = n;
			}
			else {
				mq_getattr(mq[m], &mq_attr);
				buflen = mq_attr.mq_msgsize;
				bufptr = (char *) malloc(buflen);
				read_msg = -2;
				if( mq_receive(mq[m], (char *) bufptr, buflen, NULL) > 0 ) {
					itemptr = (struct item *) bufptr;
					read_msg = ( itemptr -> key );
					insert_node_last( itemptr -> key );
					list_size++;
				}
				free(bufptr);
			}
			
			// write
			if( read_msg == 0 && last_read_msg == 0 ) {
				finish = 1;
				delete_node_first();
				send_item.key = -1;
				while( mq_send(mq[0], (char *) &send_item, sizeof(struct item), 0) < 0 ) { }
			}
			else {
				if( list_size > 0 ) {
					send_item.key = first -> value;
					delete_node_first();
					while( mq_send(mq[0], (char *) &send_item, sizeof(struct item), 0) < 0 ) { }
					list_size--;
				}
			}
			last_read_msg = read_msg;
		}
		
		// Prime
		else if( process_no == m + 1 ) {
			mq_getattr(mq[m+1], &mq_attr);
			buflen = mq_attr.mq_msgsize;
			bufptr = (char *) malloc(buflen);
			if( mq_receive(mq[m+1], (char *) bufptr, buflen, NULL) > 0 ) {
				itemptr = (struct item *) bufptr;
				if( (itemptr->key) == -1 ) {
					finish = 1;
				}
				else {
					printf( "%d\n" , (itemptr->key) );
					// total_prime++;
				}
			}
			free(bufptr);
		}
		
		// Chlid
		else {
			mq_getattr(mq[process_no-1], &mq_attr);
			buflen = mq_attr.mq_msgsize;
			bufptr = (char *) malloc(buflen);
			if( mq_receive(mq[process_no-1], (char *) bufptr, buflen, NULL) > 0 ) {
				itemptr = (struct item *) bufptr;
				int my_read_msg = (itemptr->key);
				if( my_read_msg == 0 ) {
					my_prime = -1;
					send_item.key = 0;
					while( mq_send(mq[process_no], (char *) &send_item, sizeof(struct item), 0) < 0 ) { }
				}
				else if( my_read_msg == -1 ) {
					finish = 1;
					if( process_no == m ) {
						send_item.key = -1;
						while( mq_send(mq[m+1], (char *) &send_item, sizeof(struct item), 0) < 0 ) { }
					}
					else {
						send_item.key = -1;
						while( mq_send(mq[process_no], (char *) &send_item, sizeof(struct item), 0) < 0 ) { }
					}
				}
				else if( my_prime == -1 ) {
					my_prime = send_item.key = my_read_msg;
					while( mq_send(mq[m+1], (char *) &send_item, sizeof(struct item), 0) < 0 ) { }
				}
				else if( my_read_msg % my_prime != 0 ) {
					send_item.key = my_read_msg;
					while( mq_send(mq[process_no], (char *) &send_item, sizeof(struct item), 0) < 0 ) { }
				}
			}
			free(bufptr);
		}
		
	}
	
	if( process_no == 0 )
		for( int i = 0 ; i <= m ; i++ )
			wait(NULL);
	
	// if( process_no == m+1 )
		// printf( "total: %d\n" , total_prime );
	
}

