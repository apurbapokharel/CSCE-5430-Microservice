CREATE TABLE IF NOT EXISTS driver (
  id                BIGINT AUTO_INCREMENT,
  first_name        CHAR(24),
  last_name         CHAR(24),
  vehicle_id        BIGINT,
  phone_number      CHAR(20),
  PRIMARY KEY (id)
);
