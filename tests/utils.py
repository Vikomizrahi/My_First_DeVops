import logging

logger = logging.getLogger(__name__)

def log_response(response):
    logger.info(f"Status Code: {response.status_code}")
    logger.info(f"Response Body: {response.text}")
