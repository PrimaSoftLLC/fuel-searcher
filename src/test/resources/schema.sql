CREATE TABLE table_metadata
(
    id         SERIAL       NOT NULL PRIMARY KEY,
    table_name VARCHAR(256) NOT NULL
);

CREATE TABLE column_metadata
(
    id               SERIAL       NOT NULL PRIMARY KEY,
    table_id         INTEGER      NOT NULL,
    column_name      VARCHAR(256) NOT NULL,
    allowable_values VARCHAR(256)[] NOT NULL
);

ALTER TABLE column_metadata
    ADD CONSTRAINT fk_column_metadata_to_table_metadata
        FOREIGN KEY (table_id) REFERENCES table_metadata (id);
