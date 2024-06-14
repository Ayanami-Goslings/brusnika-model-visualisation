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
        const childrenMap = new Map();

        const findRecursive = (node) => {
            if (!node.target) return;

            if (node.target.type === 'Subdivision') {
                childrenMap.set(node.target.name, node.target);
                return;
            }

            if (node.target.type === 'Department' && !childrenMap.has(node.target.name)) {
                childrenMap.set(node.target.name, node.target);
                return;
            }

            if (node.target.type === 'Group' && !childrenMap.has(node.target.name)) {
                childrenMap.set(node.target.name, node.target);
                return;
            }

            findRecursive(node.target);
        };

        this.#nodesWithEdges.forEach(node => {
            if (node.name === location) {
                findRecursive(node);
            }
        });

        return Array.from(childrenMap.values());
    }

    onLocationClick(location) {
        this.showLocationView(location);
    }

    onBackToMainView() {
        this.showMainView();
    }
}