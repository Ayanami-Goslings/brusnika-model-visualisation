import {drawLocationView} from "./view/location-view.js"
import {drawMainView} from "./view/main-view.js"

class Presenter {
    #currentView = null;
    #currentLocation = null;

    constructor() {
        this.#currentView = "main";
        this.init();
    }

    init() {
        this.showMainView();
    }

    showMainView() {
        this.#currentView = "main";
        drawMainView(this);
    }

    showLocationView(location) {
        this.#currentView = "location";
        this.#currentLocation = location;
        drawLocationView(this, location);
    }

    onLocationClick(location) {
        this.showLocationView(location);
    }

    onBackToMainView() {
        this.showMainView();
    }
}

const presenter = new Presenter();