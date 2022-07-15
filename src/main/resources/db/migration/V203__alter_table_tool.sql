ALTER TABLE tool DROP COLUMN number_test_days;
ALTER TABLE tool RENAME COLUMN is_free TO availability;
ALTER TABLE tool ALTER COLUMN availability TYPE varchar USING availability::varchar;

UPDATE tool
    SET availability='START_FREE'
    WHERE id=12;
UPDATE tool
    SET availability='FREE'
    WHERE id=1;
UPDATE tool
    SET availability='FREE'
    WHERE id=2;
UPDATE tool
    SET availability='FREE'
    WHERE id=3;
UPDATE tool
    SET availability='START_FREE'
    WHERE id=6;
UPDATE tool
    SET availability='FREE'
    WHERE id=7;
UPDATE tool
    SET availability='START_FREE'
    WHERE id=9;
UPDATE tool
    SET availability='FREE'
    WHERE id=5;
UPDATE tool
    SET availability='FREE'
    WHERE id=10;
UPDATE tool
    SET availability='FREE'
    WHERE id=8;
UPDATE tool
    SET availability='FREE'
    WHERE id=4;
UPDATE tool
    SET availability='START_FREE'
    WHERE id=13;
UPDATE tool
    SET availability='FREE'
    WHERE id=14;
UPDATE tool
    SET availability='FREE'
    WHERE id=15;