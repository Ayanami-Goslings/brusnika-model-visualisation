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
    
        const locationNodes = this.#nodesWithEdges.filter(node => node.name === location);
    
        let elements = [];
        for (const locationNode of locationNodes) {
            const locationElements = this.getElementsFromLocation(locationNode);
            elements.push(...locationElements.map(target => target.name)); // Собираем имена подразделений
        }
    
        // Фильтрация уникальных имен подразделений
        elements = elements.filter((name, index, self) => self.indexOf(name) === index);
        
        // Преобразуем уникальные имена обратно в объекты
        elements = elements.map(name => ({ name }));
    
        elements.forEach((element, index) => {
            element.index = index;
        });
    
        const locationView = new LocationView(this, location, elements);
        locationView.drawLocationView();
    }
    
    getElementsFromLocation(locationNode) {
        const elements = [];
    
        const traverse = (node) => {
            if (node && node.targets) {
                node.targets.forEach(target => {
                    elements.push(target);
                    traverse(target); // Рекурсивный вызов для обхода дальше
                });
            }
        };
    
        traverse(locationNode);
    
        return elements;
    }

    onLocationClick(location) {
        this.showLocationView(location);
    }

    onBackToMainView() {
        this.showMainView();
    }
}