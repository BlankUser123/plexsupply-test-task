# plexsupply-test-task

## Task

You need to write a console-based, multi-threaded program to calculate the factorial for each number from an input file:

https://drive.google.com/file/d/13KyD2wchh-FA8e4yUXXNtStw_KD-rV_6/view?usp=sharing

Requirements for the program:

- 1 thread reads data from the input.txt file

- 1 thread writes the results of calculations to the file output.txt, the results of calculations should be written in the format of

ix (number from the input file) = ir (factorial of the number)

1 = 1

4 = 24

6 = 720

writing thread requirement: the data must be written in the order in which they are written in the input.txt file

- pool of N threads (the size is set in the console) of the size in which the factorial calculation is performed

- [read thread] should pass the read data to [thread pool] for calculation, after receiving the result, it is necessary to pass the result from [thread pool] to [write thread].

- a delay must be added to the pool of threads for calculation, the factorial can be calculated for a maximum of 100 numbers in 1 second, this rule applies to all threads for calculation, if there are 100 threads, each thread can process 1 number in 1 second.

The file for testing is attached to the email
