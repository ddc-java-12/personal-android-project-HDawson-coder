-- noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE IF NOT EXISTS `History`
(
    `history_id`        INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `timestamp`         INTEGER                           NOT NULL,
    `plant_id`          INTEGER                           NOT NULL,
    `yearPlanted`       INTEGER,
    `totalPlanted`      INTEGER                           NOT NULL,
    `totalFruitYielded` INTEGER                           NOT NULL,
    `dayPlanted`        INTEGER,
    `dayFirstHarvest`   INTEGER,
    FOREIGN KEY (`plant_id`) REFERENCES `Plant` (`plant_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS `index_History_timestamp` ON `History` (`timestamp`);

CREATE INDEX IF NOT EXISTS `index_History_plant_id` ON `History` (`plant_id`);

CREATE TABLE IF NOT EXISTS `Plant`
(
    `plant_id`        INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `timestamp`       INTEGER                           NOT NULL,
    `commonName`      TEXT,
    `scientificName`  TEXT,
    `minTemp`         INTEGER,
    `maxTemp`         INTEGER,
    `daysToMaturity`  INTEGER,
    `spacingInInches` INTEGER
);

CREATE INDEX IF NOT EXISTS `index_Plant_timestamp` ON `Plant` (`timestamp`);

CREATE INDEX IF NOT EXISTS `index_Plant_commonName` ON `Plant` (`commonName`);

CREATE TABLE IF NOT EXISTS `Note`
(
    `note_id`   INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `timestamp` INTEGER                           NOT NULL,
    `plant_id`  INTEGER                           NOT NULL,
    `note`      TEXT,
    FOREIGN KEY (`plant_id`) REFERENCES `Plant` (`plant_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS `index_Note_timestamp` ON `Note` (`timestamp`);

CREATE INDEX IF NOT EXISTS `index_Note_plant_id` ON `Note` (`plant_id`);
