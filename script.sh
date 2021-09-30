## 개발용 DB
docker run -p 5432:5432 --name doc-db -e POSTGRES_USER=doc -e POSTGRES_PASSWORD=doc -e POSTGRES_DB=doc -d postgres

## 테스트용 DB
docker run -p 15432:5432 --name doc-testdb -e POSTGRES_USER=doctest -e POSTGRES_PASSWORD=doctest -e POSTGRES_DB=doctest -d postgres