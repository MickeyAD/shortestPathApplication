package gr.university.thesis.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface DijktraAlgorithm {
    double dijktraAlgorithmTimeCost(Object start, Object end);

    Object nextClosestAndUnvisitedVertex(HashMap<Object, Double> shortestPath);

    void resetVerticesVisited();
}
