#include <stdlib.h>
#include <omp.h>
#include <assert.h>

#include <cmath>
#include <iostream>

double integral(int);

double f(double x)
{
	return pow(2, pow(2, pow(2,x)));
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
		std::cerr << threads << std::endl;
		assert(!"Invalid number of threads");	
	}
	threads = (threads > omp_get_num_procs()) ? omp_get_num_procs() : threads;	
	
	const double t0 = omp_get_wtime();	
	std::cout << "result " << integral(threads) << std::endl;
	std::cout << " time " << omp_get_wtime() - t0 << std::endl;			
	return 0;
}


double integral(int threads)
{
	const int a = -200;
	const int b = 200;
	const int N = 10000000;
	double h = (double(b) - a) / N;
	double result  = 0;
	double x = a;
	
	omp_set_num_threads(threads);
	#pragma omp parallel for  reduction(+: result) // schedule(dynamic)
//	{	
//		#pragma omp for
		for( int i = 0; i < N; i += 2)
		{	
//			std::cout << omp_get_thread_num() << std::endl;		
			result = (h / 3) * (f(x+ i*h) + 4 * f(x + (i+1)*h) + f(x + (i+2)*h)) + result;
//		}		
	}
	return result; 	
}
