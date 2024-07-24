import pytest
import requests


@pytest.fixture(scope="module")
def base_url():
    return "http://app:8080/api/jobs"


@pytest.fixture(scope="module")
def create_job(base_url):
    job_data = {
        "jobName": "Initial Job",
        "status": "Pending",
        "jobType": "Type A"
    }
    response = requests.post(base_url, json=job_data)
    return response.json()
