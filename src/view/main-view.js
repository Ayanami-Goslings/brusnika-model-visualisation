import { calculateCoordinates } from "../utils.js";

export function drawMainView(presenter) {
    const content = document.querySelector(".content");
    content.innerHTML = '<svg width="1550" height="1004"></svg>';
    const svg = d3.select("svg");
    const centerX = 775;
    const centerY = 502;
    const radius = 300;
    const rectWidth = 208;
    const rectHeight = 50;
    const locations = ["Екатеринбург", "Тюмень", "Уфа", "Пермь", "Казань", "Омск", "Сургут", "Москва", "штаб", "Томск", "Новосибирск", "Курган"];

    locations.forEach((location, index) => {
        const { x, y } = calculateCoordinates(centerX, centerY, radius, index, locations.length);

        drawLine(svg, centerX, centerY, x, y);
        drawLocationRectangle(svg, x, y, location, presenter);
    });

    drawMainRectangle(svg, centerX, centerY, rectWidth, rectHeight);
    drawMainText(svg, centerX, centerY);
}

function drawLine(svg, x1, y1, x2, y2) {
    svg.append("line")
        .attr("x1", x1)
        .attr("y1", y1)
        .attr("x2", x2)
        .attr("y2", y2)
        .attr("stroke", "#000000")
        .attr("stroke-width", 2);
}

function drawLocationText(svg, x, y, location, presenter, rect) {
    svg.append("text")
        .attr("class", "location-text")
        .attr("x", x)
        .attr("y", y)
        .attr("text-anchor", "middle")
        .attr("dominant-baseline", "middle")
        .attr("fill", "#FFFFFF")
        .text(location)
        .on("click", () => presenter.onLocationClick(location))
        .on("mouseover", function() {
            rect.attr("fill", "#6e001f");
        })
        .on("mouseout", function() {
            rect.attr("fill", "#8C123C");
        });
}

function drawLocationRectangle(svg, x, y, location, presenter) {
    const rect = svg.append("rect")
        .attr("class", "location-rect")
        .attr("x", x - 50)
        .attr("y", y - 25)
        .attr("width", 104)
        .attr("height", 50)
        .attr("fill", "#8C123C")
        .attr("rx", 5)
        .attr("ry", 5)
        .on("click", () => presenter.onLocationClick(location))
        .on("mouseover", function() {
            d3.select(this).attr("fill", "#6e001f");
        })
        .on("mouseout", function() {
            d3.select(this).attr("fill", "#8C123C");
        });

    drawLocationText(svg, x, y, location, presenter, rect);
}

function drawMainRectangle(svg, centerX, centerY, rectWidth, rectHeight) {
    const rectX = centerX - rectWidth / 2;
    const rectY = centerY - rectHeight / 2;
    svg.append("rect")
        .attr("x", rectX)
        .attr("y", rectY)
        .attr("width", rectWidth)
        .attr("height", rectHeight)
        .attr("fill", "#EF3B24")
        .attr("rx", 5)
        .attr("ry", 5)
}

function drawMainText(svg, centerX, centerY) {
    svg.append("text")
        .attr("class", "main-text")
        .attr("x", centerX)
        .attr("y", centerY)
        .attr("fill", "#FFFFFF")
        .attr("text-anchor", "middle")
        .attr("dominant-baseline", "middle")
        .text("Брусника");
}
