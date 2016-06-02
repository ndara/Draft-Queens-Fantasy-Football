-- Nishanth Dara
-- ndara@calpoly.edu


-- Finds the top 10 TEs FROM remaining player pool based on average ppg.
SELECT Z.player
FROM (
   SELECT 
      AVG(Y.pts) AS avgPts,
      Y.player,
      Y.fname,
      Y.lname
   FROM (
      SELECT
         G.wk, 
         (SUM(S.yds) / 10) + COALESCE(X.tds, 0) AS pts,
         L.player,
         L.fname,
         L.lname,
         L.pos1,
         L.teamOn
      FROM
         Play P
         JOIN Game G ON G.gid = P.gid
         JOIN Pass S ON P.pid = S.pid
         JOIN Player L ON S.trg = L.player
         LEFT JOIN (
            SELECT
               COUNT(*) * 6 AS tds,
               L.player,
               wk
            FROM
               Play P
               JOIN Game G ON G.gid = P.gid
               JOIN Pass S ON S.pid = P.pid
               JOIN Player L ON S.trg = L.player
               JOIN Td T ON P.pid = T.pid
            WHERE
               P.type = 'PASS'
               AND teamOn = 0
            GROUP BY wk, L.player
         ) X ON X.player = L.player AND X.wk = G.wk
      WHERE
         P.type = 'PASS'
         AND teamOn = 0
      GROUP BY G.wk, L.player
      ) Y
   WHERE Y.pos1 = 'TE' AND Y.teamOn = 0
   GROUP BY Y.player
   ORDER BY avgPts DESC
   LIMIT 10
   ) Z
ORDER BY RAND()
LIMIT 1;

-- Finds the top 10 WRs FROM remaining player pool based on average ppg.
SELECT Z.player
FROM (
   SELECT 
      AVG(Y.pts) AS avgPts,
      Y.player,
      Y.fname,
      Y.lname
   FROM (
      SELECT
         G.wk, 
         (SUM(S.yds) / 10) + COALESCE(X.tds, 0) AS pts,
         L.player,
         L.fname,
         L.lname,
         L.pos1,
         L.teamOn
      FROM
         Play P
         JOIN Game G ON G.gid = P.gid
         JOIN Pass S ON P.pid = S.pid
         JOIN Player L ON S.trg = L.player
         LEFT JOIN (
            SELECT
               COUNT(*) * 6 AS tds,
               L.player,
               wk
            FROM
               Play P
               JOIN Game G ON G.gid = P.gid
               JOIN Pass S ON S.pid = P.pid
               JOIN Player L ON S.trg = L.player
               JOIN Td T ON P.pid = T.pid
            WHERE
               P.type = 'PASS'
               AND teamOn = 0
            GROUP BY wk, L.player
         ) X ON X.player = L.player AND X.wk = G.wk
      WHERE
         P.type = 'PASS'
         AND teamOn = 0
      GROUP BY G.wk, L.player
      ) Y
   WHERE Y.pos1 = 'WR' AND Y.teamOn = 0
   GROUP BY Y.player
   ORDER BY avgPts DESC
   LIMIT 10
   ) Z
ORDER BY RAND()
LIMIT 1;

-- Finds the top 10 RBs FROM remaining player pool based on average ppg.
SELECT Z.player
FROM (
   SELECT 
      AVG(Y.pts) AS avgPts,
      Y.player,
      Y.fname,
      Y.lname
   FROM (
      SELECT
         G.wk, 
         (SUM(R.yds) / 10) + COALESCE(X.tds, 0) AS pts,
         L.player,
         L.fname,
         L.lname,
         L.pos1,
         L.teamOn
      FROM
         Play P
         JOIN Game G ON G.gid = P.gid
         JOIN Rush R ON P.pid = R.pid
         JOIN Player L ON R.bc = L.player
         LEFT JOIN (
            SELECT
               COUNT(*) * 6 AS tds,
               L.player,
               wk
            FROM
               Play P
               JOIN Game G ON G.gid = P.gid
               JOIN Rush R ON R.pid = P.pid
               JOIN Player L ON R.bc = L.player
               JOIN Td T ON P.pid = T.pid
            WHERE
               P.type = 'RUSH'
               AND teamOn = 0
            GROUP BY wk, L.player
         ) X ON X.player = L.player AND X.wk = G.wk
      WHERE
         P.type = 'RUSH'
         AND teamOn = 0
      GROUP BY G.wk, L.player
      ORDER BY L.player, G.wk
      ) Y
   WHERE Y.pos1 = 'RB' AND Y.teamOn = 0
   GROUP BY Y.player
   ORDER BY avgPts DESC
   LIMIT 10
   ) Z
ORDER BY RAND()
LIMIT 1;

-- Finds the top 10 QBs FROM remaining player pool based on average ppg.
SELECT Z.player
FROM (
   SELECT 
      AVG(Y.pts) AS avgPts,
      Y.player,
      Y.fname,
      Y.lname
   FROM (
      SELECT
         G.wk,
         SUM(yds) / 25 + COALESCE(X.tds, 0) AS pts,
         L.player,
         L.fname,
         L.lname,
         L.pos1,
         L.teamOn
      FROM
         Play P
         JOIN Game G ON G.gid = P.gid
         JOIN Pass S ON P.pid = S.pid
         JOIN Player L ON L.player = S.psr
         LEFT JOIN (
            SELECT
               wk,
               COUNT(*) * 4 AS tds,
               L.player
            FROM
               Play P
               JOIN Game G ON G.gid = P.gid
               JOIN Pass S ON S.pid = P.pid
               JOIN Player L ON S.psr = L.player
               JOIN Td T ON P.pid = T.pid
            WHERE
               P.type = 'PASS'
               AND teamOn = 0
            GROUP BY wk, L.player
         ) X ON X.player = L.player AND X.wk = G.wk
      WHERE
         P.type = 'PASS'
         AND teamOn = 0
      GROUP BY G.wk, L.player
      ORDER BY L.player, G.wk
      ) Y
   WHERE Y.pos1 = 'QB' AND Y.teamOn = 0
   GROUP BY Y.player
   ORDER BY avgPts DESC
   LIMIT 10
   ) Z
ORDER BY RAND()
LIMIT 1;
