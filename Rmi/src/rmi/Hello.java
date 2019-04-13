package rmi;
import java.rmi.*;
import java.rmi.server.*;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.jgrapht.*;
import org.jgrapht.alg.connectivity.*;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.*;
import org.jgrapht.alg.interfaces.*;
import org.jgrapht.alg.shortestpath.*;
import org.jgrapht.graph.*;

import java.util.*;
import java.rmi.*;
public interface Hello extends java.rmi.Remote {
    // constructs a directed graph with the specified vertices and edges
    String sayHello(String name) throws java.rmi.RemoteException;

    List<Graph<String, DefaultEdge>> getGraph() throws java.rmi.RemoteException;
    Integer getPoint() throws java.rmi.RemoteException;
    Integer getEdge() throws java.rmi.RemoteException;

    Boolean addPoint(String name) throws java.rmi.RemoteException;
    DefaultEdge addEdge(String startPoint, String endPoint) throws java.rmi.RemoteException;

    Boolean deletePoint(String name) throws java.rmi.RemoteException;

    GraphPath<String, DefaultEdge> getShortest(String startPoint, String endPoint) throws java.rmi.RemoteException;
}