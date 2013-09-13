# Ray Tracer

A project created in the context of a week of studies. Aim was to create a ray tracing algorithm and implement different features such as refractions, different materials or geometrical objects.

## Objects

### Triangle
The triangle is defined by three points. The normal vector of this triangle is calculated and shows which side is up.

### Rectangle
A rectangle is a combination of two triangles. It takes three points as input and calculates the other point from those three.

### Sphere
The sphere is defined by a radius and a center point. In addition a normal vector can be passed in with the coordinates. This vector will determine how the sphere is aligned.

### Cuboid
A cuboid is made up of six rectangles and takes four points as input. These points should be in a right angle to each other. The other cuboid points are then calculated from those points.

## Cameras

### Pinhole Camera
The pinhole camera is the most basic camera possible. It sends all rays from a single point.

### Lens Camera
The lens camera implements a depth of view preception. It shifts the origin of the light rays by a value (= lens radius). The rays are pointed at a point on the focal plane. If an object is on that focal plane it will appear in focus, if not it is blurred.

## Materials

### Diffuse Material

### Phong Material

### Reflective Material

### Refractive Material

