-- Returns point total for RB GIVEN a variable week and variable player ID
SELECT
   (SUM(R.yds) / 10) + X.tds AS pts
FROM
   Play P 
   JOIN Game G ON G.gid = P.gid
   JOIN Rush R ON R.pid = P.pid
   JOIN Player L ON R.bc = L.player
   JOIN (
      SELECT
         COUNT(*) * 6 AS tds,
         L.player AS player
      FROM
         Play P
         JOIN Game G ON G.gid = P.gid
         JOIN Rush R ON R.pid = P.pid
         JOIN Player L ON R.bc = L.player
         JOIN Td T ON P.pid = T.pid
      WHERE
         wk = 1
         AND P.type = 'RUSH'
         AND L.player = 'CN-0500'
   ) X ON X.player = L.player
WHERE
   wk = 1
   AND P.type = 'RUSH'
   AND L.player = 'CN-0500';


-- Returns point total for WR or TE GIVEN a variable week and variable playerID
SELECT 
   (SUM(S.yds) / 10) + X.tds AS pts
FROM
   Play P
   JOIN Game G ON G.gid = P.gid
   JOIN Pass S ON P.pid = S.pid
   JOIN Player L ON S.trg = L.player
   JOIN (
      SELECT
         COUNT(*) * 6 AS tds,
         L.player
      FROM
         Play P
         JOIN Game G ON G.gid = P.gid
         JOIN Pass S ON S.pid = P.pid
         JOIN Player L ON S.trg = L.player
         JOIN Td T ON P.pid = T.pid
      WHERE
         wk = 1
         AND P.type = 'PASS'
         AND L.player = 'RG-2200'
   ) X ON X.player = L.player 
WHERE
   wk = 1
   AND P.type = 'PASS'
   AND L.player = 'RG-2200';


-- Returns the point total for QB GIVEN a variable week and variable playerID
SELECT SUM(yds) / 25 + X.tds AS pts 
FROM
   Play P
   JOIN Game G ON G.gid = P.gid
   JOIN Pass S ON P.pid = S.pid
   JOIN Player L ON L.player = S.psr
   JOIN (
      SELECT
         COUNT(*) * 4 AS tds,
         L.player
      FROM
         Play P
         JOIN Game G ON G.gid = P.gid
         JOIN Pass S ON S.pid = P.pid
         JOIN Player L ON S.psr = L.player
         JOIN Td T ON P.pid = T.pid
      WHERE
         wk = 1
         AND P.type = 'PASS'
         AND L.player = 'CN-0500'
   ) X ON X.player = L.player
WHERE
   wk = 1
   AND P.type = 'PASS'
   AND L.player = 'CN-0500';
