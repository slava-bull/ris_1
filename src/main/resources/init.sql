DROP TABLE IF EXISTS nodes;

CREATE TABLE nodes
(
    id       BIGINT           NOT NULL PRIMARY KEY,
    username VARCHAR(256)     NOT NULL,
    lon      DOUBLE PRECISION NOT NULL,
    lat      DOUBLE PRECISION NOT NULL
);

DROP TABLE IF EXISTS tags;

CREATE TABLE tags
(
    nodeid BIGINT       NOT NULL REFERENCES nodes (id),
    key    VARCHAR(256) NOT NULL,
    value  VARCHAR(256) NOT NULL,
    PRIMARY KEY (node_id, key)
);