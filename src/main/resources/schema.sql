-- H2 2.1.214;
SET DB_CLOSE_DELAY -1;
;
CREATE USER IF NOT EXISTS "SA" SALT '8ee66401589d87d3' HASH '4605c6da1cca27afd0752a7cf7992b055d2371bb995f8651e4c1f0b6b01f61ad' ADMIN;
CREATE MEMORY TABLE "PUBLIC"."ACCOUNT"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 5) NOT NULL,
    "ACCOUNT_NO" CHARACTER VARYING(45) NOT NULL,
    "BALANCE" DOUBLE PRECISION NOT NULL,
    "LAST_UPDATED_DATE" DATE
);
ALTER TABLE "PUBLIC"."ACCOUNT" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_E" PRIMARY KEY("ID");
-- 4 +/- SELECT COUNT(*) FROM PUBLIC.ACCOUNT;

CREATE MEMORY TABLE "PUBLIC"."TRANSACTION"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 5) NOT NULL,
    "TRANSACTION_AMOUNT" DOUBLE PRECISION NOT NULL,
    "TRANSACTION_TYPE" CHARACTER VARYING(255),
    "TRANSACTION_DATE" DATE,
    "REMARKS" CHARACTER VARYING(255)
);
ALTER TABLE "PUBLIC"."TRANSACTION" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_F" PRIMARY KEY("ID");
-- 4 +/- SELECT COUNT(*) FROM PUBLIC.TRANSACTION;

CREATE MEMORY TABLE "PUBLIC"."LEDGER"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 5) NOT NULL,
    "TRANSACTION_ID" BIGINT,
    "ACCOUNT_ID" BIGINT,
    "CLOSING_BALANCE" DOUBLE PRECISION NOT NULL,
    "OPENING_BALANCE" DOUBLE PRECISION NOT NULL,
    "LEDGER_STATUS" CHARACTER VARYING(255),
    "CREATED_DATE" DATE,
    "LAST_UPDATED_DATE" DATE
);
ALTER TABLE "PUBLIC"."LEDGER" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_G" PRIMARY KEY("ID");
-- 4 +/- SELECT COUNT(*) FROM PUBLIC.LEDGER;

ALTER TABLE "PUBLIC"."ACCOUNT" ADD CONSTRAINT "PUBLIC"."UK_RUXG86Y66HMN0A129N4J75AKK" UNIQUE("ACCOUNT_NO");
ALTER TABLE "PUBLIC"."LEDGER" ADD CONSTRAINT "PUBLIC"."FK6G20FCR3BHR6BIHGY24RQ1R1B" FOREIGN KEY("ACCOUNT_ID") REFERENCES "PUBLIC"."ACCOUNT"("ID") NOCHECK;
ALTER TABLE "PUBLIC"."LEDGER" ADD CONSTRAINT "PUBLIC"."FKNNWPO0LFQ4XAI1RS6887SX02K" FOREIGN KEY("TRANSACTION_ID") REFERENCES "PUBLIC"."TRANSACTION"("ID") NOCHECK;
