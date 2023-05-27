CREATE DATABASE IF NOT EXISTS `trading`;

use `trading`;

CREATE TABLE IF NOT EXISTS `candle`
(
    id         bigint PRIMARY KEY AUTO_INCREMENT,
    stock_code VARCHAR(255),
    price      bigint,
    high_price bigint,
    low_price  bigint,
    volume     bigint,
    base_time  DateTime,
    CONSTRAINT uc_stockCode_baseTime UNIQUE (stock_code, base_time)
);