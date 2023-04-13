#include <stdio.h>
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
	
struct SharedData *sharedData_ptr;

int main(int argc, char *argv[]) {
	
	char sharedmem_name[MAX_SEMAPHORE_NAME];
	strcpy(sharedmem_name ,argv[1]);
	
	char keyword[MAX_KEYWORD];
	strcpy(keyword ,argv[2]);
	
	char semaphore_prefix[MAX_SEMAPHORE_NAME];
	strcpy(semaphore_prefix ,argv[3]);
	
	if (argc != 4) {
		printf("usage: consumer \n");
		exit(1);
	}
	
	int fd = shm_open(sharedmem_name, O_RDWR, 0660);
	
	if (fd < 0) {
		perror("can not open shared memory\n");
		exit (1);
	} else {
		// printf("sharedmem open success, fd = %d \n", fd);
	}
	
	struct stat sbuf;
	fstat(fd, &sbuf);
	
	void *shm_start;
	shm_start = mmap(NULL, sbuf.st_size,PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
	
	if (shm_start < 0) {
		perror("can not map shared memory \n");
		exit (1);
	} else {
		// printf("mapping ok, start address = %lu\n", (unsigned long) shm_start);
	}
	
	close(fd);
	
	sharedData_ptr = (struct SharedData *) shm_start;
	
	//REQUEST AND STATUS SEMAPHORE INITILIAZING - BEGIN
	sem_t *sem_mutex_request; 
	sem_t *sem_full_request; 
	sem_t *sem_empty_request; 

	sem_t *sem_mutex_queue_state; 

	char semaphore_name_mutex_request_queue[MAX_SEMAPHORE_NAME];
	char semaphore_name_full_request_queue[MAX_SEMAPHORE_NAME];
	char semaphore_name_empty_request_queue[MAX_SEMAPHORE_NAME];

	char semaphore_name_mutex_queue_state[MAX_SEMAPHORE_NAME];
	
	strcpy( semaphore_name_mutex_request_queue , semaphore_prefix );
	strcat( semaphore_name_mutex_request_queue , "_MutexRequestQueue" );
	sem_mutex_request = sem_open(semaphore_name_mutex_request_queue, O_RDWR );
	//printf( "BAS %s(%d)\n" , semaphore_name_mutex_request_queue , strlen( semaphore_name_mutex_request_queue));
	
	strcpy( semaphore_name_empty_request_queue , semaphore_prefix );
	strcat( semaphore_name_empty_request_queue , "_EmptyRequestQueue" );
	sem_empty_request = sem_open(semaphore_name_empty_request_queue, O_RDWR );
	//printf( "BAS %s(%d)\n" , semaphore_name_empty_request_queue , strlen( semaphore_name_empty_request_queue));
	
	strcpy( semaphore_name_full_request_queue , semaphore_prefix );
	strcat( semaphore_name_full_request_queue , "_FullRequestQueue" );
	sem_full_request = sem_open(semaphore_name_full_request_queue, O_RDWR );
	//printf( "BAS %s(%d)\n" , semaphore_name_full_request_queue , strlen( semaphore_name_full_request_queue));
	
	strcpy( semaphore_name_mutex_queue_state , semaphore_prefix );
	strcat( semaphore_name_mutex_queue_state , "_MutexQueueState" );
	sem_mutex_queue_state = sem_open(semaphore_name_mutex_queue_state, O_RDWR );
	//printf( "BAS %s(%d)\n" , semaphore_name_mutex_queue_state , strlen( semaphore_name_mutex_queue_state));
	if(sem_mutex_queue_state<0)
		fprintf(stderr,"queue state failed\n");
	//SEMAPHORE INITILIAZING - END

	int q_id = -1;
	
	sem_wait(sem_mutex_queue_state);
	
	for( int i = 0 ; q_id == -1 && i < N ; i++ ){
		if( sharedData_ptr -> queueState[i] == 0 ) {
			q_id = i;
			sharedData_ptr -> queueState[i]++;
			//printf( "qid %s %d %d\n" , keyword , q_id , sharedData_ptr -> queueState[i] );
		}
	}	
		
	
	sem_post(sem_mutex_queue_state);
	
	if( q_id == -1 ) {
		// TODO MAYBE
		printf( "too many clients started\n" );
		// shm_unlink(sharedmem_name);
		return 0;
	}
	
	//RESULT QUEUE INITIALIZING - BEGIN
	sem_t *sem_mutex_result_queue;
	sem_t *sem_full_result_queue;
	sem_t *sem_empty_result_queue;
	
	char semaphore_name_mutex_result_queue[MAX_SEMAPHORE_NAME];
	char semaphore_name_full_result_queue[MAX_SEMAPHORE_NAME];
	char semaphore_name_empty_result_queue[MAX_SEMAPHORE_NAME];

	char c[2];
	c[0] = '0' + q_id;
	c[1] = '\0';
	
	strcpy( semaphore_name_mutex_result_queue , semaphore_prefix );
	strcat( semaphore_name_mutex_result_queue , "_MutexResultQueue_" );
	strcat( semaphore_name_mutex_result_queue , c );
	sem_mutex_result_queue = sem_open(semaphore_name_mutex_result_queue, O_RDWR );
	//printf( "BAS %s(%d)\n" , semaphore_name_mutex_result_queue , strlen( semaphore_name_mutex_result_queue ) );
	
	
	strcpy( semaphore_name_full_result_queue , semaphore_prefix );
	strcat( semaphore_name_full_result_queue , "_FullResultQueue_" );
	strcat( semaphore_name_full_result_queue , c );
	sem_full_result_queue = sem_open(semaphore_name_full_result_queue, O_RDWR);
	//printf( "BAS %s(%d)\n" , semaphore_name_full_result_queue , strlen( semaphore_name_full_result_queue ) );
	
	
	strcpy( semaphore_name_empty_result_queue , semaphore_prefix );
	strcat( semaphore_name_empty_result_queue , "_EmptyResultQueue_" );
	strcat( semaphore_name_empty_result_queue , c );
	sem_empty_result_queue = sem_open(semaphore_name_empty_result_queue, O_RDWR );
	//printf( "BAS %s(%d)\n" , semaphore_name_empty_result_queue , strlen( semaphore_name_empty_result_queue ) );
	
	//RESULT QUEUE INITIALIZING - END	
	
	sem_wait(sem_empty_request);
	sem_wait(sem_mutex_request);
	
	int _in = sharedData_ptr -> requestQueue.in;
	sharedData_ptr -> requestQueue.request[_in].id = q_id;
	strcpy( sharedData_ptr -> requestQueue.request[_in].key , keyword );
	sharedData_ptr -> requestQueue.in = ( sharedData_ptr -> requestQueue.in + 1 ) % N;

	sem_post(sem_mutex_request);
	sem_post(sem_full_request);

	while( 1 ) {
	//	if(sharedData_ptr -> resultQueue[q_id].out != sharedData_ptr -> resultQueue[q_id].in ){
			sem_wait(sem_full_result_queue);
			sem_wait(sem_mutex_result_queue);
			
			int _out = sharedData_ptr -> resultQueue[q_id].out;
			int msg = sharedData_ptr -> resultQueue[q_id].buffer[_out];
			sharedData_ptr -> resultQueue[q_id].out = ( sharedData_ptr -> resultQueue[q_id].out + 1 ) % BUFFER_SIZE;
			
			sem_post(sem_mutex_result_queue);
			sem_post(sem_empty_result_queue);
			
			if( msg == -1 )
				break;
			printf( "%d\n",  msg );
		//}
	}
		
	sem_wait(sem_mutex_queue_state);
	
	sharedData_ptr -> queueState[q_id] = 0;
	
	sem_post(sem_mutex_queue_state);
	
//	printf( "Byeee %s %d\n" , keyword , q_id );
	exit(0);
	

}
