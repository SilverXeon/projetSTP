# projetSTP
Implementation of simple temporal problem in a web-service in Java.


For the server you need to compile with all the jar of the lib directory.

For the client, you need to compile with the jar of the jaxws lib directory.


Visualisation of graph.
If you want to use the visualisation of the graph, you need to have an active display on the server.

You can also use a virtual display with [Xvfb](https://www.x.org/archive/X11R7.6/doc/man/man1/Xvfb.1.xhtml).

```
Xvfb :1 &
DISPLAY=:1 java -cp "bin:lib/jgrapht/*" fr.univ_artois.stp.server.STPPublisher
```
