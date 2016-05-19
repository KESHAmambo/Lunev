#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <errno.h>
#include <unistd.h>


#define ASSERT( cond, message )         
            if ( ! ( cond ) ){  
                        printf("Fatal error %s file: %s line: %d\n", #message, __FILE__, __LINE__);              
                        return -1;          
                }

const int CORE_MAX = 128;
const double A = 0;
const double B = 10000;
const double STEP = 0.000001;

#define FUNC(x) 0

typedef struct Core_Arg
{
        double down;
        double up;
        double res;
} Core_Arg_t;

void *integral(void *raw_arg) 
{
	Core_Arg_t *arg = (Core_Arg_t *) raw_arg;
    double res = 0;
    double a = arg->down;
    double b = arg->up;
    double x = a;
    double h = STEP;
    unsigned long N = (b - a) / STEP;
    long i = 0;
    
    for( int i = 0; i < N; i += 2)
    {       
        res = (h / 3) * (FUNC(x+ i*h) + 4 * FUNC(x + (i+1)*h) + FUNC(x + (i+2)*h)) + res;      
    }

    arg->res = res; 
    return NULL;
}

int main(int argc, char* argv[]) 
{
	ASSERT(argc == 2, "Bad input");
        ASSERT(argv[1],   "Bad input");
        
	char *rub = NULL;
        int n = strtol(argv[1], &rub, 0);
        
	ASSERT(!*rub && errno != ERANGE, "Bad input");
        ASSERT(n > 0 && n <= CORE_MAX, "Bad core number");
	
        int i = 0;
	double step = (B - A) / n;
	double res = 0;

        Core_Arg_t args[CORE_MAX];
        pthread_t core_list[CORE_MAX];
        for (i = 0; i < n; i++) {
		args[i].down = A + i * step;
		args[i].up = A + (i + 1) * step;
        	pthread_create(core_list + i, NULL, &integral, (void *) &args[i]);
        }
	 
        for (i = 0; i < n; i++)
        	pthread_join(core_list[i], NULL);
        
        for (i = 0; i < n; i++)
        	res += args[i].res;
	
	printf("%lf", res);
        return 0;
}