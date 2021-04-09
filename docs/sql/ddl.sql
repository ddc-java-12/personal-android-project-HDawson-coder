CREATE TABLE IF NOT EXISTS `History`
(
    `history_id`        INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `timestamp`         INTEGER                           NOT NULL,
    `plant_id`          INTEGER                           NOT NULL,
    `yearPlanted`       INTEGER                           NOT NULL,
    `totalPlanted`      INTEGER                           NOT NULL,
    `totalFruitYielded` INTEGER                           NOT NULL,
    `dayPlanted`        INTEGER                           NOT NULL,
    `dayFirstHarvest`   INTEGER                           NOT NULL,
    FOREIGN KEY (`plant_id`) REFERENCES `Plant` (`plant_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS `index_History_timestamp` ON `History` (`timestamp`);

CREATE INDEX IF NOT EXISTS `index_History_plant_id` ON `History` (`plant_id`);

CREATE TABLE IF NOT EXISTS `Plant`
(
    `plant_id`        INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `timestamp`       INTEGER                           NOT NULL,
    `commonName`      TEXT                              NOT NULL,
    `scientificName`  TEXT                              NOT NULL,
    `minTemp`         INTEGER                           NOT NULL,
    `maxTemp`         INTEGER                           NOT NULL,
    `daysToMaturity`  INTEGER                           NOT NULL,
    `spacingInInches` INTEGER                           NOT NULL
);

CREATE INDEX IF NOT EXISTS `index_Plant_timestamp` ON `Plant` (`timestamp`);

CREATE INDEX IF NOT EXISTS `index_Plant_commonName` ON `Plant` (`commonName`);

CREATE TABLE IF NOT EXISTS `Note`
(
    `note_id`   INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `timestamp` INTEGER                           NOT NULL,
    `plant_id`  INTEGER                           NOT NULL,
    `category`  INTEGER                           NOT NULL,
    `note`      TEXT,
    FOREIGN KEY (`plant_id`) REFERENCES `Plant` (`plant_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS `index_Note_timestamp` ON `Note` (`timestamp`);

CREATE INDEX IF NOT EXISTS `index_Note_plant_id` ON `Note` (`plant_id`);

CREATE INDEX IF NOT EXISTS `index_Note_category` ON `Note` (`category`);

