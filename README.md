<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DevOps Project README</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 20px;
            padding: 0;
        }
        h1, h2, h3 {
            color: #333;
        }
        code {
            background-color: #f4f4f4;
            border: 1px solid #ddd;
            padding: 2px 4px;
            border-radius: 4px;
        }
        pre {
            background-color: #f4f4f4;
            border: 1px solid #ddd;
            padding: 10px;
            border-radius: 4px;
            overflow-x: auto;
        }
    </style>
</head>
<body>
    <h1>DevOps Project</h1>
    <p>Welcome to the DevOps project. This project includes a Spring Boot API and associated tests. Follow the instructions below to set up and run the project.</p>

    <h2>Repository Links</h2>
    <ul>
        <li><a href="https://github.com/Vikomizrahi/My_First_DeVops" target="_blank">Master GitHub Repository</a></li>
        <li><a href="https://github.com/Vikomizrahi/My_First_DeVops/tree/master/ci_cd-server" target="_blank">ci_cd-server Project</a></li>
        <li><a href="https://github.com/Vikomizrahi/My_First_DeVops/tree/master/tests" target="_blank">Tests Project</a></li>
    </ul>

    <h2>Setup and Running Instructions</h2>

    <h3>1. Clone the Repository</h3>
    <pre><code>git clone https://github.com/Vikomizrahi/My_First_DeVops.git</code></pre>
    <p>Navigate to the project directory:</p>
    <pre><code>cd My_First_DeVops</code></pre>

    <h3>2. Running the API</h3>
    <p>Navigate to the <code>ci_cd-server</code> directory:</p>
    <pre><code>cd ci_cd-server</code></pre>
    <p>Build and run the API using Docker Compose:</p>
    <pre><code>docker-compose up --build</code></pre>
    <p>The API will be accessible at <code>http://localhost:8080/api/jobs</code>.</p>

    <h3>3. Running the Tests</h3>
    <p>Navigate to the <code>tests</code> directory:</p>
    <pre><code>cd ../tests</code></pre>
    <p>Build and run the tests using Docker Compose:</p>
    <pre><code>docker-compose -f docker-compose.tests.yml up --build</code></pre>
    <p>Note: Ensure that the <code>docker-compose.tests.yml</code> file is correctly configured for the test environment.</p>

    <h2>Additional Information</h2>
    <p>If you encounter any issues, ensure that Docker is properly installed and running on your machine. Verify network configurations and dependencies.</p>

    <h2>Contact</h2>
    <p>For any questions or issues, please contact the project maintainer.</p>
</body>
</html>
