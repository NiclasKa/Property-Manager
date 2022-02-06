CREATE TABLE IF NOT EXISTS properties (
  "id" SERIAL NOT NULL,
  "name" TEXT NOT NULL,
  "address" TEXT NOT NULL,
  "postalCode" TEXT NOT NULL,
  "city" TEXT NOT NULL,
  "country" TEXT NOT NULL,
  "description" TEXT NOT NULL,
  "coordinates" TEXT NOT NULL,
  
  PRIMARY KEY ("id")
);