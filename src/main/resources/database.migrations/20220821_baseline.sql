CREATE TABLE pages (
    id           serial      PRIMARY KEY,
    state_delta  jsonb,
    raw_content  text        NOT NULL,
    created_at   timestamp   NOT NULL,
    created_by   text        NOT NULL,
    modified_at  timestamp   NOT NULL,
    modified_by  text   NOT NULL
);

CREATE TABLE page_translations (
    id           serial      PRIMARY KEY,
    page_id      integer,
    locale       varchar(5)  NOT NULL,
    raw_content  text        NOT NULL,
    created_at   timestamp   NOT NULL,
    created_by   text        NOT NULL,
    modified_at  timestamp   NOT NULL,
    modified_by  text   NOT NULL,
    UNIQUE (page_id, locale),
    FOREIGN KEY (page_id)
          REFERENCES pages (id)
);
