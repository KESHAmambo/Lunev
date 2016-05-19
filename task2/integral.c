#include <stdlib.h>
#include <omp.h>
#include <assert.h>

#include <math.h>
#include <stdio.h>
#include <string.h>

double integral(int);

double f(double x)
{
	return 0;
}

int main(int argc, char **argv)
{
	if(argc != 2)
	{
		assert(!"Invalid number of arguments");
	}
	int threads = strtol(argv[1], NULL, 10);
	if(threads < 1)
	{
		fprintf(stderr, "%d\n", threads);
		assert(!"Invalid number of threads");	
	}
	threads = (threads > omp_get_num_procs()) ? omp_get_num_procs() : threads;	
	
	const double t0 = omp_get_wtime();	
	integral(threads);
//	std::cout << "result " << integral(threads) << std::endl;
//	std::cout << " time " << omp_get_wtime() - t0 << std::endl;	
//	printf("%lf\n", integral(threads));
	printf("%lf\n", omp_get_wtime() - t0);		
	return 0;
}


double integral(int threads)
{
	const int a = 0;
	const int b = 10000;
	const int N = 10000 / 0.00001;
	double h = (b - a) / N;
	double result  = 0;
	double x = a;
	
	omp_set_num_threads(threads);
	#pragma omp parallel for  reduction(+: result)  schedule(guided, 1000)
	for( int i = 0; i < N; i += 2)
	{		
		result = (h / 3) * (f(x+ i*h) + 4 * f(x + (i+1)*h) + f(x + (i+2)*h)) + result;		
	}
	return result; 	
}
