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
        FOREIGN KEY (property_metadata_id) REFERENCES table_metadata (id);
