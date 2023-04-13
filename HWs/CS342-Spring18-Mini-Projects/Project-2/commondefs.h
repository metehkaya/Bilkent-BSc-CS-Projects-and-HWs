#define BUFFER_SIZE 100
#define N 10
#define MAX_KEYWORD 132


struct ResultQueue {
	int in;
	int out;
	int buffer[BUFFER_SIZE];
};

struct Request {
	int id;
	char key[MAX_KEYWORD];
};

struct RequestQueue {
	int in;
	int out;
	struct Request request[N];
};

struct SharedData {
	int queueState[N];
	struct ResultQueue resultQueue[N];
	struct RequestQueue requestQueue;
};

#define SHAREDMEM_SIZE 6000
#define MAX_LINE_CHARACTER 1028
#define MAX_FILENAME 132
#define MAX_SHARED_MEMORY_NAME 132
#define MAX_SEMAPHORE_NAME 200
