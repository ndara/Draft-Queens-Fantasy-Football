DELETE FROM Player 
WHERE 
  pos1 != 'QB' AND pos1 != 'RB' AND pos1 != 'WR' AND pos1 != 'TE';

DELETE FROM Player
WHERE
  player NOT IN (SELECT DISTINCT trg FROM Pass 
               UNION 
               SELECT DISTINCT psr FROM Pass 
               UNION 
               SELECT DISTINCT bc FROM Rush);
