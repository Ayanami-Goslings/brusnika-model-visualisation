import { calculateCoordinates } from "../utils.js";
export class BaseView {
    constructor(presenter) {
        this.presenter = presenter;
    }

    drawElementsInCircle(svg, centerX, centerY, radius, elements, clickCallback) {
        elements.forEach((element, index) => {
            const { x, y } = calculateCoordinates(centerX, centerY, radius, index, elements.length);
            this.drawElement(svg, x, y, element, clickCallback);
        });
    }

    drawElement(svg, x, y, element, clickCallback) {
        const rect = svg.append("rect")
            .attr("x", x - 50)
            .attr("y", y - 25)
            .attr("width", 104)
            .attr("height", 50)
            .attr("fill", this.getElementColor(element.type))
            .attr("rx", 5)
            .attr("ry", 5)
            .on("click", () => clickCallback(element))
            .on("mouseover", function() {
                d3.select(this).attr("fill", d3.rgb(d3.select(this).attr("fill")).darker(1));
            })
            .on("mouseout", function() {
                d3.select(this).attr("fill", d3.rgb(d3.select(this).attr("fill")).brighter(1));
            });

        this.drawElementText(svg, x, y, element.name);
    }

    drawElementText(svg, x, y, text) {
        svg.append("text")
            .attr("x", x)
            .attr("y", y)
            .attr("text-anchor", "middle")
            .attr("dominant-baseline", "middle")
            .attr("fill", "#FFFFFF")
            .text(text);
    }

    drawLine(svg, x1, y1, x2, y2) {
        svg.append("line")
            .attr("x1", x1)
            .attr("y1", y1)
            .attr("x2", x2)
            .attr("y2", y2)
            .attr("stroke", "#000000")
            .attr("stroke-width", 2);
    }


    getElementColor(type) {
        switch (type) {
            case "Subdivision":
                return "#5B5E5E";
            case "Department":
                return "#37465B";
            case "Group":
                return "#9ACA3C";
            default:
                return "#AAAAAA";
        }
    }
}