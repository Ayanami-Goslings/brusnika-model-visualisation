import { LocationView } from "./view/location-view.js";
import { MainView } from "./view/main-view.js";
import { fetchNodes, fetchNodesWithEdges } from "./api.js";

export class Presenter {
    #currentView = null;
    #currentLocation = null;
    #locations = [];
    #nodesWithEdges = [];

    constructor() {
        this.#currentView = "main";
        this.init();
    }

    get locations() {
        return this.#locations;
    }

    get nodesWithEdges() {
        return this.#nodesWithEdges;
    }

    async init() {
        this.#locations = await fetchNodes();
        this.#nodesWithEdges = await fetchNodesWithEdges();
        this.showMainView();
    }

    showMainView() {
        this.#currentView = "main";
        const mainView = new MainView(this);
        mainView.drawMainView();
    }

    async showLocationView(location) {
        this.#currentView = "location";
        this.#currentLocation = location;

        // Найти дочерние элементы для выбранной локации
        const children = this.findChildren(location);
        children.forEach((child, index) => child.index = index);

        const locationView = new LocationView(this, location, children);
        locationView.drawLocationView();
    }

    findChildren(location) {
        const children = new Map();

        const findRecursive = (node, depth = 0) => {
            if (!node.target) return;

            if (depth === 0 && node.target.type === 'Subdivision') {
                if (!children.has(node.target.name)) {
                    children.set(node.target.name, node.target);
                }
                return; // Stop if a subdivision is found
            }

            if (depth === 1 && node.target.type === 'Department') {
                if (!children.has(node.target.name)) {
                    children.set(node.target.name, node.target);
                }
                return; // Stop if a department is found
            }

            if (depth === 2 && node.target.type === 'Group') {
                if (!children.has(node.target.name)) {
                    children.set(node.target.name, node.target);
                }
                return; // Stop if a group is found
            }

            // Continue recursion only if no subdivision/department/group was added
            findRecursive(node.target, depth + 1);
        };

        this.#nodesWithEdges.forEach(node => {
            if (node.name === location) {
                findRecursive(node);
            }
        });

        return Array.from(children.values());
    }

    onLocationClick(location) {
        this.showLocationView(location);
    }

    onBackToMainView() {
        this.showMainView();
    }
}