import { calculateCoordinates } from "../utils.js";
import { BaseView } from "./base-view.js";

export class MainView extends BaseView{
    constructor(presenter) {
        super(presenter);
        this.presenter = presenter;
    }

    drawMainView() {
        const content = document.querySelector(".content");
        content.innerHTML = '<svg width="1550" height="100%"></svg>';
        const svg = d3.select("svg");
        const centerX = 775;
        const centerY = 502;
        const radius = 300;
        const rectWidth = 208;
        const rectHeight = 50;
        const locations = this.presenter.locations;

        locations.forEach((location, index) => {
            const { x, y } = calculateCoordinates(centerX, centerY, radius, index, locations.length);
            this.drawLine(svg, centerX, centerY, x, y);
            this.drawLocationRectangle(svg, x, y, location.name);
        });

        this.drawMainRectangle(svg, centerX, centerY, rectWidth, rectHeight);
        this.drawMainText(svg, centerX, centerY);
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
            .on("click", () => this.presenter.onLocationClick(location))
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
            .on("click", () => this.presenter.onLocationClick(location))
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

    drawMainRectangle(svg, centerX, centerY, rectWidth, rectHeight) {
        const rectX = centerX - rectWidth / 2;
        const rectY = centerY - rectHeight / 2;
        svg.append("rect")
            .attr("x", rectX)
            .attr("y", rectY)
            .attr("width", rectWidth)
            .attr("height", rectHeight)
            .attr("fill", "#EF3B24")
            .attr("rx", 5)
            .attr("ry", 5);
    }

    drawMainText(svg, centerX, centerY) {
        svg.append("text")
            .attr("class", "main-text")
            .attr("x", centerX)
            .attr("y", centerY)
            .attr("fill", "#FFFFFF")
            .attr("text-anchor", "middle")
            .attr("dominant-baseline", "middle")
            .text("Брусника");
    }
}