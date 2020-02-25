package gr.university.thesis.service;

import gr.university.thesis.entity.Edge;
import gr.university.thesis.repository.EdgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EdgeService {

    @Autowired
    private EdgeRepository edgeRepository;

    public List<Edge> findEdges() {
        return edgeRepository.findAll();
    }

    public Edge findEdgeById(Integer id) throws Exception {
        Optional<Edge> edge = edgeRepository.findById(id);
        if (edge.isPresent()) {
            return edge.get();
        } else {
            throw new Exception("Could not find edge with id: " + id);
        }
    }

//    public Edge createEdge(Edge edge) {
//        return edgeRepository.save(edge);
//    }
//
//    public void deleteEdgeById(Long id) {
//        edgeRepository.deleteById(id);
//    }
//
//    public void deleteAllEdges() {
//        edgeRepository.deleteAll();
//    }
//
//    public Edge updateEdge(Edge edge) {
//        return edgeRepository.save(edge);
//    }
}
