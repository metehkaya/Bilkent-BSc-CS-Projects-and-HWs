#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <string.h>
#include <errno.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <pthread.h>
#include <semaphore.h>

#include "commondefs.h"

char inputfile_name[MAX_FILENAME];
char semaphore_prefix[MAX_SEMAPHORE_NAME];

struct SharedData *sharedData_ptr;

sem_t *sem_mutex_result_queue[N];
sem_t *sem_full_result_queue[N];
sem_t *sem_empty_result_queue[N];

sem_t *sem_mutex_request; /* protects the buffer */
sem_t *sem_full_request; /* counts the number of items */
sem_t *sem_empty_request; /* counts the number of empty buffer slots */

sem_t *sem_mutex_queue_state; /* protects the buffer */

char semaphore_name_mutex_result_queue[MAX_SEMAPHORE_NAME];
char semaphore_name_full_result_queue[MAX_SEMAPHORE_NAME];
char semaphore_name_empty_result_queue[MAX_SEMAPHORE_NAME];
	
char semaphore_name_mutex_request_queue[MAX_SEMAPHORE_NAME];
char semaphore_name_full_request_queue[MAX_SEMAPHORE_NAME];
char semaphore_name_empty_request_queue[MAX_SEMAPHORE_NAME];

char semaphore_name_mutex_queue_state[MAX_SEMAPHORE_NAME];

struct arg {
	int id;
	char keyword[MAX_KEYWORD];
};

/*
void makeInput() {
	
	memset( line_characters , 0 , sizeof( line_characters ) );
	
	char c;
	FILE *file;
	file = fopen(inputfile_name, "r");
	
	if (file) {
		while ((c = getc(file)) != EOF) {
			if( c == '\n' ) {
				// printf( "total chs %d - %d\n" , total_lines , line_characters[total_lines] );
				line_characters[ ++total_lines ] = 0;
			}
			else
				text[total_lines][ line_characters[total_lines]++ ] = c;
		}
		fclose(file);
	}
	
}
* */

static void *func( void *arg_ptr ) {
	
	char keyword[MAX_KEYWORD];
	strcpy( keyword , ( (struct arg *) arg_ptr ) -> keyword );
	
	int id = ( (struct arg *) arg_ptr ) -> id;
	
	//printf( "func: %d %s\n" , id , keyword );
	
	int m = strlen( keyword );
	
	char c;
	FILE *file;
	file = fopen(inputfile_name, "r");
	
	int varEOF = 0;
	
	if( file ) {}
	else varEOF = 1;
	
	int _in;
	
	for( int i = 0 ; varEOF == 0 ; i++ ) {
		
		int lc = 0;
		char text[MAX_LINE_CHARACTER];
		
		if (file) {
			while ( 1 ) {
				c = getc(file);
				if( c == EOF ) {
					varEOF = 1;
					break;
				}
				if( c == '\n' )
					break;
				else
					text[lc++] = c;
			}
		}
		
		int found_keyword = 0;
		for( int j = 0 ; found_keyword == 0 && j <= lc - m ; j++ ) {
			int found = 1;
			for( int k = 0 ; k < m && found == 1 ; k++ )
				if( keyword[k] != text[j+k] )
					found = 0;
			if( found == 1 ) {
				// printf( "Found %d %d: %s\n" , i + 1 , j , keyword );
					
				sem_wait(sem_empty_result_queue[id]);
				sem_wait(sem_mutex_result_queue[id]);
				
				_in = sharedData_ptr -> resultQueue[id].in;
				sharedData_ptr -> resultQueue[id].buffer[_in] = i + 1;
				sharedData_ptr -> resultQueue[id].in = ( sharedData_ptr -> resultQueue[id].in + 1 ) % BUFFER_SIZE;
				found_keyword = 1;
				
				sem_post(sem_mutex_result_queue[id]);
				sem_post(sem_full_result_queue[id]);

			}
		}
		
	}
	
	sem_wait(sem_empty_result_queue[id]);
	sem_wait(sem_mutex_result_queue[id]);
	
	_in = sharedData_ptr -> resultQueue[id].in;
	sharedData_ptr -> resultQueue[id].buffer[_in] = -1;
	sharedData_ptr -> resultQueue[id].in = ( sharedData_ptr -> resultQueue[id].in + 1 ) % BUFFER_SIZE;
	
	sem_post(sem_mutex_result_queue[id]);
	sem_post(sem_full_result_queue[id]);
	
	fclose(file);
	
	pthread_exit(NULL);
	
}

int main(int argc, char **argv) {
	
	char sharedmem_name[MAX_SEMAPHORE_NAME];
	
	strcpy(sharedmem_name ,argv[1]);
	strcpy(inputfile_name ,argv[2]);
	strcpy(semaphore_prefix ,argv[3]);
	
	shm_unlink (sharedmem_name);
	
	// makeInput();
	
	if (argc != 4) {
		printf("usage: producer\n");
		exit(1);
	}
	
	int fd = shm_open( sharedmem_name , O_RDWR | O_CREAT , 0660 );
	if (fd < 0) {
		perror("can not create shared memory\n");
		exit (1);
	} else {
		// printf("sharedmem create success, fd = %d\n", fd);
	}
	
	ftruncate(fd, SHAREDMEM_SIZE);
	
	struct stat sbuf;
	fstat(fd, &sbuf); 
	
	void *shm_start;
	shm_start = mmap( NULL , sbuf.st_size , PROT_READ | PROT_WRITE , MAP_SHARED , fd , 0);
	
	if (shm_start < 0) {
		perror("can not map the shared memory \n");
		exit (1);
	} else {
		// printf("mapping ok, start address = %lu\n", (unsigned long) shm_start);
	}
	
	close(fd);
	
	sharedData_ptr = (struct SharedData *) shm_start;
	
	//SEMAPHORE INITILIAZING - BEGIN
	for( int i = 0 ; i < N ; i++ ) {
		char c[2];
		c[0] = '0' + i;
		c[1] = '\0';
		strcpy( semaphore_name_mutex_result_queue , semaphore_prefix );
		strcat( semaphore_name_mutex_result_queue , "_MutexResultQueue_" );
		strcat( semaphore_name_mutex_result_queue , c );
		sem_unlink( semaphore_name_mutex_result_queue );
		sem_mutex_result_queue[i] = sem_open(semaphore_name_mutex_result_queue, O_RDWR | O_CREAT, 0660, 1);
		//printf( "BAS %s(%d)\n" , semaphore_name_mutex_result_queue , strlen( semaphore_name_mutex_result_queue ) );
	}
	
	for( int i = 0 ; i < N ; i++ ) {
		char c[2];
		c[0] = '0' + i;
		c[1] = '\0';
		strcpy( semaphore_name_full_result_queue , semaphore_prefix );
		strcat( semaphore_name_full_result_queue , "_FullResultQueue_" );
		strcat( semaphore_name_full_result_queue , c );
		sem_unlink( semaphore_name_full_result_queue );
		sem_full_result_queue[i] = sem_open(semaphore_name_full_result_queue, O_RDWR | O_CREAT, 0660, 0);
	//	printf( "BAS %s(%d)\n" , semaphore_name_full_result_queue , strlen( semaphore_name_full_result_queue ) );
	}
	
	for( int i = 0 ; i < N ; i++ ) {
		char c[2];
		c[0] = '0' + i;
		c[1] = '\0';
		strcpy( semaphore_name_empty_result_queue , semaphore_prefix );
		strcat( semaphore_name_empty_result_queue , "_EmptyResultQueue_" );
		strcat( semaphore_name_empty_result_queue , c );
		sem_unlink( semaphore_name_empty_result_queue );
		sem_empty_result_queue[i] = sem_open(semaphore_name_empty_result_queue, O_RDWR | O_CREAT, 0660, BUFFER_SIZE);
		//printf( "BAS %s(%d)\n" , semaphore_name_empty_result_queue , strlen( semaphore_name_empty_result_queue ) );
	}
	
	strcpy( semaphore_name_mutex_request_queue , semaphore_prefix );
	strcat( semaphore_name_mutex_request_queue , "_MutexRequestQueue" );
	sem_unlink( semaphore_name_mutex_request_queue );
	sem_mutex_request = sem_open(semaphore_name_mutex_request_queue, O_RDWR | O_CREAT, 0660, 1);
	//printf( "BAS %s(%d)\n" , semaphore_name_mutex_request_queue , strlen( semaphore_name_mutex_request_queue));
	
	strcpy( semaphore_name_empty_request_queue , semaphore_prefix );
	strcat( semaphore_name_empty_request_queue , "_EmptyRequestQueue" );
	sem_unlink( semaphore_name_empty_request_queue );
	sem_empty_request = sem_open(semaphore_name_empty_request_queue, O_RDWR | O_CREAT, 0660, N);
	//printf( "BAS %s(%d)\n" , semaphore_name_empty_request_queue , strlen( semaphore_name_empty_request_queue));
	
	strcpy( semaphore_name_full_request_queue , semaphore_prefix );
	strcat( semaphore_name_full_request_queue , "_FullRequestQueue" );
	sem_unlink( semaphore_name_full_request_queue );
	sem_full_request = sem_open(semaphore_name_full_request_queue, O_RDWR | O_CREAT, 0660, 0);
	//printf( "BAS %s(%d)\n" , semaphore_name_full_request_queue , strlen( semaphore_name_full_request_queue));
	
	strcpy( semaphore_name_mutex_queue_state , semaphore_prefix );
	strcat( semaphore_name_mutex_queue_state , "_MutexQueueState" );
	sem_unlink( semaphore_name_mutex_queue_state );
	sem_mutex_queue_state = sem_open(semaphore_name_mutex_queue_state, O_RDWR | O_CREAT, 0660, 1);
	//printf( "BAS %s(%d)\n" , semaphore_name_mutex_queue_state , strlen( semaphore_name_mutex_queue_state));
	
	//SEMAPHORE INITILIAZING - END
	
	for( int i = 0 ; i < N ; i++ )
		sharedData_ptr -> queueState[i] = 0;
	
	for( int i = 0 ; i < N ; i++ ) {
		sharedData_ptr -> resultQueue[i].in = 0;
		sharedData_ptr -> resultQueue[i].out = 0;
		for( int j = 0 ; j < BUFFER_SIZE ; j++ )
			sharedData_ptr -> resultQueue[i].buffer[j] = 0;
	}
	
	sharedData_ptr -> requestQueue.in = 0;
	sharedData_ptr -> requestQueue.out = 0;
	
	pthread_t tids[N];
	struct arg t_args[N];
	
	while( 1 ) {
	//	if( sharedData_ptr ->requestQueue.in != sharedData_ptr ->requestQueue.out ){
		
			sem_wait(sem_full_request);
			sem_wait(sem_mutex_request);
			
			int _out = sharedData_ptr -> requestQueue.out;
			int id = sharedData_ptr -> requestQueue.request[_out].id;
			struct Request reqClient = sharedData_ptr -> requestQueue.request[_out];
			sharedData_ptr -> requestQueue.out = ( sharedData_ptr -> requestQueue.out + 1 ) % N;

			sem_post(sem_mutex_request);
			sem_post(sem_empty_request);
		
			t_args[id].id = reqClient.id;			
			strcpy( t_args[id].keyword , reqClient.key );
			int ret = pthread_create( &(tids[id]) , NULL , func , (void *) &(t_args[id]) );
			
			if( ret != 0 ) {
				printf( "thread create failed\n" );
				exit(1);
			}
			// ret = pthread_join(tids[id], NULL);		
	//	}
	}
	

	exit(0);
	
}
