all: task2 pthread


CFLAGS:= -std=c99  -Wall -MD -fopenmp -pthread
LDLIBS:= -lm

INCL= -I ./ 

task2: integral.o
	$(CC) $(CFLAGS) $(LDLIBS) -o $@ $^ 
	@echo "--------------------------------"
	@echo "$@ is built successfully."

pthread: pthread.o
	$(CC) $(CFLAGS) $(LDLIBS) -o $@ $^ 
	@echo "--------------------------------"
	@echo "$@ is built successfully."

integarl.o: integral.c
	$(CC) $(CFLAGS) $(LDLIBS) -c $< 

pthread.o: pthread.c
	$(CC) $(CFLAGS) $(LDLIBS) -c $< 

clean:
	 rm -rf *.o *.d task2 pthread

.PHONY: clean