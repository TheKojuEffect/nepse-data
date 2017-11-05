CREATE TABLE stock_live
(
  value_date        DATE           NOT NULL,
  value_time        TIME           NOT NULL,
  symbol            VARCHAR(255)   NOT NULL,
  ltp               NUMERIC(19, 2) NOT NULL,
  ltv               NUMERIC(19, 2) NOT NULL,
  point_change      NUMERIC(19, 2) NOT NULL,
  percentage_change NUMERIC(19, 2) NOT NULL,
  open              NUMERIC(19, 2) NOT NULL,
  high              NUMERIC(19, 2) NOT NULL,
  low               NUMERIC(19, 2) NOT NULL,
  volume            INTEGER        NOT NULL,
  previous_closing  NUMERIC(19, 2) NOT NULL,
  CONSTRAINT stock_live_pkey
  PRIMARY KEY (value_date, value_time, symbol)
);