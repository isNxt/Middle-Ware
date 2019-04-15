---
title: 'Rmi'
date: 2019-04-09
permalink: /posts/2019/04/Rmi/
tags:
  - Middleware
  - Java
---

Rmi Summary for Middleware Course

#  实验一：RPC原理和java RMI实践

## 1 实验环境
- 操作系统：Windows10 Pro
- 开发环境：IDEA
- 语言: Java
- JDK版本: 1.8.0_202

## 2 实验目的
- 掌握远程过程调用原理，基于java RMI进行远程编程和控制。
- 要求定义远程接口类及实现类

## 3 实验内容

### 3.1 查询和操作远程的图数据（Graph）：
- 服务器端构建一个图结构，可基于[jgrapht](https://jgrapht.org/guide/UserOverview#hello-jgrapht)进行操作。
- 客户端可以往这个图插入节点和边，可删除节点（附带删除边），并可查询图的节点数、边数，以及任意两个边的最短路径。


### 3.2 扩展内容【加分 0~5 分】：
- Dubbo是 阿里巴巴公司开源的一个高性能优秀的服务框架，使得应用可通过高性能的 RPC 实现服务的输出和输入功能，可以和Spring框架无缝集成。它提供了三大核心能力：面向接口的远程方法调用，智能容错和负载均衡，以及服务自动注册和发现。

- 请查阅相关资料和官网文档，尝试把以上的作业用[dubbo](http://dubbo.apache.org/en-us/docs/user/quick-start.html)实现：

## 4 实验知识点

### 4.1 什么是RPC？
- RPC 是远程过程调用（Remote Procedure Call）的缩写形式。RPC 是指计算机 A 上的进程，调用另外一台计算机 B 上的进程，其中 A 上的调用进程被挂起，而 B 上的被调用进程开始执行，当值返回给 A 时，A 进程继续执行。调用方可以通过使用参数将信息传送给被调用方，而后可以通过传回的结果得到信息。而这一过程，对于开发人员来说是透明的。

### 4.2 什么是RMI？
- RMI（Remote Method Invocation):远程方法调用，即在RPC的基础上有向前迈进了一步，提供分布式对象间的通讯。允许运行在一个java 虚拟机的对象调用运行在另一个java虚拟机上对象的方法。这两个虚拟机可以是运行在相同计

## 5 实验设计与实现过程

- 定义一个远程接口
    ```java
    package rmi;
    import org.jgrapht.*;
    import org.jgrapht.graph.*;
    import java.util.*;

    public interface Hello extends java.rmi.Remote {
    //构建一个确定的图
    List<Graph<String, DefaultEdge>> getGraph() throws java.rmi.RemoteException;
    //获取图
    Integer getPoint() throws java.rmi.RemoteException;
    //获取点
    Integer getEdge() throws java.rmi.RemoteException;
    //获取一条边
    Boolean addPoint(String name) throws java.rmi.RemoteException;
    //增加一个点
    DefaultEdge addEdge(String startPoint, String endPoint) throws java.rmi.RemoteException;
    //增加一条边
    Boolean deletePoint(String name) throws java.rmi.RemoteException;
    //删除一个点
    GraphPath<String, DefaultEdge> getShortest(String startPoint, String endPoint) throws java.rmi.RemoteException;
    //得到最短路径
    }
    ```

    
- 开发远程接口的实现类
    ```java
    package rmi;
    import org.jgrapht.*;
    import org.jgrapht.alg.connectivity.*;
    import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.*;
    import org.jgrapht.alg.interfaces.*;
    import org.jgrapht.alg.shortestpath.*;
    import org.jgrapht.graph.*;
    import java.util.*;

    public class HelloImpl implements Hello 
    {
    private Graph<String, DefaultEdge> directedGraph =
            new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

    public HelloImpl() 
    {
        directedGraph.addVertex("a");
        directedGraph.addVertex("b");
        directedGraph.addVertex("c");
        directedGraph.addVertex("d");
        directedGraph.addVertex("e");
        directedGraph.addVertex("f");
        directedGraph.addVertex("g");
        directedGraph.addEdge("a", "b");
        directedGraph.addEdge("b", "d");
        directedGraph.addEdge("d", "c");
        directedGraph.addEdge("c", "a");
        directedGraph.addEdge("e", "d");
        directedGraph.addEdge("e", "f");
        directedGraph.addEdge("f", "g");
        directedGraph.addEdge("g", "e");
        
    }
    //构建默认图
    public String sayHello(String name) {
        return "rmi.Hello, " + name + " !";
    }

    public List<Graph<String, DefaultEdge>> getGraph()
    {
        // computes all the strongly connected components of the directed graph
        StrongConnectivityAlgorithm<String, DefaultEdge> scAlg = new KosarajuStrongConnectivityInspector<>(directedGraph);
        return scAlg.getStronglyConnectedComponents();
    }

    public Integer getPoint() {
        return directedGraph.vertexSet().size();
    }

    public Integer getEdge() {
        return directedGraph.edgeSet().size();
    }

    public Boolean addPoint(String name) {
        return directedGraph.addVertex(name);
    }

    public DefaultEdge addEdge(String startPoint, String endPoint) {
        return directedGraph.addEdge(startPoint, endPoint);
    }

    public Boolean deletePoint(String name) {
        return directedGraph.removeVertex(name);
    }

    public GraphPath<String, DefaultEdge> getShortest(String startPoint, String endPoint) 
    {
        // Prints the shortest path from vertex i to vertex c. This certainly
        // exists for our particular directed graph.
        DijkstraShortestPath<String, DefaultEdge> dijkstraAlg =
                new DijkstraShortestPath<>(directedGraph);
        SingleSourcePaths<String, DefaultEdge> iPaths = dijkstraAlg.getPaths(startPoint);
        return iPaths.getPath(endPoint);
    }   
    }
    ```


- Registry和RMI Server 的创建
    ```java
    package rmi;
    import java.rmi.*;
    import java.rmi.registry.*;
    import java.rmi.server.*;

    @SuppressWarnings("ALL")
    public class Server{
    public Server() {}
    public static void main(String args[]) {
        System.setSecurityManager(new RMISecurityManager());

        final HelloImpl obj = new HelloImpl();
        try {                               // 0 - anonymous TCP port ↓
            Hello stub = (Hello)UnicastRemoteObject.exportObject(obj, 0);
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(3333);
            registry.rebind("Hello", stub);
            for(int i = 0; i < registry.list().length; i++)
                System.out.println(registry.list()[i]);
            System.err.println("Server ready....");
            System.err.println("Listinging on port 3333 ....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    ```

- RMI Client的创建
    ```java
    package rmi;
    import java.util.Scanner;
    import java.net.MalformedURLException;
    import java.rmi.registry.*;
    import java.rmi.*;
    import org.jgrapht.*;
    import org.jgrapht.graph.*;
    import java.util.*;

    public class Client {
    private Client() {
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
        String host = (args.length < 1) ? "localhost" : args[0];
        String name = (args.length == 2) ? args[1] : "World";

        String urlo = "rmi://" + host + ":3333/Hello";
        Hello stub = (Hello) Naming.lookup(urlo);
        System.out.println("link to the server: \n" + urlo);
        Registry registry = LocateRegistry.getRegistry(host);
        String response = stub.sayHello(name);
        System.out.println("Response: " + response);

        try (Scanner scan = new Scanner(System.in)) {
            String s;
            String startPoint = null;
            String endPoint = null;
            String removePoint = null;
            String newPoint = null;
            label:
            while (true) {
                System.out.println("Enter command:\n" +
                        "'s' to show the default graph\n" +
                        "'p' to show the point sum\n" +
                        "'e' to show the edge sum\n" +
                        "'+p' to add a point\n" +
                        "'+e' to add an edge\n" +
                        "'-p' to remove a point\n" +
                        "'d' to show the shortest path between 2 points\n" +
                        "'q' to quit.");
                s = scan.nextLine();
                switch (s) {
                    case "s":
                        List<Graph<String, DefaultEdge>> stronglyConnectedSubgraphs = stub.getGraph();
                        System.out.println("Strongly connected components:");
                        for (Graph<String, DefaultEdge> stronglyConnectedSubgraph : stronglyConnectedSubgraphs) {
                            System.out.println(stronglyConnectedSubgraph);
                        }
                        break;
                    case "p":
                        System.out.println("Point Sum:" + stub.getPoint());
                        break;
                    case "e":
                        System.out.println("Edge Sum:" + stub.getEdge());
                        break;
                    case "+p":
                        System.out.println("Enter new point name: ");
                        newPoint = scan.nextLine();
                        System.out.println("Add Point:" + stub.addPoint(newPoint));
                        break;
                    case "+e":
                        System.out.println("Enter new edge's start point: ");
                        startPoint = scan.nextLine();
                        System.out.println("Enter new edge's end point: ");
                        endPoint = scan.nextLine();
                        System.out.println("Add Edge:" + stub.addEdge(startPoint, endPoint));
                        break;
                    case "-p":
                        System.out.println("Enter remove point name: ");
                        removePoint = scan.nextLine();
                        System.out.println("Remove Point:" + stub.deletePoint(removePoint));
                        break;
                    case "d":
                        System.out.println("Enter start point: ");
                        startPoint = scan.nextLine();
                        System.out.println("Enter end point: ");
                        endPoint = scan.nextLine();
                        System.out.println("Shortest Path:" + stub.getShortest(startPoint, endPoint));
                        break;
                    case "q":
                        break label;
                    default:
                        System.out.println("command not found");
                        break;
                }
            }
        }
    }
    }
    ```




