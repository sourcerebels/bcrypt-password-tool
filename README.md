# Password Tool

Tool to encode / match secrets. 

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
./gradlew clean build && docker build -t sourcerebels/password-tool:0.0.1-SNAPSHOT .
```

```
docker run -d -p 8080:8080 --name password-tool sourcerebels/password-tool:0.0.1-SNAPSHOT
```
