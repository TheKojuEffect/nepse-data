CREATE TABLE floor_sheet (
  contract_no   BIGINT         NOT NULL CONSTRAINT floor_sheet_pkey PRIMARY KEY,
  stock_symbol  VARCHAR(255)   NOT NULL,
  buyer_broker  VARCHAR(255)   NOT NULL,
  seller_broker VARCHAR(255)   NOT NULL,
  quantity      INTEGER        NOT NULL,
  rate          NUMERIC(19, 2) NOT NULL,
  amount        NUMERIC(19, 2) NOT NULL
);