# CPU Scheduling using Priority and Round Robin
<p align=center><img src="https://github.com/TheJaberi/Benefit-AI-Hackathon-2024/assets/151864110/4625146f-211d-4d8a-a3a4-f242dbcf8961" width="200"><img src="https://github.com/TheJaberi/Benefit-AI-Hackathon-2024/assets/151864110/d94f0417-596a-4f91-9614-488ceff25b50" width="200"><p>

## Description
This Project is a CPU Process Scedhuling emulator for operating systems done in java, using the priority scedhuling algorithm with round robin. It takes the process ID, arrival time, burst time, and priority of process as input, then schedules the processes according to the algorithm, and then displays a Gantt chart showing the the order of processes and time taken by each process.

`App.java` is the main file

## Sample input/output using presented process

### input

```
Enter quantum time (q): 3
Do you like to use preentered process (yes/no)?yes
enter test num (0-6): 3
```

### output

```
Gantt Chart:
0-p1-3-p2-6-p1-9-p2-12-p1-15-p2-18-p1-19-p2-20
-----------------------
pid: 1
Arrival Time: 0
Bust Time: 10
Priority: 1
Turnaround Time: 19
Response Time: 0
Waiting Time: 9
-----------------------
pid: 2
Arrival Time: 0
Bust Time: 10
Priority: 1
Turnaround Time: 20
Response Time: 3
Waiting Time: 10
-----------------------
average turnaround time: 19.5
average Waiting time: 9.5
average Response time: 1.5
```

## Usage
1. Compile and run the program
```
javac *.java
App.java
```
2. Enter inputs based on the prompt [Quantum time, PID, Arrival time, Burst time and it's priority] or use one of the six preentered test cases.
3. If not a preentered test case, you may stop giving processes as input by giving a process a value of all zeros for all prompts.

## Contributors
* [Ahmed Yusuf Mansoor Khamdan](https://github.com/codemanbh) 
* [Sayed Hesham Ahmed](https://github.com/heshamalmosawi)
* [Fatima Abbas Ali](https://github.com/xlvk)
* [Sayed Mohamed Majeed](https://github.com/altenen-dev)
* [Mohammed A.Redha Ismaeel](https://github.com/MohammedRedha126)