default:
	echo "Hello 👋"

test:
	./mvnw test

run:
	java -jar target/rulof.application.task-0.0.1-SNAPSHOT.jar

clean:
	./mvnw clean

install:
	./mvnw install
