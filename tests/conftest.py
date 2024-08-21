import pytest
import requests
import time

@pytest.fixture(scope="module")
def base_url():
    # Base URL for the application running in Docker
    return "http://localhost:8080/api/jobs"

@pytest.fixture(scope="module")
def create_job(base_url):
    # Ensure the application has started before running tests
    # This might not be necessary if your Docker setup ensures readiness
    time.sleep(10)  # Wait for the app to be fully up and running

    job_data = {
        "jobName": "Initial Job",
        "status": "Pending",
        "jobType": "Type A"
    }
    response = requests.post(base_url, json=job_data)
    return response.json()