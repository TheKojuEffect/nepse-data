CREATE TABLE todays_price
(
  date               DATE           NOT NULL,
  company_name       VARCHAR(255)   NOT NULL,
  amount             NUMERIC(19, 2) NOT NULL,
  closing_price      NUMERIC(19, 2) NOT NULL,
  difference         NUMERIC(19, 2) NOT NULL,
  max_price          NUMERIC(19, 2) NOT NULL,
  min_price          NUMERIC(19, 2) NOT NULL,
  no_of_transactions INTEGER        NOT NULL,
  previous_closing   NUMERIC(19, 2) NOT NULL,
  traded_shares      INTEGER        NOT NULL,
  value_date         DATE           NOT NULL,
  value_time         TIME           NOT NULL,
  CONSTRAINT todays_price_pkey
  PRIMARY KEY (date, company_name)
);