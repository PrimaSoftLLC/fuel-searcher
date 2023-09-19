DROP TABLE IF EXISTS property_metadata;
DROP TABLE IF EXISTS table_metadata;

CREATE TABLE table_metadata
(
    id         SERIAL       NOT NULL PRIMARY KEY,
    table_name VARCHAR(256) NOT NULL
);

CREATE TABLE property_metadata
(
    id                SERIAL       NOT NULL PRIMARY KEY,
    table_metadata_id INTEGER      NOT NULL,
    property_name     VARCHAR(256) NOT NULL,
    allowable_values  TEXT[] NOT NULL
);

ALTER TABLE property_metadata
    ADD CONSTRAINT fk_property_metadata_to_table_metadata
        FOREIGN KEY (table_metadata_id) REFERENCES table_metadata (id)
            ON DELETE CASCADE;

ALTER TABLE table_metadata
    ADD CONSTRAINT table_name_should_be_unique UNIQUE (table_name);

ALTER TABLE property_metadata
    ADD CONSTRAINT table_metadata_id_and_property_name_should_be_unique UNIQUE (table_metadata_id, property_name);
