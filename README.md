# Bcrypt Password Tool

Tool to encode / match bcrypt secrets. 

## Usage

```
./gradlew clean build bootRun
```

Point your browser:

```
http://localhost:8080
```

## Docker

Build

```
./gradlew clean build && docker build -t sourcerebels/bcrypt-password-tool:0.0.1-SNAPSHOT .
```

```
docker run -d -p 8080:8080 --name bcrypt-password-tool sourcerebels/bcrypt-password-tool:0.0.1-SNAPSHOT
```
