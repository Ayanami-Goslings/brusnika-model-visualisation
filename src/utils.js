function calculateCoordinates(centerX, centerY, radius, index, totalLocations) {
    const angleStep = (2 * Math.PI) / totalLocations;
    const angle = index * angleStep;
    const x = centerX + radius * Math.cos(angle);
    const y = centerY + radius * Math.sin(angle);
    return { x, y };
}

export {calculateCoordinates};