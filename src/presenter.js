import { LocationView } from "./view/location-view.js";
import { MainView } from "./view/main-view.js";
import {fetchNodes, fetchNodesWithEdges, findDescendants} from "./api.js"

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
        const elements = [
            { id: 238, name: "Персей", type: "Subdivision" },
            { id: 239, name: "Финляндия", type: "Department" },
            { id: 240, name: "Марс", type: "Group" },
            { id: 238, name: "Персей", type: "Subdivision" },
            { id: 238, name: "Персей", type: "Subdivision" },
            { id: 238, name: "Персей", type: "Group"},
            { id: 238, name: "Персей", type: "Subdivision" },
            { id: 238, name: "Персей", type: "Subdivision" },
            { id: 238, name: "Персей", type: "Subdivision" },
            { id: 238, name: "Персей", type: "Subdivision" },
        ];
        elements.forEach((element, index) => {
            element.index = index;
        });
        const locationView = new LocationView(this, location, elements);
        locationView.drawLocationView();
    }

    onLocationClick(location) {
        this.showLocationView(location);
    }

    onBackToMainView() {
        this.showMainView();
    }
}

