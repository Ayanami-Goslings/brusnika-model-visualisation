export async function fetchNodes() {
    const response = await fetch('../data/nodes.json');
    const nodes = await response.json();
    
    const locations = nodes.filter(node => node.type === "Location");
    
    return locations;
}


export async function fetchNodesWithEdges() {
    const response = await fetch('../data/nodesWithEdges.json');
    const nodesWithEdges = await response.json();
    
    return nodesWithEdges;
}
