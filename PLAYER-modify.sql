DELETE FROM Player 
WHERE 
   pos1 != 'QB' 
   AND pos1 != 'RB'
   AND pos1 != 'WR'
   AND pos1 != 'TE';

DELETE FROM Player
WHERE
   dcp = 0
   OR dcp > 2;

ALTER TABLE Player 
  ADD COLUMN teamOn INT DEFAULT 0;


