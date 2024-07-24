FROM python:3.11

WORKDIR /app

COPY requirements.txt .
RUN pip install -r requirements.txt

COPY . .

# This will set pytest as the default command to run when the container starts
CMD ["pytest"]