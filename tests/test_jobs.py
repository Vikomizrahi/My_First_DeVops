import requests
from utils import log_response




def test_get_all_jobs(base_url):
    response = requests.get(base_url)
    log_response(response)
    assert response.status_code == 200



def test_create_job(base_url):
    job_data = {
        "jobName": "New Job",
        "status": "Pending",
        "jobType": "Type B"
    }
    response = requests.post(base_url, json=job_data)
    log_response(response)
    assert response.status_code == 201
    assert response.json().get("jobName") == job_data["jobName"]


def test_get_job_by_id(base_url, create_job):
    job_id = create_job["id"]
    response = requests.get(f"{base_url}/{job_id}")
    log_response(response)
    assert response.status_code == 200
    assert response.json().get("id") == job_id


def test_update_job_status(base_url, create_job):
    job_id = create_job["id"]

    update_data = {
        "jobName": "Updated Job",
        "status": "In Progress",
        "jobType": "Type B"
    }

    response = requests.put(f"{base_url}/{job_id}", json=update_data)
    log_response(response)
    assert response.status_code == 200
    assert response.json().get("status") == update_data["status"]


def test_delete_job(base_url, create_job):
    job_id = create_job["id"]
    response = requests.delete(f"{base_url}/{job_id}")
    log_response(response)
    assert response.status_code == 204
