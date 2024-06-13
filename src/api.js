export async function fetchNodes() {
    const response = await fetch('../data/nodes.json');
    const nodes = await response.json();
    
    const locations = nodes.filter(node => node.type === "Location");
    
    return locations;
}


export async function fetchNodesWithEdges() {
    const response = await fetch('../data/nodesWithEdges.json');
    const nodesWithEdges = await response.json();
    console.log(nodesWithEdges);
    return nodesWithEdges;
}

export function findDescendants(locationName, nodesWithEdges) {
    const location = nodesWithEdges.find(node => node.name === locationName);
    console.log(location);
    if (location && location.targets) {
        console.log(location);
        return location.targets;
    }
    console.log("я отработал");
    return [];
}
