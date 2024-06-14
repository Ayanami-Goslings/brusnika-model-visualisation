import { BaseView } from "./base-view.js";
import { calculateCoordinates } from "../utils.js";

export class LocationView extends BaseView {
    constructor(presenter, location, elements) {
        super(presenter);
        this.location = location;
        this.elements = elements;
    }

    drawLocationView() {
        const content = document.querySelector(".content");
        content.innerHTML = '<svg width="1550" height="100%"></svg>';
        const svg = d3.select("svg");
        const centerX = 775;
        const centerY = 502;
        const radius = 400;

        this.drawLinesFromLocationToElements(svg, centerX, centerY, radius, this.elements);
        this.drawLocationRectangle(svg, centerX, centerY, this.location);
        this.drawElementsInCircle(svg, centerX, centerY, radius, this.elements, () => {});
    }

    drawLinesFromLocationToElements(svg, centerX, centerY, radius, elements) {
        elements.forEach((element, index) => {
            const elementCoords = calculateCoordinates(centerX, centerY, radius, index, elements.length);
            this.drawLine(svg, centerX, centerY, elementCoords.x, elementCoords.y);
        });
    }

    drawLocationRectangle(svg, x, y, location) {
        const rect = svg.append("rect")
            .attr("class", "location-rect")
            .attr("x", x - 50)
            .attr("y", y - 25)
            .attr("width", 104)
            .attr("height", 50)
            .attr("fill", "#8C123C")
            .attr("rx", 5)
            .attr("ry", 5)
            .on("click", () => this.presenter.onBackToMainView())
            .on("mouseover", function() {
                d3.select(this).attr("fill", "#690226");
            })
            .on("mouseout", function() {
                d3.select(this).attr("fill", "#8C123C");
            })
            .on("mousedown", function() {
                d3.select(this).attr("fill", "#50021C");
            })
            .on("mouseup", function() {
                d3.select(this).attr("fill", "#690226");
            });

        this.drawLocationText(svg, x, y, location, rect);
    }

    drawLocationText(svg, x, y, location, rect) {
        svg.append("text")
            .attr("class", "location-text")
            .attr("x", x)
            .attr("y", y)
            .attr("text-anchor", "middle")
            .attr("dominant-baseline", "middle")
            .attr("fill", "#FFFFFF")
            .text(location)
            .on("click", () => this.presenter.onBackToMainView())
            .on("mouseover", function() {
                rect.attr("fill", "#690226");
            })
            .on("mouseout", function() {
                rect.attr("fill", "#8C123C");
            })
            .on("mousedown", function() {
                rect.attr("fill", "#50021C");
            })
            .on("mouseup", function() {
                rect.attr("fill", "#690226");
            });
    }
}