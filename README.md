<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My First DevOps Project</title>
</head>
<body>
    <h1>My First DevOps Project</h1>
    <p>This project demonstrates a simple CI/CD pipeline using Docker, Python, and a Spring Boot application with MySQL.</p>

    <h2>Table of Contents</h2>
    <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
        <li><a href="#running-the-project">Running the Project</a></li>
        <li><a href="#testing">Testing</a></li>
        <li><a href="#project-structure">Project Structure</a></li>
        <li><a href="#technologies-used">Technologies Used</a></li>
    </ul>

    <h2 id="prerequisites">Prerequisites</h2>
    <ul>
        <li><a href="https://www.docker.com/get-started">Docker</a></li>
        <li><a href="https://docs.docker.com/compose/install/">Docker Compose</a></li>
        <li>Git</li>
    </ul>

    <h2 id="installation">Installation</h2>
    <ol>
        <li>Clone the repository:
            <pre><code>git clone https://github.com/Vikomizrahi/My_First_DeVops.git
cd My_First_DeVops</code></pre>
        </li>
        <li>Build and run the Docker containers:
            <pre><code>docker-compose up --build</code></pre>
            <p>This command will:</p>
            <ul>
                <li>Build the Spring Boot application Docker image.</li>
                <li>Build the Python test Docker image.</li>
                <li>Start the MySQL database container.</li>
                <li>Start the Spring Boot application container.</li>
                <li>Run the tests in the Python container.</li>
            </ul>
        </li>
    </ol>

    <h2 id="running-the-project">Running the Project</h2>
    <p>To start the application, use the following command:</p>
    <pre><code>docker-compose up</code></pre>
    <p>To stop the application, press <code>Ctrl+C</code> in the terminal where the command was run, and then:</p>
    <pre><code>docker-compose down</code></pre>

    <h2 id="testing">Testing</h2>
    <p>The tests will automatically run after the containers are started. You can also run the tests manually:</p>
    <ol>
        <li>Navigate to the <code>tests</code> directory:
            <pre><code>cd tests</code></pre>
        </li>
        <li>Run the tests using Docker:
            <pre><code>docker-compose up</code></pre>
        </li>
    </ol>

    <h2 id="project-structure">Project Structure</h2>
    <pre><code>My_First_DeVops/
├── ci_cd-server/
│   ├── Dockerfile
│   ├── src/
│   ├── pom.xml
│   └── ...
├── tests/
│   ├── Dockerfile
│   ├── test_jobs.py
│   ├── requirements.txt
│   └── ...
├── docker-compose.yml
└── README.md</code></pre>

    <h2 id="technologies-used">Technologies Used</h2>
    <ul>
        <li><strong>Docker</strong>: Containerization platform</li>
        <li><strong>Spring Boot</strong>: Java-based framework for building web applications</li>
        <li><strong>MySQL</strong>: Relational database management system</li>
        <li><strong>Python</strong>: Programming language for writing tests</li>
        <li><strong>pytest</strong>: Testing framework for Python</li>
    </ul>

    <h2>Additional Notes</h2>
    <ul>
        <li>Make sure Docker is running before executing the commands.</li>
        <li>The health of the Spring Boot application is checked via the <code>/actuator/health</code> endpoint.</li>
        <li>The <code>tests</code> service waits for the <code>app</code> service to be healthy before running the tests.</li>
    </ul>
    
    <p>If you encounter any issues or have any questions, feel free to open an issue on the GitHub repository.</p>
    <p>Happy coding!</p>
</body>
</html>
