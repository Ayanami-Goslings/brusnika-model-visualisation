export function drawLocationView(presenter, location) {
    const content = document.querySelector(".content");
    content.innerHTML = '<svg width="1550" height="1004"></svg>';
    const svg = d3.select("svg");
    const centerX = 775;
    const centerY = 502;
    const rectWidth = 208;
    const rectHeight = 50;

    drawLocationRectangle(svg, centerX, 50, presenter);

    function drawLocationRectangle(svg, x, y, presenter) {
        const rect = svg.append("rect")
            .attr("class", "location-rect")
            .attr("x", x - 50)
            .attr("y", y - 25)
            .attr("width", 104)
            .attr("height", 50)
            .attr("fill", "#8C123C")
            .attr("rx", 5)
            .attr("ry", 5)
            .on("click", () => presenter.onBackToMainView())
            .on("mouseover", function() {
                d3.select(this).attr("fill", "#6e001f");
            })
            .on("mouseout", function() {
                d3.select(this).attr("fill", "#8C123C");
            });
    
        drawLocationText(svg, x, y, location, presenter, rect);
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
            .on("click", () => presenter.onBackToMainView())
            .on("mouseover", function() {
                rect.attr("fill", "#6e001f");
            })
            .on("mouseout", function() {
                rect.attr("fill", "#8C123C");
            });
    }
}