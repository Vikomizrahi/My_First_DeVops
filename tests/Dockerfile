# Use the official Python image from the Docker Hub
FROM python:3.11-slim

# Set the working directory inside the container
WORKDIR /app

# Copy requirements file and install dependencies
COPY requirements.txt /app/
RUN pip install --no-cache-dir -r requirements.txt

# Copy the test files to the container
COPY . /app

# Command to run the tests
CMD ["pytest", "--maxfail=1", "--disable-warnings", "-q"]
