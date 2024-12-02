import numpy as np
import matplotlib.pyplot as plt
import matplotlib.animation as animation

# Parameters
num_particles = 5
max_iterations = 10
positionX = np.array([2, 3, 4, 5, 6], dtype=float)
velocity = np.array([1, 1, 3, 2, 2], dtype=float)
pbest = positionX.copy()
gbest = positionX[0]  # Initialize gbest to the first particle's position
c1 = 1.496180  # cognitive coefficient
c2 = 1.496180  # social coefficient
w = 0.729844  # inertia coefficient

# Store positions for animation
history_positions = []

# Objective function
def objective_function(x):
    return x ** 2

# Update positions
for i in range(max_iterations):
    # Store the current positions and global best for this iteration
    history_positions.append((positionX.copy(), gbest))

    for j in range(num_particles):
        pos = objective_function(positionX[j])
        pbest_fitness = objective_function(pbest[j])
        gbest_fitness = objective_function(gbest)

        # Check if current position is better than the personal best
        if pos < pbest_fitness:
            pbest[j] = positionX[j]

        # Check if the personal best is better than the global best
        if pbest_fitness < gbest_fitness:
            gbest = pbest[j]

        # Update velocity
        r1, r2 = np.random.uniform(-5, 5, 2)
        velocity[j] = (w * velocity[j] + 
                       c1 * r1 * (pbest[j] - positionX[j]) + 
                       c2 * r2 * (gbest - positionX[j]))
        positionX[j] += velocity[j]

# Create the figure
fig, ax = plt.subplots()
x_vals = np.linspace(-20, 20, 100)  # Range from -20 to 20
y_vals = objective_function(x_vals)

# Set fixed axis limits
ax.set_xlim(-20, 20)
ax.set_ylim(0, 400)  # f(x) = x^2, max y will be 20^2 = 400
ax.plot(x_vals, y_vals, 'r--', label='Objective Function: f(x) = x^2')

# Function to update the frame
def update(frame):
    ax.clear()  # Clear the plot for the next frame
    ax.set_xlim(-20, 20)
    ax.set_ylim(0, 400)
    
    ax.plot(x_vals, y_vals, 'r--', label='Objective Function: f(x) = x^2')
    positions, gbest_position = history_positions[frame]

    # Plot the particles' positions
    for j in range(num_particles):
        pos_y = objective_function(positions[j])
        ax.scatter(positions[j], pos_y, c='blue')  # Particle position

    # Plot gbest position clearly
    gbest_y = objective_function(gbest_position)
    ax.scatter(gbest_position, gbest_y, c='green', s=100, label='Global Best (gbest)', marker='*')  # gbest position
    ax.annotate(f'gbest = ({gbest_position:.2f}, {gbest_y:.2f})',
                xy=(gbest_position, gbest_y), xycoords='data',
                xytext=(gbest_position + 5, gbest_y + 25),
                arrowprops=dict(arrowstyle="->", lw=1.5, color='green'),
                fontsize=10, color='green')

    # Draw arrows for velocity (scaled down for visibility)
    velocity_scale = 0.05  # Scale down the velocity vectors for better visibility
    for j in range(num_particles):
        pos_y = objective_function(positions[j])
        ax.quiver(positions[j], pos_y, velocity[j] * velocity_scale, -pos_y * velocity_scale, 
                   angles='xy', scale_units='xy', scale=1, color='orange', alpha=0.5)

    ax.legend()
    ax.set_title('Particle Swarm Optimization Animation')
    ax.set_xlabel('Position (x)')
    ax.set_ylabel('Objective Function Value (f(x))')

    # Show iteration number
    ax.text(0.05, 0.95, f'Iteration: {frame + 1}/{max_iterations}', transform=ax.transAxes,
            fontsize=12, verticalalignment='top')

# Create the animation
ani = animation.FuncAnimation(fig, update, frames=len(history_positions), repeat=False)

# Save the animation as a GIF
ani.save('pso_animation.gif', writer='pillow', fps=1)
plt.show()  # Uncomment this to display the animation in the plot window
 