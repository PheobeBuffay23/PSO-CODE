import matplotlib.pyplot as plt

# Example globalbestX and globalbestY from the output of Java program (replace with actual values)
globalBestHistoryX = [1.0, 0.8, 0.6, 0.5, 0.4, 0.3, 0.2, 0.1, 0.05, 0.02]  # Global best X positions over iterations
globalBestHistoryY = [1.0, 0.9, 0.8, 0.7, 0.6, 0.5, 0.4, 0.3, 0.2, 0.1]  # Global best Y positions over iterations

# Create an iteration number list (assuming 10 iterations)
iterations = list(range(1, len(globalBestHistoryX) + 1))

# Plotting the global best positions over the iterations
plt.figure(figsize=(8, 6))
plt.plot(iterations, globalBestHistoryX, label="Global Best X", color='r', marker='o')
plt.plot(iterations, globalBestHistoryY, label="Global Best Y", color='b', marker='x')

# Add labels and title
plt.xlabel('Iteration')
plt.ylabel('Position Value')
plt.title('Global Best Position Evolution Over Iterations')

# Add a legend
plt.legend()

# Show the plot
plt.grid(True)
plt.show()
