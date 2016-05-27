DELETE FROM Player 
WHERE 
  pos1 != 'QB' AND pos1 != 'RB' AND pos1 != 'WR' AND pos1 != 'TE';

ALTER TABLE Player 
  ADD COLUMN teamOn INT DEFAULT 0;
