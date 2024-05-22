import { LocationView } from "./view/location-view.js";
import { MainView } from "./view/main-view.js";

export class Presenter {
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
        const mainView = new MainView(this);
        mainView.drawMainView();
    }

    showLocationView(location) {
        this.#currentView = "location";
        this.#currentLocation = location;
        const locationView = new LocationView(this, location);
        locationView.drawLocationView();
    }

    onLocationClick(location) {
        this.showLocationView(location);
    }

    onBackToMainView() {
        this.showMainView();
    }
}

