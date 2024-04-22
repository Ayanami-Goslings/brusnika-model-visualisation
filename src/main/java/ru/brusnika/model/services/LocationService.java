package ru.brusnika.model.services;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.brusnika.model.domain.Location;
import ru.brusnika.model.repositories.ExtendedJpaRepository;
import ru.brusnika.model.repositories.LocationRepository;

import java.util.List;

@Service
public class LocationService extends EntityService<Location, Long> {
    @Autowired
    private LocationRepository locationRepository;

    //TODO возможно стоит перенести укладку графа в отдельный(е) класс(ы) + подумать над оптимизацией
    public Graph<String, DefaultEdge> buildLocationGraph() {
        Graph<String, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);

        List<Location> locations = getAll();

        locations.forEach(location -> {
            String locationName = location.getName();
            graph.addVertex(locationName);

            location.getDivisions().forEach(division -> {
                String divisionName = division.getName();
                graph.addVertex(divisionName);
                graph.addEdge(locationName, divisionName);

                location.getDepartments().forEach(department -> {
                    String departmentName = department.getName();
                    graph.addVertex(departmentName);
                    graph.addEdge(locationName, departmentName);
                });
            });
        });
        return graph;
    }

    @Override
    protected ExtendedJpaRepository<Location, Long> getRepository() {
        return locationRepository;
    }

    @Override
    protected String entityName() {
        return "Location";
    }
}