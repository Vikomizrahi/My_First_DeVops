<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Devops Project</title>
</head>

    <ol>
        <li><strong>API</strong>: A Spring Boot application with Maven for build automation.</li>
        <li><strong>Tests</strong>: A set of Python tests using pytest.</li>
    </ol>
    <p>Both projects are configured to work together using Docker and Docker Compose.</p>

    <h2>Prerequisites</h2>
    <p>Make sure you have the following installed:</p>
    <ul>
        <li><a href="https://docs.docker.com/get-docker/">Docker</a></li>
        <li><a href="https://docs.docker.com/compose/install/">Docker Compose</a></li>
    </ul>

    <h2>Project Structure</h2>
    <pre>
my-combined-project/
│
├── ci_cd-server/              # Spring Boot API project
│   ├── Dockerfile
│   ├── docker-compose.yml
│   └── ...                    # Other Spring Boot project files
│
└── tests/                     # Python tests project
    ├── Dockerfile
    └── ...                    # Other Python test files
    </pre>

    <h2>Getting Started</h2>

    <h3>1. Clone the Repository</h3>
    <pre><code>git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name
    </code></pre>

    <h3>2. Build and Run the Docker Containers</h3>
    <pre><code>docker-compose up --build
    </code></pre>
    <p>This command will:</p>
    <ul>
        <li>Build the Docker images for both the API and the tests.</li>
        <li>Start the API and the test services in separate containers.</li>
        <li>Automatically set up a MySQL database and connect the API and tests to it.</li>
    </ul>

    <h3>3. Run Tests</h3>
    <p>The test container will automatically run tests when started. You can view the test results in the logs of the <code>tests</code> container.</p>
    <p>To see the logs for the test container:</p>
    <pre><code>docker-compose logs tests
    </code></pre>

    <h3>4. Stopping the Services</h3>
    <p>To stop the running containers:</p>
    <pre><code>docker-compose down
    </code></pre>
    <p>This command will stop and remove all running containers, but will not remove the images or volumes.</p>

    <h2>Troubleshooting</h2>
    <ul>
        <li><strong>Connection Errors</strong>: If you encounter connection errors when running tests, ensure that the API service is fully started and healthy before running tests.</li>
        <li><strong>Permissions</strong>: Make sure Docker has the appropriate permissions to access the required directories and ports.</li>
        <li><strong>Health Checks</strong>: If the API service is not passing the health check, inspect the logs for errors related to the Spring Boot application.</li>
    </ul>

    <h2>License</h2>
    <p>This project is licensed under the MIT License. See the <a href="LICENSE">LICENSE</a> file for details.</p>

    <h2>Contact</h2>
    <p>For questions or issues, please contact <a href="mailto:your-email@example.com">Your Name</a>.</p>
</body>
</html>
