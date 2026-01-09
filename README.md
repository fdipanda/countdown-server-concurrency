# countdown-server-concurrency
A Java networking project comparing iterative and concurrent TCP server designs using a countdown service.

## Overview
This project compares **iterative** and **concurrent** TCP server designs in Java using a simple countdown service.

Clients connect to a server and receive a countdown sequence. Two server implementations are provided to demonstrate how different architectures handle multiple client connections.

## Server Implementations
- **Iterative Server**
  - Handles one client at a time
  - Blocks while processing a request
- **Concurrent Server**
  - Creates a new thread for each client
  - Supports multiple simultaneous connections

## Technologies Used
- **Language:** Java
- **Networking:** TCP sockets
- **Concurrency:** Threads
- **I/O:** BufferedReader, PrintWriter

## Project Structure
- `server/`
  - `IterativeCountdownServer.java`
  - `ConcurrentCountdownServer.java`
- `client/`
  - `CountdownClient_Iterative.java`
  - `CountdownClient_Concurrent.java`

## How It Works
1. The server listens for incoming TCP connections
2. A client connects and requests a countdown
3. The server sends a sequence of decreasing numbers
4. In the concurrent server, multiple clients are serviced simultaneously using threads

## How to Run

### Compile
```bash
javac server/*.java client/*.java
```

### Run Iterative Server
```bash
java server.IterativeCountdownServer
```

### Run Concurrent Server
```bash
java server.ConcurrentCountdownServer
```

### Run a Client
```bash
java client.CountdownClient_Concurrent
```

Run multiple clients to observe the difference in server behavior.

## Academic Context
This project was developed to practice:
- TCP server design
- Iterative vs concurrent architectures
- Multithreaded server programming
- Client–server communication

## Author
Franck Dipanda
