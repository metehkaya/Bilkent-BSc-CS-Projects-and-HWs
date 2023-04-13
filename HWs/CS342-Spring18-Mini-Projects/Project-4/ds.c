#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <math.h>

#define  maxf 200

typedef  long long LL;

struct req {
	int rTime, rPos;
};

int n,m;
char inputFileName[maxf];

LL totalTime;
LL totalHeadMovements;
int *visited;
LL *reqWaitTime;

struct req *requests;

void displayStats( int type ) {
	
	switch(type) {
		case 1:	printf("FCFS :"); break;
		case 2: printf("SSTF :"); break;
		case 3: printf("LOOK :"); break;
		case 4: printf("CLOOK:"); break;
	}
	
	// Total Time
	printf( " %lld" , totalHeadMovements );
	
	// Average
	double sum = 0 , avg;
	for( int i = 0 ; i < n ; i++ )
		sum += reqWaitTime[i];
	avg = sum / n;
	printf( " %lf" , avg );
	
	// Standard Deviation
	double var = 0 , sdev;
	for( int i = 0 ; i < n ; i++ )
		var += ( reqWaitTime[i] - avg ) * ( reqWaitTime[i] - avg );
	var /= n - 1;
	sdev = sqrt( var );
	printf( " %lf\n" , sdev );
	
}

void applyFCFS() {
	
	totalHeadMovements = totalTime = 0;
	
	for( int i = 0 , curr = 1 ; i < n ; i++ ) {
		int dist = abs( curr - requests[i].rPos );
		if( totalTime < requests[i].rTime )
			totalTime = requests[i].rTime;
		reqWaitTime[i] = totalTime - requests[i].rTime;
		totalTime += dist;
		totalHeadMovements += dist;
		curr = requests[i].rPos;
	}
	
	displayStats( 1 );
	
}

void applySSTF() {
	
	totalHeadMovements = totalTime = 0;
	for( int i = 0 ; i < n ; i++ )
		visited[i] = 0;
	
	for( int done = 0 , next = 0 , curr = 1 ; done < n ; done++ ) {
		
		while( next < n && requests[next].rTime <= totalTime )
			next++;
		
		if( done == next ) {
			totalTime = requests[next].rTime;
			while( next < n && requests[next].rTime <= totalTime )
				next++;
		}
		
		int bestReq = -1 , bestDist = -1;
		for( int i = 0 ; i < next ; i++ )
			if( visited[i] == 0 ) {
				int dist = abs( curr - requests[i].rPos );
				if( bestReq == -1 || dist < bestDist ) {
					bestReq = i;
					bestDist = dist;
				}
			}
		
		visited[bestReq] = 1;
		reqWaitTime[bestReq] = totalTime - requests[bestReq].rTime;
		totalTime += bestDist;
		totalHeadMovements += bestDist;
		curr = requests[bestReq].rPos;
		
	}
	
	displayStats( 2 );
	
}

void applyLOOK() {
	
	totalHeadMovements = totalTime = 0;
	for( int i = 0 ; i < n ; i++ )
		visited[i] = 0;
	
	for( int done = 0 , next = 0 , curr = 1 , dir = +1 ; done < n ; done++ ) {
		
		while( next < n && requests[next].rTime <= totalTime )
			next++;
		
		if( done == next ) {
			totalTime = requests[next].rTime;
			while( next < n && requests[next].rTime <= totalTime )
				next++;
		}
		
		int bestLeftReq = -1 , bestLeftDist = -1;
		int bestSameReq = -1;
		int bestRightReq = -1 , bestRightDist = -1;
		
		for( int i = 0 ; i < next ; i++ )
			if( visited[i] == 0 ) {
				int dist = abs( curr - requests[i].rPos );
				if( requests[i].rPos < curr ) {
					if( bestLeftReq == -1 || dist < bestLeftDist ) {
						bestLeftReq = i;
						bestLeftDist = dist;
					}
				}
				else if( requests[i].rPos > curr ) {
					if( bestRightReq == -1 || dist < bestRightDist ) {
						bestRightReq = i;
						bestRightDist = dist;
					}
				}
				else {
					if( bestSameReq == -1 )
						bestSameReq = i;
				}
			}
		
		int bestReq = -1 , bestDist = -1;
		
		if( bestSameReq != -1 ) {
			bestReq = bestSameReq;
			bestDist = 0;
		}
		else if( dir == +1 ) {
			if( bestRightReq != -1 ) {
				bestReq = bestRightReq;
				bestDist = bestRightDist;
			}
			else if( bestLeftReq != -1 ) {
				dir = -1;
				bestReq = bestLeftReq;
				bestDist = bestLeftDist;
			}
		}
		else if( dir == -1 ) {
			if( bestLeftReq != -1 ) {
				bestReq = bestLeftReq;
				bestDist = bestLeftDist;
			}
			else if( bestRightReq != -1 ) {
				dir = +1;
				bestReq = bestRightReq;
				bestDist = bestRightDist;
			}
		}
		
		visited[bestReq] = 1;
		reqWaitTime[bestReq] = totalTime - requests[bestReq].rTime;
		totalTime += bestDist;
		totalHeadMovements += bestDist;
		curr = requests[bestReq].rPos;
		
	}
	
	displayStats( 3 );
	
}

void applyCLOOK() {
	
	totalHeadMovements = totalTime = 0;
	for( int i = 0 ; i < n ; i++ )
		visited[i] = 0;
	
	for( int done = 0 , next = 0 , curr = 1 ; done < n ; done++ ) {
		
		while( next < n && requests[next].rTime <= totalTime )
			next++;
		
		if( done == next ) {
			totalTime = requests[next].rTime;
			while( next < n && requests[next].rTime <= totalTime )
				next++;
		}
		
		int bestLeftReq = -1 , bestLeftDist = -1;
		int bestSameReq = -1;
		int bestRightReq = -1 , bestRightDist = -1;
		
		for( int i = 0 ; i < next ; i++ )
			if( visited[i] == 0 ) {
				int dist = abs( curr - requests[i].rPos );
				if( requests[i].rPos < curr ) {
					if( bestLeftReq == -1 || dist > bestLeftDist ) {
						bestLeftReq = i;
						bestLeftDist = dist;
					}
				}
				else if( requests[i].rPos > curr ) {
					if( bestRightReq == -1 || dist < bestRightDist ) {
						bestRightReq = i;
						bestRightDist = dist;
					}
				}
				else {
					if( bestSameReq == -1 )
						bestSameReq = i;
				}
			}
		
		int bestReq = -1 , bestDist = -1;
		
		if( bestSameReq != -1 ) {
			bestReq = bestSameReq;
			bestDist = 0;
		}
		else if( bestRightReq != -1 ) {
			bestReq = bestRightReq;
			bestDist = bestRightDist;
		}
		else if( bestLeftReq != -1 ) {
			bestReq = bestLeftReq;
			bestDist = bestLeftDist;
		}
		
		visited[bestReq] = 1;
		reqWaitTime[bestReq] = totalTime - requests[bestReq].rTime;
		totalTime += bestDist;
		totalHeadMovements += bestDist;
		curr = requests[bestReq].rPos;
		
	}
	
	displayStats( 4 );
	
}

int getN() {
	int ttime , pos;
	FILE *file = fopen( inputFileName , "r" );
	if(file) {
		n = 0;
		while( !feof(file) ) {
			fscanf( file , "%d %d\n" , &ttime , &pos );
			n++;
		}
		fclose(file);
		return 1;
	}
	else
		return 0;
}

int getInput() {
	FILE *file = fopen( inputFileName , "r" );
	if( file ) {
		int cnt = 0;
		requests = malloc( sizeof(struct req) * n );
		while( !feof(file) ) {
			fscanf( file , "%d %d\n" , &requests[cnt].rTime , &requests[cnt].rPos );
			cnt++;
		}
		fclose(file);
		return 1;
	}
	else
		return 0;
}

int readInput() {
	if( getN() == 0 )
		return 0;
	if( getInput() == 0 )
		return 0;
	return 1;
}

void displayText() {
	puts("----- Display Input -----");
	printf( "%d %d\n" , n , m );
	for( int i = 0 ; i < n ; i++ )
		printf( "%d %d %d\n" , i , requests[i].rTime , requests[i].rPos );
	puts("----- Display Input -----");
}

void freeMemoryLeak() {
	free(requests);
	free(visited);
	free(reqWaitTime);
}

int main( int argc , char *argv[] ) {
	
	m = atoi( argv[1] );
	strcpy( inputFileName , argv[2] );
	
	if( readInput() == 0 ) {
		puts( "Invalid input file name!" );
		return 0;
	}
	
	// displayText();
	
	visited = malloc( sizeof(int) * n );
	reqWaitTime = malloc( sizeof(LL) * n );
	
	applyFCFS();
	applySSTF();
	applyLOOK();
	applyCLOOK();
	
	freeMemoryLeak();
	
	return 0;
	
}
