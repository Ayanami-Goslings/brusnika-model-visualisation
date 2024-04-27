

const svg = d3.select("svg");

const centerX = 810;
const centerY = 502;

const rectWidth = 208;
const rectHeight = 50;

svg.append("rect")
    .attr("x", centerX - rectWidth / 2)
    .attr("y", centerY - rectHeight / 2)
    .attr("width", rectWidth)
    .attr("height", rectHeight)
    .attr("fill", "#EF3B24")
    .attr("stroke", "black")
    .attr("stroke-width", 2)
    .attr("rx", 5)
    .attr("ry", 5);

svg.append("text")
    .attr("class", "main-text")
    .attr("x", centerX)
    .attr("y", centerY)
    .attr("fill", "#FFFFFF")
    .attr("text-anchor", "middle")
    .attr("dominant-baseline", "middle")
    .text("Брусника");