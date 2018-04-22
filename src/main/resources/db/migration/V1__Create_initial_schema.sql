CREATE TABLE cities (
  city_id UUID PRIMARY KEY,
  name VARCHAR
);

CREATE TABLE weather (
  weather_id UUID PRIMARY KEY,
  units VARCHAR,
  temperature DOUBLE PRECISION,
  creation_date TIMESTAMP,
  modification_date TIMESTAMP,
  city_id UUID REFERENCES cities(city_id)
);

INSERT INTO cities(city_id, name)
 VALUES('6a8e4688-62d0-44e8-baac-856c5ffc74bd', 'Moscow')